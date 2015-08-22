package com.pantau.user

class AuthUser {

    transient springSecurityService

    String username
    String password
    String email
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    List<AuthRole> roles

    static transients = ['springSecurityService']

    static constraints = {
        username blank: false, unique: true
        password blank: false
        email blank: false, unique: true, email: true
        roles bindable: true
    }

    static mapping = {
        password column: '`password`'
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