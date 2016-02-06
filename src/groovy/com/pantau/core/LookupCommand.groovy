package com.pantau.core

/**
 * Created by widodo on 23/08/15.
 */
import grails.validation.Validateable;

@Validateable
class LookupCommand {
    String name
	Double radius
	Double lat
	Double lng
	String date
}
