package com.pantau.core

import com.pantau.user.AuthRole
import com.pantau.user.AuthUser
import com.pantau.user.AuthUserAuthRole
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Secured(['permitAll'])
@Transactional(readOnly = true)
class ApiController {
    def passwordEncoder
    static allowedMethods = [hargaall: "POST", comodity: "POST", input: "POST", register: "POST", login: "POST", hargadate: "GET"]

    def hargaall(LookupCommand lookup) {
        List markers = new ArrayList()
        def maxDay = 30
        def nowCal = Calendar.instance
        nowCal.add(Calendar.DAY_OF_MONTH, (maxDay * -1))
        println 'date ' + nowCal.time
        String query = "from ComodityInput " +
                "where sqrt(POWER (69.1 * (lat - :ulatitude),2) + POWER (69.1 * (lng - :ulongitude) * cos(:ulatitude / 57.3),2)) * 1.609344 < :udistance " +
                "and comodityName.name = :ucomodityName " +
                "and dateCreated >= :fewDays  " +
                "ORDER BY dateCreated desc"
        ComodityInput.findAll (query,[ulatitude: lookup.lat, ulongitude:lookup.lng, udistance:lookup.radius, ucomodityName:lookup.name, fewDays:nowCal.time], [cache: true])
                .unique {
            it?.lat
            it?.lng
        }.each {
            def nohp = 0
            if (it?.type != null || it?.type == 0) {
                if (it?.user?.enabled) {
                    nohp = it?.user?.nohp
                }

            }
            markers.add(new Marker(barang: it?.comodityName?.name, price: it?.price, latitude: it?.lat, longitude: it?.lng, nohp: nohp, lastUpdated: it?.lastUpdated, type: it?.type, description: it?.description))
        }
        render markers as JSON
    }

    def comodityall() {
        render Comodity.list([sort: 'name', order: 'asc', cache: true]) as JSON
    }

    def comodity(LookupCommand lookup) {
//        println 'test ' + lookup.name
//        println "%${lookup.name}%"
        render Comodity.where {
            name == lookup.name
        }.list([sort: 'name', order: 'asc', max: 10, cache: true]) as JSON
    }

    @Transactional
    def input(PostComodityCommand instanceCommodity) {
//        println instanceCommodity.id
//        println instanceCommodity.harga
//
//        println instanceCommodity.nohp
//        println instanceCommodity.quantity
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
        def com = new ComodityInput(user: member, comodityName: comodity, price: instanceCommodity?.harga, lat: instanceCommodity?.lat, lng: instanceCommodity?.lng, amount: instanceCommodity?.quantity, type:type, delta: dt, region: district, description: instanceCommodity?.description)
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
//        println userRegister.username
        def res
        def user = AuthUser.findByNohpOrUsername(userRegister.nohp, userRegister.username?.toLowerCase())
        println 'userRegister >>>>>>>>> ' + userRegister.properties
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
            if (userRegister.nohp.equals(user.nohp)) {
                res = new Message(message: 'No Handphone sudah digunakan', error: true)
            } else if (userRegister.username.equalsIgnoreCase(user.username)) {
                res = new Message(message: 'Email sudah digunakan', error: true)
            }
            println 'user exists: ' + (user != null)
            request.withFormat {
                '*' { respond res, [status: FORBIDDEN] }
            }
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

    def hargadate() {
        def markers = new ArrayList()
        def calEnd = Calendar.instance
        try {
            def date = new Date().parse('dd/M/yyyy', params.date)
            calEnd.setTime(date)
            calEnd.add(Calendar.DAY_OF_MONTH, 1)
            def query = "from ComodityInput where lastUpdated > :startDate and lastUpdated <= :endDate"
            def parameters = [startDate: date, endDate: calEnd.time]
            if (params.name) {
                query += "  and comodityName.name = :comodityName"
                parameters.comodityName = params.name
            }
            query += " order by comodityName.id"
            ComodityInput.findAll(query, parameters, [cache: true]).each {
                def nohp = 0
                if (it.type != null || it.type == 0) {
                    if (it.user.enabled) {
                        nohp = it.user.nohp
                    }

                }
                markers.add(new Marker(barang: it?.comodityName?.name, price: it?.price, latitude: it?.lat, longitude: it?.lng, nohp: nohp, lastUpdated: it?.lastUpdated, type: it?.type, description: it?.description))
            }
            render markers as JSON
        } catch (Exception e) {
            log.error(e.message, e)
            render status: BAD_REQUEST
        }
    }

}