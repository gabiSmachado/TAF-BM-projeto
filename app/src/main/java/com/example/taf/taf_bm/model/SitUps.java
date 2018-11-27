package com.example.taf.taf_bm.model;

import com.orm.dsl.Table;

@Table
public class SitUps extends Exercise {
    public SitUps(int quantity, int gender, Points twentySeven, Points thirtyFive, Points fortyFour, Points fifteen, Points moreFifteen) {
        super(quantity, gender, twentySeven, thirtyFive, fortyFour, fifteen, moreFifteen);
    }
    public SitUps(){ }
}
