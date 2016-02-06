package com.pantau.core

class ComodityType {
	
	String name
	Double weight
	static hasMany = [
		comodity: Comodity,
	]
    static constraints = {
		name unique: true
    }

	String toString(){
		name
	}

	static mapping = {
		cache true
	}
}
