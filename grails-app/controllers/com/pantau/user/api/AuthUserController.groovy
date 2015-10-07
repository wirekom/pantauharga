package com.pantau.user.api

import com.pantau.user.AuthRole
import com.pantau.user.AuthUser
import com.pantau.user.AuthUserAuthRole
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class AuthUserController {
    def springSecurityService

    static namespace = "api"

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AuthUser.list(params), model:[authUserInstanceCount: AuthUser.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
    def show(AuthUser authUserInstance) {
        if(authUserInstance != springSecurityService.currentUser){
            unauthorized()
            return
        }
        respond authUserInstance
    }

    @Secured(["permitAll"])
    def create() {
        respond new AuthUser(params)
    }

    @Secured(["permitAll"])
    @Transactional
    def save(AuthUser authUserInstance) {
        if (authUserInstance == null) {
            notFound()
            return
        }

        if (authUserInstance.hasErrors()) {
            respond authUserInstance.errors, view:'create'
            return
        }

        authUserInstance.save flush:true
        AuthUserAuthRole.create authUserInstance, AuthRole.findByAuthority('ROLE_TRUSTED'), true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'authUser.label', default: 'AuthUser'), authUserInstance.id])
                redirect authUserInstance
            }
            '*' { respond authUserInstance, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
    def edit(AuthUser authUserInstance) {
        respond authUserInstance
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
    @Transactional
    def update(AuthUser authUserInstance) {
        if (authUserInstance == null) {
            notFound()
            return
        }

        if(authUserInstance != springSecurityService.currentUser){
            unauthorized()
            return
        }

        if (authUserInstance.hasErrors()) {
            respond authUserInstance.errors, view:'edit'
            return
        }

        authUserInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AuthUser.label', default: 'AuthUser'), authUserInstance.id])
                redirect authUserInstance
            }
            '*'{ respond authUserInstance, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    @Transactional
    def delete(AuthUser authUserInstance) {

        if (authUserInstance == null) {
            notFound()
            return
        }

        if(authUserInstance != springSecurityService.currentUser){
            unauthorized()
            return
        }

        authUserInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AuthUser.label', default: 'AuthUser'), authUserInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'authUser.label', default: 'AuthUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    protected void unauthorized() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.unauthorized.message', args: [message(code: 'authUser.label', default: 'AuthUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: UNAUTHORIZED }
        }
    }
}
