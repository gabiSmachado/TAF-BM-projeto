package com.example.taf.taf_bm.helpers;

import com.example.taf.taf_bm.facade.Facade;
import com.example.taf.taf_bm.model.Miles;

import java.util.List;

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

    public int totalP (int points){
       return (211-points);
    }

   public int miles(int vo2, int gender){
       Facade facade = Facade.getInstance();
       List<Miles> miles = facade.findMiles(gender);
        if (gender == 1){
            for (int i=0; i<miles.size();i++){
                if (vo2 <= (-16) && (vo2 < 17)){
                    if (miles.get(i).getQuantity() == -16)
                    return i;
                }else if ((vo2 >20) && (vo2 <23 )){
                    if (miles.get(i).getQuantity() == 20)
                        return i;
                }else if ((vo2 >23) && (vo2 <26 )){
                    if (miles.get(i).getQuantity() == 23)
                        return i;
                }else if ((vo2 >28) && (vo2 <30 )){
                    if (miles.get(i).getQuantity() == 28)
                        return i;
                }
            }

        }else if(gender == 2){
            for (int i=0; i<miles.size();i++){
                if (vo2 <= (-13) && (vo2 < 14)){
                    if (miles.get(i).getQuantity() == -13)
                        return i;
                }else if ((vo2 >20) && (vo2 < 25 )){
                    if (miles.get(i).getQuantity() == 20)
                        return i;
                }
            }
        }
       for (int i=0; i<miles.size();i++){
           if (miles.get(i).getQuantity() == vo2){
               return i;
           }
       }
        return 0;
    }
}
