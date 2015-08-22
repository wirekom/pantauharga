package com.pantau.core


class InflationCommandModel {
	//gw belom test, comodities itu disini semua comodityinput yang ketarik dari awal bulan sampai akhir bulan
	List<ComodityInput> Comodities
	Date start
	Date end
	def countInflation () {
		Double proteinSum = 0
		Double karbohidratSum = 0
		Double lainlainSum = 0
		
		Integer proteinCount = 0
		Integer karbohidratCount = 0
		Integer lainlainCount = 0 
		for (ComodityInput ci : Comodities) {
			if (ci.comodityName.comodityType.name == "Daging"){
				proteinSum + ci.delta
				proteinCount++
				
				
			}else if (ci.comodityName.comodityType.name == "Beras"){
				karbohidratSum + ci.delta
				karbohidratCount++
				
				
			} else if (ci.comodityName.comodityType.name == "Gula"){
				lainlainSum + ci.delta
				lainlainCount++
				
				
			}
			
		}
		Double proteinAvg = 0
		Double karbohidratAvg = 0
		Double lainlainAvg = 0 
		if (proteinCount > 0){
			proteinAvg = proteinSum/proteinCount
		} else if (karbohidratCount > 0 ) {
			karbohidratAvg = karbohidratSum/karbohidratCount	
		} else if (lainlainCount) {
			lainlainAvg = lainlainSum/lainlainCount
		}
		/*proteinSum = Comodities.findAll{it.comodityName.ComodityType.name == "Daging" }.sum()/Comodities.findAll{it.comodityName.ComodityType.name == "Daging"}.count()
		karbohidratSum = Comodities.findAll{it.comodityName.ComodityType.name == "Beras" }.sum()/Comodities.findAll{it.comodityName.ComodityType.name == "Beras"}.count()
		lainlainSum = Comodities.findAll{it..comodityName.ComodityType.name == "Gula"}.sum()/Comodities.findAll{it.comodityName.ComodityType.name == "Gula"}.count()*/
		Double proteinWeight = ComodityType.find{name == "Daging"}.weight
		Double karbohidratWeight = ComodityType.find {name == "Beras"}.weight
		Double lainlainWeight = ComodityType.find{name == "Gula"}.weight
		
		Double totalInflation = ((proteinAvg*proteinWeight) +(karbohidratAvg*karbohidratWeight)+(lainlainAvg*lainlainWeight))*30/100
		return totalInflation
		
		
	}
}
