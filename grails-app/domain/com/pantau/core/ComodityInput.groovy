package com.pantau.core


import com.pantau.user.AuthUser

class ComodityInput {

    Double price
    String geoTag
    Date dateCreated
    Date lastUpdated

    Double plus(ComodityInput other) {
        price + other.price
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
    }
}
