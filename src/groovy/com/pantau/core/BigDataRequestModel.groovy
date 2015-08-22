package com.pantau.core

import grails.plugins.rest.client.RestBuilder

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
        def restRequest = restBuilder.post("https://api.bigdataindonesia.com/poi/nearby/json?lat=-6.2087634&lng=106.84559899999999&rad=0.03") {
            contentType: "application/x-www-form-urlencoded"
            body: "key=58b066d5064c98e75bb02adb853bc1ad"

        }
        return restRequest.json

    }


}
