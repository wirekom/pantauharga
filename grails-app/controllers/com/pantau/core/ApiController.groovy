package com.pantau.core

import com.pantau.user.AuthRole
import com.pantau.user.AuthUser
import com.pantau.user.AuthUserAuthRole
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
@Transactional(readOnly = true)
class ApiController {
    def passwordEncoder

    def comodity(LookupCommand lookup){
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
        println instanceCommodity.geolocation
        println instanceCommodity.nohp
        println instanceCommodity.quantity
        def comodity = Comodity.get(instanceCommodity.id)
        if(!comodity){
            request.withFormat {
                '*' { render status: NO_CONTENT }
            }
            return
        }
        def member = AuthUser.findByNohp(instanceCommodity.nohp)?:new AuthUser(
                username: instanceCommodity.nohp,
                password: instanceCommodity.nohp,
                nohp: instanceCommodity.nohp,
                enabled: true).save(flush: true)
        println 'user ' + member.username
        AuthUserAuthRole.create member, AuthRole.findByAuthority('ROLE_USER'), true
        def last = ComodityInput.list([max: 1, sort: 'dateCreated', order: 'desc']).first()
        Double dt = last?(instanceCommodity.harga - last.price):instanceCommodity.harga
        println 'harga ' + last.price
        def com = new ComodityInput(user: member, comodityName: comodity, price: instanceCommodity.harga, geoTag: instanceCommodity.geolocation, amount: instanceCommodity.quantity, delta: dt)
        if (!com.save(flush: true)) {
            println 'error ' +  com.errors.allErrors.join(' \n') //each error is an instance of  org.springframework.validation.FieldError
        }
        println 'com ' + com.geoTag
        request.withFormat {
            '*' { respond instanceCommodity, [status: CREATED] }
        }
    }

    @Transactional
    def register(UserRegisterCommad userRegister) {
        def user = new AuthUser(userRegister.properties)
        user.save(flush: true)
        AuthUserAuthRole.create user, AuthRole.findByAuthority('ROLE_TRUSTED'), true

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
