package com.example.taf.taf_bm.controller;

import com.example.taf.taf_bm.model.Points;

import java.util.ArrayList;
import java.util.List;

public class ControllerPoints {

    public void save(ArrayList<Points> points){
        for (Points p : points) {
            if (p.getValue() > 0)
                p.save();
        }
    }

    public List<Points> read(){
        return Points.listAll(Points.class);
    }

}
