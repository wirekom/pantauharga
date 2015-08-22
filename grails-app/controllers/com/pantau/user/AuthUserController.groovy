package com.pantau.user



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
@Transactional(readOnly = true)
class AuthUserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AuthUser.list(params), model:[authUserInstanceCount: AuthUser.count()]
    }

    def show(AuthUser authUserInstance) {
        respond authUserInstance
    }

    def create() {
        respond new AuthUser(params)
    }

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

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'authUser.label', default: 'AuthUser'), authUserInstance.id])
                redirect authUserInstance
            }
            '*' { respond authUserInstance, [status: CREATED] }
        }
    }

    def edit(AuthUser authUserInstance) {
        respond authUserInstance
    }

    @Transactional
    def update(AuthUser authUserInstance) {
        if (authUserInstance == null) {
            notFound()
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

    @Transactional
    def delete(AuthUser authUserInstance) {

        if (authUserInstance == null) {
            notFound()
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
}
