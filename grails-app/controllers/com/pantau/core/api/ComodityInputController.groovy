package com.pantau.core.api

import com.pantau.core.ComodityInput
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class ComodityInputController {
    def springSecurityService

    static namespace = "api"

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ComodityInput.list(params), model: [comodityInputInstanceCount: ComodityInput.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
    def show(ComodityInput comodityInputInstance) {
        respond comodityInputInstance
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
    def create() {
        respond new ComodityInput(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
    @Transactional
    def save(ComodityInput comodityInputInstance) {
        if (comodityInputInstance == null) {
            notFound()
            return
        }

        if (comodityInputInstance.hasErrors()) {
            respond comodityInputInstance.errors, view: 'create'
            return
        }
        comodityInputInstance.user = springSecurityService.currentUser
        comodityInputInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'comodityInput.label', default: 'ComodityInput'), comodityInputInstance.id])
                redirect comodityInputInstance
            }
            '*' { respond comodityInputInstance, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
    def edit(ComodityInput comodityInputInstance) {
        respond comodityInputInstance
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
    @Transactional
    def update(ComodityInput comodityInputInstance) {
        if (comodityInputInstance == null) {
            notFound()
            return
        }

        if (comodityInputInstance.hasErrors()) {
            respond comodityInputInstance.errors, view: 'edit'
            return
        }

        comodityInputInstance.user = springSecurityService.currentUser
        comodityInputInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ComodityInput.label', default: 'ComodityInput'), comodityInputInstance.id])
                redirect comodityInputInstance
            }
            '*' { respond comodityInputInstance, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    @Transactional
    def delete(ComodityInput comodityInputInstance) {

        if (comodityInputInstance == null) {
            notFound()
            return
        }

        comodityInputInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ComodityInput.label', default: 'ComodityInput'), comodityInputInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'comodityInput.label', default: 'ComodityInput'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
