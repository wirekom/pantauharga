package com.pantau.core


import com.pantau.user.AuthUser

class ComodityInput {

    Double price
    Double lat
    Double lng
    Integer amount
    Double delta
    Date dateCreated
    Date lastUpdated

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
        delta nullable: true, blank: true
    }

    static mapping = {
        id generator: 'sequence', params: [sequence: 'seq_comodity_input_id']
    }

    String getLocation() {
        return "{lat: ${lat}, lng: ${lng}}"
    }
}
