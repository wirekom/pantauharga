package com.pantau.core

import grails.plugin.springsecurity.rest.token.AccessToken
import grails.plugin.springsecurity.rest.token.generation.TokenGenerator
import org.springframework.security.core.userdetails.UserDetails

class AuthenticationToken implements TokenGenerator{
    String tokenValue
    String username

    static mapping = {
        version false
    }
    static constraints = {
    }

    @Override
    AccessToken generateAccessToken(UserDetails principal) {
    }

    @Override
    AccessToken generateAccessToken(UserDetails principal, Integer expiration) {
        return null
    }
}
