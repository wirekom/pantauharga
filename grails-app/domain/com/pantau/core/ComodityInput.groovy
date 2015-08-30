package com.pantau.core


import com.pantau.user.AuthUser

class ComodityInput {

    Double price
	Double delta
    Double lat
	Double lng
    Date dateCreated
    Date lastUpdated
	Integer amount
    Double distance
    Double plus(ComodityInput other) {
        delta + other.delta
    }
    static belongsTo = [
            comodityName: Comodity,
            user        : AuthUser,
            region      : Region
    ]
    static mapping = {
        distance formula:"( 6371 * acos( cos( radians(37) ) * cos( radians( lat ) ) * cos( radians( lng ) - radians(-122) ) + sin( radians(37) ) * sin( radians( lat ) ) ) )"
    }

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
