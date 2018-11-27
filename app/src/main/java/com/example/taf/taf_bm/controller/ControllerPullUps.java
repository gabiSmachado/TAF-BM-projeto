package com.example.taf.taf_bm.controller;

import com.example.taf.taf_bm.model.PullUps;

import java.util.ArrayList;
import java.util.List;

public class ControllerPullUps {
    public void save(ArrayList<PullUps> pullUps){
        for (PullUps p : pullUps){
            p.save();
        }
    }

    public List<PullUps> read(){
        return PullUps.listAll(PullUps.class);
    }

}
