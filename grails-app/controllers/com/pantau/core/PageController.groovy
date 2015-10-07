package com.pantau.core

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import java.text.DecimalFormat

@Secured(['permitAll'])
class PageController {

    def index() {}

    def comodityInputGeoJSON() {
        def pattern = "##,###.##"
        def moneyform = new DecimalFormat(pattern)

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
                    'price': moneyform.format(it.price),
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
