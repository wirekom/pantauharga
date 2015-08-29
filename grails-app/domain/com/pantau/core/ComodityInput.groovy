package com.pantau.core


import com.pantau.user.AuthUser

class ComodityInput {

    Double price
	Double delta
    String lat
	String lng
    Date dateCreated
    Date lastUpdated
	Integer amount
    Double plus(ComodityInput other) {
        delta + other.delta
    }
    static belongsTo = [
            comodityName: Comodity,
            user        : AuthUser,
            region      : Region
    ]

    static constraints = {
        price blank: false
        user nullable: true, blank: true
        region nullable: true, blank: true
        amount nullable: true, blank: true
    }

    String getLocation() {
        //def (latitude, longitude) = geoTag.tokenize(',');
        return "{lat: ${lat}, lng: ${lng}}"
    }
}
