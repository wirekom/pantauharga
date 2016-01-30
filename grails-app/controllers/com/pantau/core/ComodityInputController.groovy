package com.pantau.core

import com.pantau.user.AuthUser
import org.hibernate.criterion.CriteriaSpecification

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
@Transactional(readOnly = true)
class ComodityInputController {
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def map(Integer max) {
        List<ComodityInput> ComodityInputs
        // params.max = Math.min(max ?: 10, 100)
        if (params.idparent != null) {
            ComodityInputs = ComodityInput.withCriteria {
                createAlias("region", "r", CriteriaSpecification.LEFT_JOIN)
                createAlias("region.parent", "p", CriteriaSpecification.LEFT_JOIN)
                and {
                    eq("p.id", params.idparent)
                }

            }
        } else {
            ComodityInputs = ComodityInput.list()

        }
        respond ComodityInputs, model: [comodityInputInstanceCount: ComodityInputs.size(), idparent: params.idparent]
    }

    def index(Integer max) {
        def currentUser = (AuthUser) springSecurityService.currentUser
        params.max = Math.min(max ?: 10, 100)
        def comodityInputs = ComodityInput.findAll('from ComodityInput where user = ? order by dateCreated desc', [currentUser], params)
        def comodityInputCount = ComodityInput.executeQuery('select count(id) from ComodityInput where user = ?', [currentUser])
        respond comodityInputs, model: [comodityInputInstanceCount: comodityInputCount.get(0)]
    }

    def download() {
        def currentUser = (AuthUser) springSecurityService.currentUser
        def comodityInputs = ComodityInput.findAll('from ComodityInput where user = ? order by dateCreated desc', [currentUser], [eager: 'user'])
        def result = ''
        result += ['Price', 'Lat', 'Lng', 'Date', 'Username'].join('\t') + '\n'
        comodityInputs?.each {
            result += [it.price, it.lat, it.lng, it.dateCreated, it.user.username ].join('\t') + '\n'
        }
        render (contentType: 'text/csv', text: result)
    }

    def show(ComodityInput comodityInputInstance) {
        respond comodityInputInstance
    }

    def calculateinflation(InflationCommandModel inflationCommandModelInstance) {
        println params.start
        println params.end
        List<ComodityInput> Comodities = ComodityInput.findAllByDateCreatedBetween(params.start, params.end)
        // println inflationCommandModelInstance.region.id
        if (inflationCommandModelInstance.region != null || inflationCommandModelInstance.region?.id > 0) {

            Comodities = ComodityInput.withCriteria {
                createAlias("region", "r", CriteriaSpecification.LEFT_JOIN)
                createAlias("region.parent", "p", CriteriaSpecification.LEFT_JOIN)
                and {
                    between("dateCreated", inflationCommandModelInstance.start, inflationCommandModelInstance.end)
                    eq("p.id", inflationCommandModelInstance.region.id)
                }

            }


        } else {

            Comodities = ComodityInput.withCriteria {
                //createAlias("region", "r", CriteriaSpecification.LEFT_JOIN)
                // createAlias("region.parent", "p", CriteriaSpecification.LEFT_JOIN)
                between("dateCreated", inflationCommandModelInstance.start, inflationCommandModelInstance.end)


            }
        }
        println Comodities.size()
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
