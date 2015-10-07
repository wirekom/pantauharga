package com.pantau.core

class Comodity {
    String name
    String sku

    static belongsTo = [comodityType: ComodityType]

    static hasMany = [comodityList: ComodityInput]

    static constraints = {
        name blank: false
    }

    static mapping = {
        id generator: 'sequence', params: [sequence: 'seq_comodity_id']
    }

    String toString() {
        name
    }
}
