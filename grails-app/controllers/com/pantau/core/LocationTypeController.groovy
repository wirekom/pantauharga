package com.pantau.core



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class LocationTypeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond LocationType.list(params), model:[locationTypeInstanceCount: LocationType.count()]
    }

    def show(LocationType locationTypeInstance) {
        respond locationTypeInstance
    }

    def create() {
        respond new LocationType(params)
    }

    @Transactional
    def save(LocationType locationTypeInstance) {
        if (locationTypeInstance == null) {
            notFound()
            return
        }

        if (locationTypeInstance.hasErrors()) {
            respond locationTypeInstance.errors, view:'create'
            return
        }

        locationTypeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'locationType.label', default: 'LocationType'), locationTypeInstance.id])
                redirect locationTypeInstance
            }
            '*' { respond locationTypeInstance, [status: CREATED] }
        }
    }

    def edit(LocationType locationTypeInstance) {
        respond locationTypeInstance
    }

    @Transactional
    def update(LocationType locationTypeInstance) {
        if (locationTypeInstance == null) {
            notFound()
            return
        }

        if (locationTypeInstance.hasErrors()) {
            respond locationTypeInstance.errors, view:'edit'
            return
        }

        locationTypeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'LocationType.label', default: 'LocationType'), locationTypeInstance.id])
                redirect locationTypeInstance
            }
            '*'{ respond locationTypeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(LocationType locationTypeInstance) {

        if (locationTypeInstance == null) {
            notFound()
            return
        }

        locationTypeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'LocationType.label', default: 'LocationType'), locationTypeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'locationType.label', default: 'LocationType'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
