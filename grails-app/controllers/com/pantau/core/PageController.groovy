package com.pantau.core

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class PageController {

    def index() {}

    def comodityInputGeoJSON() {
        def results = [:]
        results['type'] = 'FeatureCollection'
        def items = []
        ComodityInput.list(params).each {
            def item = [:]
            item['type'] = 'Feature'
            item['properties'] = [
                    'comodityType': it.comodityName?.comodityType?.name,
                    'comodityName': it.comodityName?.name,
                    'username': it.user?.username,
                    'phone': it.user?.nohp,
                    'amount': it.amount
            ]
            item['geometry'] = ['type': "Point", 'coordinates': [it?.lng, it?.lat]]
            items.push(item)
        }

        results['features'] = items
        render results as JSON
    }
}
