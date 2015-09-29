package com.pantau.core

import com.pantau.user.AuthRole
import com.pantau.user.AuthUser
import com.pantau.user.AuthUserAuthRole
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
            ComodityInput.where {

                'in'('comodityName', comodities)

                lt('distance', lookup.radius * 1000)
                // between('lat', lookup.lat-radius, lookup.lat+radius)
                //between('lng', lookup.lng-radius, lookup.lng+radius)

                order('dateCreated', 'desc'
                )
            }.list([sort: 'dateCreated', order: 'desc']).unique {
                it.lat
                it.lng
            }.each {
                def nohp = 0
                if (it.amount > 0) {
                    if (it.user.enabled == true) {
                        nohp = it.user.nohp
                    }

                }
                markers.add(new Marker(barang: it.comodityName.name, price: it.price, latitude: it.lat, longitude: it.lng, nohp: nohp))
            }

        }
        respond markers
    }

    def comodityall() {
        respond Comodity.list([sort: 'name', order: 'asc', max: 10])
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
        def member = AuthUser.findByNohp(instanceCommodity.nohp) ?: new AuthUser(
                username: instanceCommodity.nohp,
                password: instanceCommodity.nohp,
                nohp: instanceCommodity.nohp,
                enabled: true).save(flush: true)
        println 'user ' + member.username

        def roleUser = AuthRole.findByAuthority('ROLE_USER')
        if (!AuthUserAuthRole.exists(member.id, roleUser.id)) {
            AuthUserAuthRole.create member, roleUser, true
        }

       // def last = ComodityInput.list([max: 1, sort: 'dateCreated', order: 'asc'])
        Double dt = 0
        //if (!last.isEmpty()) {
        //    dt = instanceCommodity.harga - last.first().price
       // }

        //def (lat, lng) = instanceCommodity.geolocation.tokenize(',')
       /// BigDataRequestModel big = new BigDataRequestModel()
       // def jsonSlurper = new JsonSlurper()
       // // def json = jsonSlurper.parseText(big.getNearby(Double.toString(instanceCommodity.lat), Double.toString(instanceCommodity.lng), '10'))
       // def json = big.getNearby(Double.toString(instanceCommodity.lat), Double.toString(instanceCommodity.lng), '10')
       // def search
       // def apa = json.result

      //  println "sjon" + json
      /*  for (def ret : json.result) {
            println "ret" + ret
            //  def apa = jsonSlurper.parseText(ret.toString())
            // println apa

            if (ret.masterclass == "Commercial") {
                if (Double.parseDouble(ret?.latitude?.toString()))
                    search = ret
                break
            }
        }*/
       // println "search" + search.province
      //  Region prop = Region.findByName(search.province)
        /*
        if (prop?.name == null) {
            prop = new Region(name: search.province, geolocation: search.latitude + "," + search.longitude).save(flush: true)
        }
        println prop
        Region district = Region.findByName(search.district)
        if (district?.name == null) {
            district = new Region(name: search.district, geolocation: search.latitude + "," + search.longitude)
            district.setParent(prop)
            district.save(flush: true)
        }

        Location loc = Location.findByName(search.name) ?: new Location(
                name: search.name,
                lat: search.latitude,
                lng: search.longitude
        ).save(flush: true)
        if (instanceCommodity.quantity == 0) {
            instanceCommodity.lat = Double.parseDouble(search.latitude)
            instanceCommodity.lng = Double.parseDouble(search.longitude)

        }*/
        //akal2an
        Region district = Region.find("FROM Region ORDER BY id")

        def com = new ComodityInput(user: member, comodityName: comodity, price: instanceCommodity.harga, lat: instanceCommodity.lat, lng: instanceCommodity.lng, amount: instanceCommodity.quantity, delta: dt, region: district)
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
        def user = AuthUser.findByNohp(userRegister.nohp)
        println 'user' + user
        println 'userRegister >>>>>>>>> ' + userRegister.properties
        if (user == null) {
            user = new AuthUser(userRegister.properties)
        } else {
            user.properties = userRegister.properties;
        }

        user.save(flush: false, failOnError: true)
        AuthRole authRole = AuthRole.findByAuthority('ROLE_TRUSTED')
        println 'User >>>>>>>>> ' + user
        println 'AuthRole >>>>>>>>> ' + authRole

        if (!AuthUserAuthRole.exists(user.id, authRole.id)) {
            AuthUserAuthRole.create user, authRole, true
        }

        request.withFormat {
            '*' { respond userRegister, [status: CREATED] }
        }
    }

    @Transactional
    def login(UserLoginCommand userLogin) {

        def user = AuthUser.findByUsername(userLogin.username)
        if (user) {
            if (passwordEncoder.isPasswordValid(user.password, userLogin.password, null)) {
                request.withFormat {
                    '*' { respond userLogin, [status: OK] }
                }
            }
        }
        request.withFormat {
            '*' { respond userLogin, [status: UNAUTHORIZED] }
        }
    }
}


@Validateable
class Marker {
    String barang
    String latitude
    String longitude
    Double price
    String nohp
}
