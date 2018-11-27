package com.example.taf.taf_bm.model;

import com.orm.SugarRecord;

public abstract class Exercise extends SugarRecord  {
    private int quantity;
    private int gender;
    private Points twentySeven;
    private Points thirtyFive;
    private Points fortyFour;
    private Points fifteen;
    private Points moreFifteen;


    public Exercise(int quantity, int gender, Points twentySeven, Points thirtyFive, Points fortyFour, Points fifteen, Points moreFifteen) {
        this.quantity = quantity;
        this.gender = gender;
        this.twentySeven = twentySeven;
        this.thirtyFive = thirtyFive;
        this.fortyFour = fortyFour;
        this.fifteen = fifteen;
        this.moreFifteen = moreFifteen;
    }

    public Exercise() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Points getTwentySeven() {
        return twentySeven;
    }

    public void setTwentySeven(Points twentySeven) {
        this.twentySeven = twentySeven;
    }

    public Points getThirtyFive() {
        return thirtyFive;
    }

    public void setThirtyFive(Points thirtyFive) {
        this.thirtyFive = thirtyFive;
    }

    public Points getFortyFour() {
        return fortyFour;
    }

    public void setFortyFour(Points fortyFour) {
        this.fortyFour = fortyFour;
    }

    public Points getFifteen() {
        return fifteen;
    }

    public void setFifteen(Points fifteen) {
        this.fifteen = fifteen;
    }

    public Points getMoreFifteen() {
        return moreFifteen;
    }

    public void setMoreFifteen(Points moreFifteen) {
        this.moreFifteen = moreFifteen;
    }
}
