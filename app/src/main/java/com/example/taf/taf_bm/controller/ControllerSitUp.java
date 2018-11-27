package com.example.taf.taf_bm.controller;

import com.example.taf.taf_bm.model.SitUps;

import java.util.ArrayList;
import java.util.List;

public class ControllerSitUp {
    public void save(ArrayList<SitUps> sitUps){
        for (SitUps s : sitUps){
            s.save();
        }
    }

    public List<SitUps> read(){
        return SitUps.listAll(SitUps.class);
    }

    public List<SitUps> select(int gender){
        return SitUps.find(SitUps.class, "gender = ?", String.valueOf(gender));
    }
}
