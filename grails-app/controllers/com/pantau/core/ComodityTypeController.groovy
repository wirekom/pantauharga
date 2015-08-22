package com.pantau.core



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class ComodityTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ComodityType.list(params), model:[comodityTypeInstanceCount: ComodityType.count()]
    }

    def show(ComodityType comodityTypeInstance) {
        respond comodityTypeInstance
    }

    def create() {
        respond new ComodityType(params)
    }

    @Transactional
    def save(ComodityType comodityTypeInstance) {
        if (comodityTypeInstance == null) {
            notFound()
            return
        }

        if (comodityTypeInstance.hasErrors()) {
            respond comodityTypeInstance.errors, view:'create'
            return
        }

        comodityTypeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'comodityType.label', default: 'ComodityType'), comodityTypeInstance.id])
                redirect comodityTypeInstance
            }
            '*' { respond comodityTypeInstance, [status: CREATED] }
        }
    }

    def edit(ComodityType comodityTypeInstance) {
        respond comodityTypeInstance
    }

    @Transactional
    def update(ComodityType comodityTypeInstance) {
        if (comodityTypeInstance == null) {
            notFound()
            return
        }

        if (comodityTypeInstance.hasErrors()) {
            respond comodityTypeInstance.errors, view:'edit'
            return
        }

        comodityTypeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ComodityType.label', default: 'ComodityType'), comodityTypeInstance.id])
                redirect comodityTypeInstance
            }
            '*'{ respond comodityTypeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ComodityType comodityTypeInstance) {

        if (comodityTypeInstance == null) {
            notFound()
            return
        }

        comodityTypeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ComodityType.label', default: 'ComodityType'), comodityTypeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'comodityType.label', default: 'ComodityType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
