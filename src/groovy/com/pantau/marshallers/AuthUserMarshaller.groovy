package com.pantau.marshallers


import com.pantau.user.AuthUser
import grails.converters.JSON

/**
 * Created by widodo on 10/8/15.
 */
class AuthUserMarshaller {
    void register() {
        JSON.registerObjectMarshaller(AuthUser) { AuthUser obj ->
            return [
                    id      : obj.id,
                    username: obj.username,
                    email   : obj.email,
                    nama    : obj.nama,
                    alamat  : obj.alamat,
                    nohp    : obj.nohp,
                    ktp     : obj.ktp,
                    kodepos : obj.kodepos
            ]
        }
    }
}
