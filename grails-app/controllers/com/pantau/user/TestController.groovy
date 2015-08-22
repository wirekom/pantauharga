package com.pantau.user

import com.pantau.core.BigDataRequestModel
import grails.plugins.rest.client.RestBuilder
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class TestController {

    def index() {
        def test = new BigDataRequestModel()
        println test.getNearby('-6.2087634', '106.8455', '10')
    }
}
