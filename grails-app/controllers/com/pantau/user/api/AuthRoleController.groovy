package com.pantau.user.api

import com.pantau.user.AuthRole
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class AuthRoleController {

    static namespace = "api"

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AuthRole.list(params), model: [authRoleInstanceCount: AuthRole.count()]
    }

    def show(AuthRole authRoleInstance) {
        respond authRoleInstance
    }

    def create() {
        respond new AuthRole(params)
    }

    @Transactional
    def save(AuthRole authRoleInstance) {
        if (authRoleInstance == null) {
            notFound()
            return
        }

        if (authRoleInstance.hasErrors()) {
            respond authRoleInstance.errors, view: 'create'
            return
        }

        authRoleInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'authRole.label', default: 'AuthRole'), authRoleInstance.id])
                redirect authRoleInstance
            }
            '*' { respond authRoleInstance, [status: CREATED] }
        }
    }

    def edit(AuthRole authRoleInstance) {
        respond authRoleInstance
    }

    @Transactional
    def update(AuthRole authRoleInstance) {
        if (authRoleInstance == null) {
            notFound()
            return
        }

        if (authRoleInstance.hasErrors()) {
            respond authRoleInstance.errors, view: 'edit'
            return
        }

        authRoleInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AuthRole.label', default: 'AuthRole'), authRoleInstance.id])
                redirect authRoleInstance
            }
            '*' { respond authRoleInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AuthRole authRoleInstance) {

        if (authRoleInstance == null) {
            notFound()
            return
        }

        authRoleInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AuthRole.label', default: 'AuthRole'), authRoleInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'authRole.label', default: 'AuthRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
