package com.pantau.core

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
@Transactional(readOnly = true)
class ApiController {

    @Transactional
    def input(MobilePostComoditiesCommandObjects instanceCommodity) {
        println instanceCommodity.idBarang
        println instanceCommodity.harga
        println instanceCommodity.geolocation

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'comodityInput.label', default: 'ComodityInput'), instanceCommodity.idBarang])
                redirect instanceCommodity
            }
            '*' { respond instanceCommodity, [status: CREATED] }
        }
    }
}
