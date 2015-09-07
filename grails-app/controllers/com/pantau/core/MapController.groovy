package com.pantau.core

import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class MapController {

    def index() {}

    def reverse(String location) {
        render model: ['location' : location], view: 'reverse'
    }
}
