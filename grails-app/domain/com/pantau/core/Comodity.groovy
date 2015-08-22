package com.pantau.core

class Comodity {
	String name
	Integer weight
	String sku
	static belongsTo = [
		comodityType: ComodityType
	]
	static hasMany = [
		comodityList: ComodityInput
	]
    static constraints = {
		name blank : false
		weight blank :false
    }
}
