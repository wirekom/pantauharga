package com.pantau.core

class District {

    String name
    String description
    byte[] geometry
    String geoJSON
    Date dateCreated
    Date lastUpdated

    static belongsTo = [province: Province]

    static constraints = {
        description nullable: true, blank: true
        geometry nullable: true, blank: true
    }

    static mapping = {
        id generator: 'sequence', params: [sequence: 'seq_district_id']
        geometry sqlType: 'geometry(MultiPolygon,4326)'
        geoJSON formula: 'ST_AsGeoJSON(geometry)'
    }
}
