package com.pantau.core

import com.pantau.user.AuthRole
import com.pantau.user.AuthUser
import com.pantau.user.AuthUserAuthRole
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import grails.validation.Validateable
import static org.springframework.http.HttpStatus.*

@Secured(['permitAll'])
@Transactional(readOnly = true)
class ApiController {
    def passwordEncoder
    static allowedMethods = [hargaall: "POST", comodity: "POST", input: "POST", register: "POST", login: "POST"]

    def hargaall(LookupCommand lookup) {
        println 'lookup >>>' + lookup.radius
        //Double radius = lookup.radius/157
        List markers = new ArrayList();
        def comodities = Comodity.where {
            ilike('name', "%${lookup.name}%")


        }.list()
        if (!comodities.isEmpty()) {
            println 'comodities' + comodities
            /* ComodityInput.executeQuery {

                'in'('comodityName', comodities)

                //lt('distance', lookup.radius)
                // between('lat', lookup.lat-radius, lookup.lat+radius)
                //between('lng', lookup.lng-radius, lookup.lng+radius)

                order('dateCreated', 'desc'
                )
            }.*/
            String query = "from ComodityInput where sqrt(POWER (69.1 * (lat - :ulatitude),2) + POWER (69.1 * (lng - :ulongitude) * cos(:ulatitude / 57.3),2)) * 1.609344 < :udistance and comodityName in (:ucomodityName) ORDER BY dateCreated desc"
            def inputs = ComodityInput.executeQuery (query,[ulatitude: lookup.lat, ulongitude:lookup.lng, udistance:lookup.radius, ucomodityName:comodities])
            .unique {
                it.lat
                it.lng
            }.each {
                def nohp = 0
                if (it.type != null || it.type == 0) {
                    if (it.user.enabled) {
                        nohp = it.user.nohp
                    }

                }
                markers.add(new Marker(barang: it.comodityName.name, price: it.price, latitude: it.lat, longitude: it.lng, nohp: nohp, lastUpdated: it.lastUpdated, type:it.type))
            }

        }
        respond markers
    }

    def comodityall() {
        respond Comodity.list([sort: 'name', order: 'asc'])
    }

    def comodity(LookupCommand lookup) {
        println 'test ' + lookup.name
        println "%${lookup.name}%"
        respond Comodity.where {
            ilike('name', "%${lookup.name}%")

        }.list([sort: 'name', order: 'asc', max: 10])
    }

    @Transactional
    def input(PostComodityCommand instanceCommodity) {
        println instanceCommodity.id
        println instanceCommodity.harga

        println instanceCommodity.nohp
        println instanceCommodity.quantity
        def comodity = Comodity.get(instanceCommodity.id)
        if (!comodity) {
            request.withFormat {
                '*' { render status: NO_CONTENT }
            }
            return
        }
        Double dt = 0
        Region district = Region.find("FROM Region ORDER BY id")
        Integer type = 0
        if (instanceCommodity.quantity > 0) {
            type = 1
        }
        def member = AuthUser.findByNohp(instanceCommodity.nohp)
        def com = new ComodityInput(user: member, comodityName: comodity, price: instanceCommodity.harga, lat: instanceCommodity.lat, lng: instanceCommodity.lng, amount: instanceCommodity.quantity, type:type, delta: dt, region: district)
        if (!com.save(flush: true)) {
            println 'error ' + com.errors.allErrors.join(' \n')
            //each error is an instance of  org.springframework.validation.FieldError
        }
        println 'com ' + com.lat + ' ' + com.lng
        request.withFormat {
            '*' { respond instanceCommodity, [status: CREATED] }
        }
    }

    @Transactional
    def register(UserRegisterCommand userRegister) {
        println userRegister.username
        def res
        def user = AuthUser.findByNohp(userRegister.nohp)
        println 'userRegister >>>>>>>>> ' + userRegister.properties
        if (user == null) {
            user = AuthUser.findByUsername(userRegister.username?.toLowerCase())
            if (user == null) {
                user = new AuthUser(userRegister.properties).save()
                AuthRole authRole = AuthRole.findByAuthority('ROLE_TRUSTED')
                AuthUserAuthRole.create user, authRole, false
                if (user.hasErrors()) {
                    res = new Message(message: user.errors.toString(), error: true)
                    request.withFormat {
                        '*' { respond res, [status: INTERNAL_SERVER_ERROR] }
                    }
                } else {
                    request.withFormat {
                        '*' { respond user, [status: CREATED] }
                    }
                }
            } else {
                res = new Message(message: 'Email sudah digunakan', error: true)
            }
        } else {
            res = new Message(message: 'No Handphone sudah digunakan', error: true)
        }
        println 'user exists: ' + (user != null)
        request.withFormat {
            '*' { respond res, [status: FORBIDDEN] }
        }
    }

    @Transactional
    def login(UserLoginCommand userLogin) {

        def user = AuthUser.findByUsername(userLogin.username)
        if (user) {
            if (passwordEncoder.isPasswordValid(user.password, userLogin.password, null)) {
                request.withFormat {
                    '*' { respond user, [status: OK] }
                }
            }
        }
        request.withFormat {
            '*' { respond userLogin, [status: UNAUTHORIZED] }
        }
    }

    @Transactional
    def inputRequest(PostComodityCommand instanceCommodity) {
        println instanceCommodity.id
        println instanceCommodity.harga

        println instanceCommodity.nohp
        println instanceCommodity.quantity
        def comodity = Comodity.get(instanceCommodity.id)
        if (!comodity) {
            request.withFormat {
                '*' { render status: NO_CONTENT }
            }
            return
        }

        // def last = ComodityInput.list([max: 1, sort: 'dateCreated', order: 'asc'])
        Double dt = 0

        Region district = Region.find("FROM Region ORDER BY id")
        Integer type = 2

        def member = AuthUser.findByNohp(instanceCommodity.nohp)
        def com = new ComodityInput(user: member, comodityName: comodity, price: instanceCommodity.harga, lat: instanceCommodity.lat, lng: instanceCommodity.lng, amount: instanceCommodity.quantity, type:type, delta: dt, region: district)
        if (!com.save(flush: true)) {
            println 'error ' + com.errors.allErrors.join(' \n')
            //each error is an instance of  org.springframework.validation.FieldError
        }
        println 'com ' + com.lat + ' ' + com.lng
        request.withFormat {
            '*' { respond instanceCommodity, [status: CREATED] }
        }
    }
}


@Validateable
class Marker {
    String barang
    String latitude
    String longitude
    Integer type
    Double price
    String nohp
    Date lastUpdated
}

class Message {
    String message
    boolean error = false
}