package com.example.taf.taf_bm.model;

import com.orm.SugarRecord;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

@Table
public class Points extends SugarRecord {
    private int value;

    public Points(int value) {
        this.value = value;
    }
    public Points(){}

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
