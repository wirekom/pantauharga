package com.pantau.user

class AuthenticationToken {
    String tokenValue
    String username
    Date dateCreated
    Date lastUpdated

    static mapping = {
        version false
    }

    static constraints = {
    }
}
