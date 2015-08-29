package com.pantau.test



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class HotelController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Hotel.list(params), model:[hotelInstanceCount: Hotel.count()]
    }

    def show(Hotel hotelInstance) {
        respond hotelInstance
    }

    def create() {
        respond new Hotel(params)
    }

    @Transactional
    def save(Hotel hotelInstance) {
        if (hotelInstance == null) {
            notFound()
            return
        }

        if (hotelInstance.hasErrors()) {
            respond hotelInstance.errors, view:'create'
            return
        }

        hotelInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hotel.label', default: 'Hotel'), hotelInstance.id])
                redirect hotelInstance
            }
            '*' { respond hotelInstance, [status: CREATED] }
        }
    }

    def edit(Hotel hotelInstance) {
        respond hotelInstance
    }

    @Transactional
    def update(Hotel hotelInstance) {
        if (hotelInstance == null) {
            notFound()
            return
        }

        if (hotelInstance.hasErrors()) {
            respond hotelInstance.errors, view:'edit'
            return
        }

        hotelInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Hotel.label', default: 'Hotel'), hotelInstance.id])
                redirect hotelInstance
            }
            '*'{ respond hotelInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Hotel hotelInstance) {

        if (hotelInstance == null) {
            notFound()
            return
        }

        hotelInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Hotel.label', default: 'Hotel'), hotelInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hotel.label', default: 'Hotel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
