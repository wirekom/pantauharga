package com.pantau.core

import grails.validation.Validateable;

@Validateable
class InflationCommandModel {
    //gw belom test, comodities itu disini semua comodityinput yang ketarik dari awal bulan sampai akhir bulan
    List<ComodityInput> Comodities
    Date start
    Date end
    Double inflation

    def countInflation() {
        Double proteinSum = 0
        Double karbohidratSum = 0
        Double lainlainSum = 0

        Integer proteinCount = 0
        Integer karbohidratCount = 0
        Integer lainlainCount = 0
        for (ComodityInput ci : Comodities) {
            println ci.comodityName.comodityType.name
            println ci.delta
            if (ci.comodityName.comodityType.name == "Daging") {
                proteinSum = proteinSum + ci.delta
                proteinCount++


            } else if (ci.comodityName.comodityType.name == "Beras") {
                karbohidratSum = karbohidratSum + ci.delta
                karbohidratCount++


            } else if (ci.comodityName.comodityType.name == "Gula") {
                lainlainSum = lainlainSum + ci.delta
                lainlainCount++


            }

        }

        println karbohidratCount;
        println karbohidratSum
        Double proteinAvg = 0
        Double karbohidratAvg = 0
        Double lainlainAvg = 0
        if (proteinCount > 0) {
            proteinAvg = proteinSum / proteinCount
        } else if (karbohidratCount > 0) {
            karbohidratAvg = karbohidratSum / karbohidratCount
        } else if (lainlainCount) {
            lainlainAvg = lainlainSum / lainlainCount
        }
        println karbohidratAvg
        /*proteinSum = Comodities.findAll{it.comodityName.ComodityType.name == "Daging" }.sum()/Comodities.findAll{it.comodityName.ComodityType.name == "Daging"}.count()
        karbohidratSum = Comodities.findAll{it.comodityName.ComodityType.name == "Beras" }.sum()/Comodities.findAll{it.comodityName.ComodityType.name == "Beras"}.count()
        lainlainSum = Comodities.findAll{it..comodityName.ComodityType.name == "Gula"}.sum()/Comodities.findAll{it.comodityName.ComodityType.name == "Gula"}.count()*/
        Double proteinWeight = ComodityType.find { name == "Daging" }.weight
        Double karbohidratWeight = ComodityType.find { name == "Beras" }.weight
        Double lainlainWeight = ComodityType.find { name == "Gula" }.weight
        println karbohidratWeight
        println proteinAvg * proteinWeight
        println karbohidratAvg * karbohidratWeight
        println lainlainAvg * lainlainWeight
        //println (proteinAvg*proteinWeight) +(karbohidratAvg*karbohidratWeight)+(lainlainAvg*lainlainWeight)
        Double totalInflation = ((proteinAvg * proteinWeight) + (karbohidratAvg * karbohidratWeight) + (lainlainAvg * lainlainWeight)) * 30 / 100
        println totalInflation
        inflation = totalInflation
        return totalInflation


    }
}
