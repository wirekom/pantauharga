package com.pantau.test

import grails.mongodb.geo.*
import org.bson.types.ObjectId

class Restaurant {
    ObjectId id
    String name
    Point location

    static mapping = {
        location geoIndex: '2dsphere'
    }
    static constraints = {
    }
    static mapWith = "mongo"
}
