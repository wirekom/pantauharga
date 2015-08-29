package com.pantau.test

import grails.mongodb.geo.*
import org.bson.types.ObjectId

class Entry {
    ObjectId id
    Shape shape

    static mapping = {
        shape geoIndex: '2dsphere'
    }
    static constraints = {
    }
    static mapWith = "mongo"
}
