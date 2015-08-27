package com.pantau.test

import org.bson.types.ObjectId

class Hotel {
    ObjectId id
    String name
    List location

    static mapping = {
        location geoIndex: '2d'
    }

    static constraints = {
    }
}
