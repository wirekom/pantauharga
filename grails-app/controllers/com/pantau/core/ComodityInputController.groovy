package com.pantau.core

import grails.validation.Validateable

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN'])
@Transactional(readOnly = true)
class ComodityInputController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static defaultAction = "map"
    def map(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List markers = new ArrayList();
        ComodityInput.list(params).each {
            def (latitude, longitude) = it.geoTag.tokenize(',')
            markers.add(new Marker(barang: it.comodityName.name, price: it.price, latitude: latitude, longitude: longitude))
        }
        respond ComodityInput.list(params), model:[comodityInputInstanceCount: ComodityInput.count(), markers: markers]
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ComodityInput.list(params), model:[comodityInputInstanceCount: ComodityInput.count()]
    }

    def show(ComodityInput comodityInputInstance) {
        respond comodityInputInstance
    }
	
	def calculateinflation (InflationCommandModel inflationCommandModelInstance) {
		println inflationCommandModelInstance.start
		println inflationCommandModelInstance.end
		List<ComodityInput> Comodities = ComodityInput.findAllByDateCreatedBetween(inflationCommandModelInstance.start,inflationCommandModelInstance.end)
		InflationCommandModel inflation = new InflationCommandModel()
		inflation.Comodities = Comodities
		inflationCommandModelInstance.inflation = inflation.countInflation()
		//println rate
		respond inflationCommandModelInstance
		
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

@Validateable
class Marker {
    String barang
    String latitude
    String longitude
    Double price
}
