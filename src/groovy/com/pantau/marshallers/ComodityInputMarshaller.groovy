package com.pantau.marshallers

import com.pantau.core.ComodityInput
import grails.converters.JSON

/**
 * Created by widodo on 10/8/15.
 */
class ComodityInputMarshaller {
    void register() {
        JSON.registerObjectMarshaller(ComodityInput) { ComodityInput obj ->

            return [
                    id          : obj.id,
                    price       : obj.price,
                    amount      : obj.amount,
                    delta       : obj.delta,
                    lat         : obj.lat,
                    lng         : obj.lng,
                    username    : obj.user.username,
                    nohp        : obj.user.nohp,
                    comodityName: obj.comodityName.name,
                    comodityType: obj.comodityName.comodityType.name
            ]
        }
    }
}
