package com.pantau.marshallers

import com.pantau.user.AuthRole
import grails.converters.JSON

/**
 * Created by widodo on 10/8/15.
 */
class AuthRoleMarshaller {
    void register() {
        JSON.registerObjectMarshaller(AuthRole) { AuthRole obj ->
            return [
                    id       : obj.id,
                    authority: obj.authority,
            ]
        }
    }
}
