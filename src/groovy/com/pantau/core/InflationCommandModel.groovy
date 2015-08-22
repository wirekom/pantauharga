package com.pantau.core


class InflationCommandModel {
	LinkedList<Comodity> Comodities
	Date start
	Date end
	def countInflation () {
		Double proteinSum
		Double karbohidratSum
		Double lainlainSum
		proteinSum = Comodities.findAll{it.ComodityType.name == "Daging" && it.end < end && it.inputDate > start}.sum()
		karbohidratSum = Comodities.findAll{it.ComodityType.name == "Beras"}
		lainlainSum = Comodities.findAll{it.ComodityType.name == "Gula"}
		
	}
}
