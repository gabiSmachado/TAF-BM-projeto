package com.example.taf.taf_bm.model;

import com.orm.dsl.Table;

@Table
public class PushUps extends Exercise{
    public PushUps(int quantity, int gender, Points twentySeven, Points thirtyFive, Points fortyFour, Points fifteen, Points moreFifteen) {
        super(quantity, gender, twentySeven, thirtyFive, fortyFour, fifteen, moreFifteen);
    }
    public PushUps(){ }

}
