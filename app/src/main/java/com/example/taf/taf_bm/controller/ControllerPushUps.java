package com.example.taf.taf_bm.controller;

import com.example.taf.taf_bm.model.PushUps;

import java.util.ArrayList;
import java.util.List;

public class ControllerPushUps {
    public void save(ArrayList<PushUps> pushUp){
        for (PushUps p : pushUp) {
            p.save();
        }
    }
    public List<PushUps> read(){
        return PushUps.listAll(PushUps.class);
    }

    public List<PushUps> select(int gender){
        return PushUps.find(PushUps.class, "gender = ?", String.valueOf(gender));
    }
}

