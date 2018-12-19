package com.example.taf.taf_bm.controller;

import com.example.taf.taf_bm.model.Miles;

import java.util.ArrayList;
import java.util.List;

public class ControllerMiles {

    public void save(ArrayList<Miles> miles){
        for (Miles m : miles) {
                m.save();
        }
    }

    public List<Miles> read(){
        return Miles.listAll(Miles.class);
    }

    public List<Miles> select(int gender){
        return Miles.find(Miles.class, "gender = ?", String.valueOf(gender));
    }
}
