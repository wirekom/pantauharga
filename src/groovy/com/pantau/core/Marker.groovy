package com.pantau.core

import grails.validation.Validateable

/**
 * Created by widodo on 2/17/16.
 */
@Validateable
class Marker {
    String barang
    String latitude
    String longitude
    Integer type
    Double price
    String nohp
    Date lastUpdated
    String description
}
