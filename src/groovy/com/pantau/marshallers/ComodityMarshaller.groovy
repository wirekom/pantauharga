package com.pantau.marshallers

import com.pantau.core.Comodity
import grails.converters.JSON

/**
 * Created by widodo on 10/8/15.
 */


class ComodityMarshaller {
    void register() {
        JSON.registerObjectMarshaller(Comodity) { Comodity obj ->
            return [
                    id     : obj.id,
                    name   : obj.name,
                    sku    : obj.sku,
                    type   : obj.comodityType.name,
                    type_id: obj.comodityType.id
            ]
        }
    }
}
