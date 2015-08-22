package com.pantau.core



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.rest.token.generation.TokenGenerator
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AuthenticationTokenController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AuthenticationToken.list(params), model:[authenticationTokenInstanceCount: AuthenticationToken.count()]
    }

    def show(AuthenticationToken authenticationTokenInstance) {
        respond authenticationTokenInstance
    }

    def create() {
        respond new AuthenticationToken(params)
    }

    @Transactional
    def save(AuthenticationToken authenticationTokenInstance) {
        if (authenticationTokenInstance == null) {
            notFound()
            return
        }

        if (authenticationTokenInstance.hasErrors()) {
            respond authenticationTokenInstance.errors, view:'create'
            return
        }

        authenticationTokenInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'authenticationToken.label', default: 'AuthenticationToken'), authenticationTokenInstance.id])
                redirect authenticationTokenInstance
            }
            '*' { respond authenticationTokenInstance, [status: CREATED] }
        }
    }

    def edit(AuthenticationToken authenticationTokenInstance) {
        respond authenticationTokenInstance
    }

    @Transactional
    def update(AuthenticationToken authenticationTokenInstance) {
        if (authenticationTokenInstance == null) {
            notFound()
            return
        }

        if (authenticationTokenInstance.hasErrors()) {
            respond authenticationTokenInstance.errors, view:'edit'
            return
        }

        authenticationTokenInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'AuthenticationToken.label', default: 'AuthenticationToken'), authenticationTokenInstance.id])
                redirect authenticationTokenInstance
            }
            '*'{ respond authenticationTokenInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(AuthenticationToken authenticationTokenInstance) {

        if (authenticationTokenInstance == null) {
            notFound()
            return
        }

        authenticationTokenInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'AuthenticationToken.label', default: 'AuthenticationToken'), authenticationTokenInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'authenticationToken.label', default: 'AuthenticationToken'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
