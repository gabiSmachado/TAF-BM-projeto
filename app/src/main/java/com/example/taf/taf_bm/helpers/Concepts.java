package com.example.taf.taf_bm.helpers;

public class Concepts {

    public String getConcept(int points){
        if (points >=300)
            return "Excelente";
        else if (points >= 255 && points <=299)
            return "Muito Bom";
        else if (points >= 211 && points<=254)
            return "Bom";
        else if(points>=151 && points<=210)
            return "Regular";
        return "Insuficiente";
    }

    public String getResult(String concept){
        if (concept.matches("Insuficiente") || concept.matches("Regular")){
            return "Inapto";
        }
        return "Apto";
    }
}
