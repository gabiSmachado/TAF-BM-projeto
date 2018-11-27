package com.example.taf.taf_bm.model;

import com.orm.dsl.Table;

@Table
public class Miles extends Exercise {
    public Miles(int quantity, int gender, Points twentySeven, Points thirtyFive, Points fortyFour, Points fifteen, Points moreFifteen) {
        super(quantity, gender, twentySeven, thirtyFive, fortyFour, fifteen, moreFifteen);
    }
    public Miles(){ }
}
