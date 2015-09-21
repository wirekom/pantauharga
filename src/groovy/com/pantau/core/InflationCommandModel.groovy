package com.pantau.core

import grails.validation.Validateable;

@Validateable
class InflationCommandModel {
    //gw belom test, comodities itu disini semua comodityinput yang ketarik dari awal bulan sampai akhir bulan
    List<ComodityInput> Comodities
    Date start
    Region region
    Date end
    Double inflation

    def countInflation() {
        Double proteinSum = 0
        Double karbohidratSum = 0
        Double lainlainSum = 0

        Integer proteinCount = 0
        Integer karbohidratCount = 0
        Integer lainlainCount = 0

        if (Comodities.size() > 0) {
            if (Comodities.findAll { it -> it.comodityName.comodityType.name == "Daging" }.size() > 0) {
                proteinSum = Comodities.findAll { it -> it.comodityName.comodityType.name == "Daging" }?.last().delta/Comodities.findAll{ it -> it.comodityName.comodityType.name == "Daging" }?.first().price //- Comodities.findAll{ it -> it.comodityName.comodityType.name == "Daging" }?.first().delta
            }
            if (Comodities.findAll { it -> it.comodityName.comodityType.name == "Beras" }.size() > 0) {
                karbohidratSum = Comodities.findAll { it -> it.comodityName.comodityType.name == "Beras" }?.last().delta/Comodities.findAll { it -> it.comodityName.comodityType.name == "Beras" }?.first().price //- Comodities.findAll { it -> it.comodityName.comodityType.name == "Beras" }?.first().delta
            }
            if (Comodities.findAll { it -> it.comodityName.comodityType.name == "Gula" }.size() > 0) {
                lainlainSum = Comodities.findAll { it -> it.comodityName.comodityType.name == "Gula" }?.last().delta/Comodities.findAll{ it -> it.comodityName.comodityType.name == "Gula" }?.first().price
            }
        }
        /*
        for (comodityinput ci : comodities) {
            println ci.comodityname.comoditytype.name
            println ci.delta
            if (ci.comodityname.comoditytype.name == "daging") {
                proteinsum = proteinsum + ci.delta
                proteincount++


            } else if (ci.comodityname.comoditytype.name == "beras") {
                karbohidratsum = karbohidratsum + ci.delta
                karbohidratcount++


            } else if (ci.comodityname.comoditytype.name == "gula") {
                lainlainsum = lainlainsum + ci.delta
                lainlaincount++


            }

        }*/



        //println karbohidratavg
        /*proteinsum = comodities.findall{it.comodityname.comoditytype.name == "daging" }.sum()/comodities.findall{it.comodityname.comoditytype.name == "daging"}.count()
        karbohidratsum = comodities.findall{it.comodityname.comoditytype.name == "beras" }.sum()/comodities.findall{it.comodityname.comoditytype.name == "beras"}.count()
        lainlainsum = comodities.findall{it..comodityname.comoditytype.name == "gula"}.sum()/comodities.findall{it.comodityname.comoditytype.name == "gula"}.count()*/
        double proteinweight = ComodityType.find { name == "Daging" }.weight
        double karbohidratweight = ComodityType.find { name == "Beras" }.weight
        double lainlainweight = ComodityType.find { name == "Gula" }.weight
       /* println karbohidratweight
        println proteinavg * proteinweight
        println karbohidratavg * karbohidratweight
        println lainlainavg * lainlainweight*/
        //println (proteinavg*proteinweight) +(karbohidratavg*karbohidratweight)+(lainlainavg*lainlainweight)
        double totalinflation = ((proteinSum * proteinweight) + (karbohidratSum * karbohidratweight) + (lainlainSum * lainlainweight)) * 30 / 100
        println totalinflation
        inflation = totalinflation
        return totalinflation


    }
}
