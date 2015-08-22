package com.pantau.core

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class RegionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Region.list(params), model: [regionInstanceCount: Region.count()]
    }

    def show(Region regionInstance) {
        respond regionInstance
    }

    def create() {
        respond new Region(params)
    }

    @Transactional
    def save(Region regionInstance) {
        if (regionInstance == null) {
            notFound()
            return
        }

        if (regionInstance.hasErrors()) {
            respond regionInstance.errors, view: 'create'
            return
        }

        regionInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.id])
                redirect regionInstance
            }
            '*' { respond regionInstance, [status: CREATED] }
        }
    }

    def edit(Region regionInstance) {
        respond regionInstance
    }

    @Transactional
    def update(Region regionInstance) {
        if (regionInstance == null) {
            notFound()
            return
        }

        if (regionInstance.hasErrors()) {
            respond regionInstance.errors, view: 'edit'
            return
        }

        regionInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Region.label', default: 'Region'), regionInstance.id])
                redirect regionInstance
            }
            '*' { respond regionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Region regionInstance) {

        if (regionInstance == null) {
            notFound()
            return
        }

        regionInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Region.label', default: 'Region'), regionInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
