package com.pantau.core

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

import java.text.DecimalFormat

@Secured(['permitAll'])
class PageController {

    def index() {
        return [ranks: topPosters()]
    }

    def comodityInputGeoJSON() {
        def pattern = "##,###.##"
        def moneyform = new DecimalFormat(pattern)

        def results = [:]
        results['type'] = 'FeatureCollection'
        def items = []
        def maxDay = 30
        def maxCal = Calendar.instance
        maxCal.add(Calendar.DAY_OF_MONTH, maxDay * -1)
        println maxCal.time
        ComodityInput.findAll('from ComodityInput where dateCreated >= ?', [maxCal.time]).each {
            def item = [:]
            item['type'] = 'Feature'
            item['properties'] = [
                    'comodityType': it.comodityName?.comodityType?.name,
                    'comodityName': it.comodityName?.name,
                    'username': it.user?.nama,
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

    def topPosters() {
        def res = []
        def excludes = "'admin', 'ivan.sugiarto@gmail.com', 'wid.pangestu@gmail.com', 'rohmadijafar@qwer.com'"
        def query = """
            select count(c.id) as posts, u.nama as name
            from ComodityInput c join c.user u
            where u.username not in ( ${excludes} )
            and u.username like '%@%'
            group by u.nama
            order by posts desc
        """
        def result = ComodityInput.executeQuery(query, [max: 10])
        result?.each {
            res << [posts: it[0], name: it[1]]
        }
        return res
    }
}
