package com.example.taf.taf_bm.controller;

import com.example.taf.taf_bm.model.Running;

import java.util.ArrayList;
import java.util.List;

public class ControllerRunning {
    public void save(ArrayList<Running> runnings){
        for (Running r : runnings){
            r.save();
        }
    }

    public List<Running> read(){return Running.listAll(Running.class);}

    public List<Running> select(int gender){
        return Running.find(Running.class, "gender = ?", String.valueOf(gender));
    }
}
