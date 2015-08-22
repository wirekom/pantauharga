package com.pantau.core


import com.pantau.user.AuthUser

class ComodityInput {

    Double price
	Double delta
    Date inputDate
    String geoTag
	Integer quantity

    Double plus(ComodityInput other) {
        price + other.price
    }
    static belongsTo = [
            comodityName: Comodity,
            user        : AuthUser,
            region      : Region,
			location      : Location
    ]

    static constraints = {
        price blank: false
        inputDate blank: false
        user nullable: true, blank: true
        region nullable: true, blank: true
    }
}
