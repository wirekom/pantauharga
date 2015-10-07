package com.pantau.core

class ComodityType {

    String name
    Double weight

    static hasMany = [comodity: Comodity]

    static constraints = {
        name unique: true
    }

    static mapping = {
        id generator: 'sequence', params: [sequence: 'seq_comodity_type_id']
    }

    String toString() {
        name
    }

}
