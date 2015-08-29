package com.pantau.core

import grails.validation.Validateable;

@Validateable
class LocationConsumerCommandModel {
    String masterClass
    String subClass
    String name
    String longitude
    String latitude
    String province
    String district
    String subdistrict
    String village
    String address
    String telephone
    String website
}
