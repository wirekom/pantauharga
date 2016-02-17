package com.pantau.user

/**
 * Created by widodo on 2/16/16.
 */
import grails.validation.Validateable;

@Validateable
class Register {
    String username
    String password
    String retypePassword
    String nama
    String email
    String ktp
    String nohp
    String alamat
    String kodepos
}
