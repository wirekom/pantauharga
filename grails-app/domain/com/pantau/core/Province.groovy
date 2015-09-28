package com.pantau.core

class Province {
    String name
    String description
    byte[] geometry
    String geoJSON
    Date dateCreated
    Date lastUpdated

    static hasMany = [district: District]

    static constraints = {
        description nullable: true, blank: true
        geometry nullable: true, blank: true
    }

    static mapping = {
        id generator:'sequence', params:[sequence:'seq_province_id']
        geometry sqlType: 'geometry(MultiPolygon,4326)'
        geoJSON formula: 'ST_AsGeoJSON(geometry)'
    }

}
