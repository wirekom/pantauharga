package com.pantau.user

/**
 * Created by widodo on 2/16/16.
 */
import grails.validation.Validateable;

@Validateable
class ForgotPassword {
    String email
    static constraints = {
        email blank: false, minSize: 6, validator: { val, obj ->
            if(AuthUser.findByEmail(val) == null){
                ['userNotFound', val]
            }
        }
    }

}
