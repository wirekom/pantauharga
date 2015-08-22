package com.pantau.core

import grails.validation.Validateable;

@Validateable
class PostComodityCommand {
	Integer id
	String geolocation
	String nohp
	Double harga
	Integer quantity
}
