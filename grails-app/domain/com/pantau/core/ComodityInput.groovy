package com.pantau.core


import com.pantau.user.AuthUser

class ComodityInput {
	
	Double price
	Date inputDate
	String geoTag
	Double plus(ComodityInput other) {
		price + other.price
	}
	static belongsTo = [
		comodityName: Comodity,
		user : AuthUser,
		region : Region	
	]
    static constraints = {
		price blank:false
		inputDate blank:false
    }
}
