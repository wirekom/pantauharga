package com.pantau.core

class Region {
	String name
	String geolocation
	Double lat
	Double lng

	static belongsTo = [
            parent: Region
    ]
	static hasMany = [
		children: Region,
	]
	String toString(){
		return this.name

	}
    static constraints = {
		name blank: false
		geolocation blank: false
		parent nullable: true
		lat nullable: true
		lng nullable: true
    }
	static mapping = {
		cache true
	}
}
