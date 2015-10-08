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
}
