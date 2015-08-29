package com.pantau.test


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class EntryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Entry.list(params), model: [entryInstanceCount: Entry.count()]
    }

    def show(Entry entryInstance) {
        respond entryInstance
    }

    def create() {
        respond new Entry(params)
    }

    @Transactional
    def save(Entry entryInstance) {
        if (entryInstance == null) {
            notFound()
            return
        }

        if (entryInstance.hasErrors()) {
            respond entryInstance.errors, view: 'create'
            return
        }

        entryInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'entry.label', default: 'Entry'), entryInstance.id])
                redirect entryInstance
            }
            '*' { respond entryInstance, [status: CREATED] }
        }
    }

    def edit(Entry entryInstance) {
        respond entryInstance
    }

    @Transactional
    def update(Entry entryInstance) {
        if (entryInstance == null) {
            notFound()
            return
        }

        if (entryInstance.hasErrors()) {
            respond entryInstance.errors, view: 'edit'
            return
        }

        entryInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Entry.label', default: 'Entry'), entryInstance.id])
                redirect entryInstance
            }
            '*' { respond entryInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Entry entryInstance) {

        if (entryInstance == null) {
            notFound()
            return
        }

        entryInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Entry.label', default: 'Entry'), entryInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'entry.label', default: 'Entry'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
