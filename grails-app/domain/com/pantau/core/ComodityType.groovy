package com.pantau.core

class ComodityType {
	
	String name
	static hasMany = [
		comodity: Comodity,
	]
    static constraints = {
		name blank:false
    }
}
