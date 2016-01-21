package com.pantau.user

class AuthUser {

    transient springSecurityService

    String username
    String password
    String nama
    String email
    String ktp
    String nohp
    String alamat
    String kodepos
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false
    List<AuthRole> roles

    static transients = ['springSecurityService', 'roles']

    static constraints = {
        username blank: false, unique: true
        password blank: false
        nama nullable: true, blank: false
        ktp nullable: true, blank: true
        email nullable: true, blank: true, unique: true, email: true
        nohp unique: true
        alamat nullable: true, blank: true
        kodepos nullable: true, blank: true
        roles bindable: true
    }

    Set<AuthRole> getAuthorities() {
        AuthUserAuthRole.findAllByAuthUser(this).collect { it.authRole }
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }

    String toString() {
        username
    }
}
