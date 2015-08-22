package com.pantau.core



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class ComodityInputController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ComodityInput.list(params), model:[comodityInputInstanceCount: ComodityInput.count()]
    }

    def show(ComodityInput comodityInputInstance) {
        respond comodityInputInstance
    }

    def create() {
        respond new ComodityInput(params)
    }

    @Transactional
    def save(ComodityInput comodityInputInstance) {
        if (comodityInputInstance == null) {
            notFound()
            return
        }

        if (comodityInputInstance.hasErrors()) {
            respond comodityInputInstance.errors, view:'create'
            return
        }

        comodityInputInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'comodityInput.label', default: 'ComodityInput'), comodityInputInstance.id])
                redirect comodityInputInstance
            }
            '*' { respond comodityInputInstance, [status: CREATED] }
        }
    }

    def edit(ComodityInput comodityInputInstance) {
        respond comodityInputInstance
    }

    @Transactional
    def update(ComodityInput comodityInputInstance) {
        if (comodityInputInstance == null) {
            notFound()
            return
        }

        if (comodityInputInstance.hasErrors()) {
            respond comodityInputInstance.errors, view:'edit'
            return
        }

        comodityInputInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ComodityInput.label', default: 'ComodityInput'), comodityInputInstance.id])
                redirect comodityInputInstance
            }
            '*'{ respond comodityInputInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ComodityInput comodityInputInstance) {

        if (comodityInputInstance == null) {
            notFound()
            return
        }

        comodityInputInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ComodityInput.label', default: 'ComodityInput'), comodityInputInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'comodityInput.label', default: 'ComodityInput'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
