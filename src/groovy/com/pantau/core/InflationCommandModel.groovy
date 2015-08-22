package com.pantau.core


class InflationCommandModel {
	//gw belom test, comodities itu disini semua comodityinput yang ketarik dari awal bulan sampai akhir bulan
	LinkedList<ComodityInput> Comodities
	Date start
	Date end
	def countInflation () {
		Double proteinSum
		Double karbohidratSum
		Double lainlainSum
		proteinSum = Comodities.findAll{it.comodityName.ComodityType.name == "Daging" }.sum()/Comodities.findAll{it.comodityName.ComodityType.name == "Daging"}.count()
		karbohidratSum = Comodities.findAll{it.comodityName.ComodityType.name == "Beras" }.sum()/Comodities.findAll{it.comodityName.ComodityType.name == "Beras"}.count()
		lainlainSum = Comodities.findAll{it..comodityName.ComodityType.name == "Gula"}.sum()/Comodities.findAll{it.comodityName.ComodityType.name == "Gula"}.count()
		Double proteinWeight = ComodityType.find{name == "Daging"}.weight
		Double karbohidratWeight = ComodityType.find {name == "Beras"}.weight
		Double lainlainWeight = ComodityType.find{name == "Gula"}.weight
		
		Double totalInflation = ((proteinSum*proteinWeight) +(karbohidratSum*karbohidratWeight)+(lainlainSum*lainlainWeight))*30/100
		return totalInflation
		
		
	}
}
