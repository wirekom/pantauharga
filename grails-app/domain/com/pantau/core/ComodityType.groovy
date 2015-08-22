package com.pantau.core

class ComodityType {
	
	String name
	Integer weight
	static hasMany = [
		comodity: Comodity,
	]
    static constraints = {
		name blank:false
    }
}
