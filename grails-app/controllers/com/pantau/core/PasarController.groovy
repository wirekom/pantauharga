package com.pantau.core

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class PasarController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Pasar.list(params), model: [pasarInstanceCount: Pasar.count()]
    }

    def show(Pasar pasarInstance) {
        respond pasarInstance
    }

    def create() {
        respond new Pasar(params)
    }

    @Transactional
    def save(Pasar pasarInstance) {
        if (pasarInstance == null) {
            notFound()
            return
        }

        if (pasarInstance.hasErrors()) {
            respond pasarInstance.errors, view: 'create'
            return
        }

        pasarInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pasar.label', default: 'Pasar'), pasarInstance.id])
                redirect pasarInstance
            }
            '*' { respond pasarInstance, [status: CREATED] }
        }
    }
    @Transactional
    def importPasar (Integer max) {
       // params.max = Math.min(max ?: 10, 100)
        new PasarExcelImporter(new FileInputStream(applicationContext.getResource("/uploads/database_pasar_dan_gudang.xlsx").getFile()))
        redirect action: 'index', controller: 'pasar'
    }
    def edit(Pasar pasarInstance) {
        respond pasarInstance
    }

    @Transactional
    def update(Pasar pasarInstance) {
        if (pasarInstance == null) {
            notFound()
            return
        }

        if (pasarInstance.hasErrors()) {
            respond pasarInstance.errors, view: 'edit'
            return
        }

        pasarInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Pasar.label', default: 'Pasar'), pasarInstance.id])
                redirect pasarInstance
            }
            '*' { respond pasarInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Pasar pasarInstance) {

        if (pasarInstance == null) {
            notFound()
            return
        }

        pasarInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Pasar.label', default: 'Pasar'), pasarInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pasar.label', default: 'Pasar'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
