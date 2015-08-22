package com.pantau.core
//import grails.plugins.rest.client.RestBuilder
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.ContentType
import groovyx.net.http.Method
import groovyx.net.http.RESTClient
class BigDataRequestModel {

	
	public getNearby (String lat, String lng, String rad) {
		/*def http = new HTTPBuilder("https://api.bigdataindonesia.com")
		headers.'Content-Type' = 'application/x-www-form-urlencoded'
		headers.'Content' = 'key=58b066d5064c98e75bb02adb853bc1ad'
		 http.request(Method.POST, JSON) {
			 url.path = '/poi/specific/json'
			 //lat=-6.8731&lng=107.607&rad=0.5
			 uri.query = [lat="-6.8731",lng="107.607", rad="0.6"]
			 
			
		 }*/
		
			/*def restBuilder = new RestBuilder()
			def restRequest = restBuilder.post("https://api.bigdataindonesia.com/poi/specific/json?"){
				contentType "application/x-www-form-urlencoded"
				
				//content "key=58b066d5064c98e75bb02adb853bc1ad"
				body: [
					key:"58b066d5064c98e75bb02adb853bc1ad",
					lat: lat,
					lng: lng,
					rad: rad
				]
			}*/
		
			try {
				def ret = null
				def http = new HTTPBuilder("https://api.bigdataindonesia.com/poi/nearby/json?lat=-6.2087634&lng=106.84559899999999&rad=0.03")
				def query= [
					key:"58b066d5064c98e75bb02adb853bc1ad",
					
				]
				// perform a POST request, expecting TEXT response
				http.request(Method.POST, ContentType.TEXT) {
					uri.path = "/poi/nearby/output"
					uri.query = query
					//headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'
	
					// response handler for a success response code
					response.success = { resp, reader ->
						println "response status: ${resp.statusLine}"
						println 'Headers: -----------'
						resp.headers.each { h ->
							println " ${h.name} : ${h.value}"
						}
	
						ret = reader.getText()
	
						println 'Response data: -----'
						println ret
						println '--------------------'
					}
				}
				return ret
	
			} catch (groovyx.net.http.HttpResponseException ex) {
				ex.printStackTrace()
				return null
			} catch (java.net.ConnectException ex) {
				ex.printStackTrace()
				return null
			}
			//return restRequest.json
		
		}
		
	
}
