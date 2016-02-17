package com.pantau.core

import grails.validation.Validateable;

@Validateable
class PostComodityCommand {
	Integer id
	Double lat
	Double lng
	String nohp
	Double harga
	Integer quantity
	String description

	static constraints = {
		description nullable: true, blank: true
	}
}
