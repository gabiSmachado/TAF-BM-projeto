package com.example.taf.taf_bm.facade;

import com.example.taf.taf_bm.controller.*;
import com.example.taf.taf_bm.model.*;


import java.util.ArrayList;
import java.util.List;

public class Facade {
    private ControllerPoints controllerPoints;
    private ControllerPullUps controllerPullUps;
    private ControllerPushUps controllerPushUps;
    private ControllerSitUp controllerSitUp;
    private ControllerRunning controllerRunning;
    private ControllerMiles controllerMiles;
    private static Facade facade;

    public Facade() {
        controllerPoints = new ControllerPoints();
        controllerPullUps = new ControllerPullUps();
        controllerPushUps = new ControllerPushUps();
        controllerSitUp = new ControllerSitUp();
        controllerRunning = new ControllerRunning();
        controllerMiles = new ControllerMiles();
    }

    public static Facade getInstance() {
        if (facade == null) {
            facade = new Facade();
        }
        return facade;
    }


    public void setPoints(ArrayList<Points> points){
        controllerPoints.save(points);
    }
    public List<Points> getPoints(){
        return controllerPoints.read();
    }

    public void setPullUps(ArrayList<PullUps> pullUps){
        controllerPullUps.save(pullUps);
    }
    public List<PullUps> getPullUps(){
        return controllerPullUps.read();
    }

    public void setPushUp(ArrayList<PushUps> pushUps){
        controllerPushUps.save(pushUps);
    }
    public List<PushUps> getPushUps(){
        return controllerPushUps.read();
    }
    public List<PushUps> findPushUps(int gender){return controllerPushUps.select(gender);}

    public void setSitup(ArrayList<SitUps> situp){controllerSitUp.save(situp);}
    public List<SitUps> getSitUps(){return controllerSitUp.read();}
    public List<SitUps> findSitUps(int gender){return controllerSitUp.select(gender);}

    public void setRunning(ArrayList<Running> runnings){
        controllerRunning.save(runnings);
    }
    public List<Running> getRunnings(){return controllerRunning.read();}
    public List<Running> findRunnings(int gender){return controllerRunning.select(gender);}

    public void setMiles(ArrayList<Miles> miles){
        controllerMiles.save(miles);
    }
    public List<Miles> getMiles(){return controllerMiles.read();}
    public List<Miles> findMiles(int gender){return controllerMiles.select(gender);}
}
