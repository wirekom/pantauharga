package com.pantau.marshallers

import com.pantau.core.ComodityType
import grails.converters.JSON

/**
 * Created by widodo on 10/8/15.
 */
class ComodityTypeMarshaller {
    void register() {
        JSON.registerObjectMarshaller(ComodityType) { ComodityType obj ->

            return [
                    id        : obj.id,
                    name      : obj.name,
                    weight    : obj.weight,
                    comodities: obj.comodity
            ]
        }
    }
}
