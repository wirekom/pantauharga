package com.pantau.core

import grails.plugins.rest.client.RestBuilder
import org.springframework.util.MultiValueMap
import org.springframework.util.LinkedMultiValueMap

class BigDataRequestModel {


    public getNearby(String lat, String lng, String rad) {
        /*def http = new HTTPBuilder("https://api.bigdataindonesia.com")
        headers.'Content-Type' = 'application/x-www-form-urlencoded'
        headers.'Content' = 'key=58b066d5064c98e75bb02adb853bc1ad'
         http.request(Method.POST, JSON) {
             url.path = '/poi/specific/json'
             //lat=-6.8731&lng=107.607&rad=0.5
             uri.query = [lat="-6.8731",lng="107.607", rad="0.6"]


         }*/

        def restBuilder = new RestBuilder()
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>()
        form.add("key", "58b066d5064c98e75bb02adb853bc1ad")
        def restRequest = restBuilder.post("https://api.bigdataindonesia.com/poi/nearby/json?lat="+lat+"&lng="+lng+"&rad="+rad) {
            accept("application/json")
            contentType("application/x-www-form-urlencoded")
            body(form)
        }
        return restRequest.json

    }


}
