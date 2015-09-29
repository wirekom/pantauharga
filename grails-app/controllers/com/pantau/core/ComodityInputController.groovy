package com.pantau.core

import org.hibernate.criterion.CriteriaSpecification

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_TRUSTED', 'ROLE_UNTRUSTED', 'ROLE_SPECIAL'])
@Transactional(readOnly = true)
class ComodityInputController {

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
        params.max = Math.min(max ?: 10, 100)
        respond ComodityInput.list(params), model: [comodityInputInstanceCount: ComodityInput.count()]
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
