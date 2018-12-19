package com.example.taf.taf_bm.helpers;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.taf.taf_bm.facade.Facade;
import com.example.taf.taf_bm.model.*;

import java.util.ArrayList;



public class SetUpDB {
    private Facade facade;

    public SetUpDB() {
        facade = Facade.getInstance();
    }

    public void registerDB() {
            registerPoints();
            Points p = new Points(0);
            p.save();
            registerPullUp();
            registerPushUp();
            registerSitUp();
            registerRunning();
            registerMiles();
    }

    public void registerPoints() {
        ArrayList<Points> points = new ArrayList<Points>();
        points.add(new Points(1));
        int i = 5;
        while (i < 155) {
            points.add(new Points(i));
            i += 5;
        }
        facade.setPoints(points);
    }

    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase("/data/data/com.example.taf.taf_bm/databases/taf.db", null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
            registerDB();
        }
        return checkDB != null;
    }

    public void registerPullUp() {
        ArrayList<PullUps> pullUps = new ArrayList<>();
        int value = 5;
        for (int i = 1; i < 11; i++) {
            pullUps.add(new PullUps(i, 1, Points.findById(Points.class, value),
                    Points.findById(Points.class, (value + 2)), Points.findById(Points.class, (value + 4)), Points.findById(Points.class, (value + 6)),
                    Points.findById(Points.class, (value+ 7))));
            value++;
        }
        value = 14;
        pullUps.add(new PullUps(11, 1, Points.findById(Points.class,14),
                Points.findById(Points.class, (value + 2)), Points.findById(Points.class, (value + 4)), Points.findById(Points.class, (value + 6)),
                Points.findById(Points.class, (value+ 7))));
        pullUps.add(new PullUps(12, 1, Points.findById(Points.class, value),
                Points.findById(Points.class, (value + 2)), Points.findById(Points.class, (value + 4)), Points.findById(Points.class, (value + 6)),
                Points.findById(Points.class, (value+ 7))));
        facade.setPullUps(pullUps);
    }

    public void registerPushUp() {
        ArrayList<PushUps> pushUps = new ArrayList<>();
        int value = 8;
        int gender = 1;
        for (int i = 0; i < 2; i++) {
            if (i == 1) {
                value = 7;
            }
            pushUps.add(new PushUps(value, gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 32), Points.findById(Points.class, 1),
                    Points.findById(Points.class, 2)));
            pushUps.add(new PushUps((value + 1), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 32), Points.findById(Points.class, 3),
                    Points.findById(Points.class, 4)));
            pushUps.add(new PushUps((value + 2), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 1), Points.findById(Points.class, 5),
                    Points.findById(Points.class, 6)));
            pushUps.add(new PushUps((value + 3), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 3), Points.findById(Points.class, 6),
                    Points.findById(Points.class, 7)));
            pushUps.add(new PushUps((value + 4), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 1), Points.findById(Points.class, 5), Points.findById(Points.class, 7),
                    Points.findById(Points.class, 8)));
            pushUps.add(new PushUps((value + 5), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 3), Points.findById(Points.class, 6), Points.findById(Points.class, 8),
                    Points.findById(Points.class, 9)));
            pushUps.add(new PushUps((value + 6), gender, Points.findById(Points.class, 1),
                    Points.findById(Points.class, 5), Points.findById(Points.class, 7), Points.findById(Points.class, 9),
                    Points.findById(Points.class, 10)));
            pushUps.add(new PushUps((value + 7), gender, Points.findById(Points.class, 3),
                    Points.findById(Points.class, 6), Points.findById(Points.class, 8), Points.findById(Points.class, 10),
                    Points.findById(Points.class, 11)));
            gender++;
        }

        value = 5;
        for (int i = 16; i < 26; i++) {
            pushUps.add(new PushUps(i, 1, Points.findById(Points.class, (value)),
                    Points.findById(Points.class, (value + 2)), Points.findById(Points.class, (value + 4)), Points.findById(Points.class, (value + 6)),
                    Points.findById(Points.class, (value + 7))));
            pushUps.add(new PushUps((i - 1), 2, Points.findById(Points.class, (value)),
                    Points.findById(Points.class, (value + 2)), Points.findById(Points.class, (value + 4)), Points.findById(Points.class, (value + 6)),
                    Points.findById(Points.class, (value + 7))));
            value++;
        }
        pushUps.add(new PushUps(25, 2, Points.findById(Points.class, 15),
                Points.findById(Points.class, 17), Points.findById(Points.class, 19), Points.findById(Points.class, 21),
                Points.findById(Points.class, 22)));
        pushUps.add(new PushUps(26, 2, Points.findById(Points.class, 16),
                Points.findById(Points.class, 18), Points.findById(Points.class, 20), Points.findById(Points.class, 22),
                Points.findById(Points.class, 23)));
        facade.setPushUp(pushUps);

    }


    public void registerSitUp() {
        ArrayList<SitUps> sitUps = new ArrayList<>();
        sitUps.add(new SitUps(19, 1, Points.findById(Points.class, 32),
                Points.findById(Points.class, 32), Points.findById(Points.class, 32), Points.findById(Points.class, 1),
                Points.findById(Points.class, 2)));
        sitUps.add(new SitUps(21, 1, Points.findById(Points.class, 32),
                Points.findById(Points.class, 32), Points.findById(Points.class, 32), Points.findById(Points.class, 3),
                Points.findById(Points.class, 4)));
        sitUps.add(new SitUps(23, 1, Points.findById(Points.class, 32),
                Points.findById(Points.class, 32), Points.findById(Points.class, 1), Points.findById(Points.class, 5),
                Points.findById(Points.class, 6)));
        sitUps.add(new SitUps(25, 1, Points.findById(Points.class, 32),
                Points.findById(Points.class, 32), Points.findById(Points.class, 3), Points.findById(Points.class, 6),
                Points.findById(Points.class, 7)));
        sitUps.add(new SitUps(27, 1, Points.findById(Points.class, 32),
                Points.findById(Points.class, 1), Points.findById(Points.class, 5), Points.findById(Points.class, 7),
                Points.findById(Points.class, 8)));
        sitUps.add(new SitUps(29, 1, Points.findById(Points.class, 32),
                Points.findById(Points.class, 3), Points.findById(Points.class, 6), Points.findById(Points.class, 8),
                Points.findById(Points.class, 9)));
        sitUps.add(new SitUps(31, 1, Points.findById(Points.class, 1),
                Points.findById(Points.class, 5), Points.findById(Points.class, 7), Points.findById(Points.class, 9),
                Points.findById(Points.class, 10)));
        sitUps.add(new SitUps(33, 1, Points.findById(Points.class, 3),
                Points.findById(Points.class, 6), Points.findById(Points.class, 8), Points.findById(Points.class, 10),
                Points.findById(Points.class, 11)));
        sitUps.add(new SitUps(35, 1, Points.findById(Points.class, 5),
                Points.findById(Points.class, 7), Points.findById(Points.class, 9), Points.findById(Points.class, 12),
                Points.findById(Points.class, 13)));
        sitUps.add(new SitUps(37, 1, Points.findById(Points.class, 6),
                Points.findById(Points.class, 8), Points.findById(Points.class, 10), Points.findById(Points.class, 13),
                Points.findById(Points.class, 14)));

        sitUps.add(new SitUps(17, 2, Points.findById(Points.class, 32),
                Points.findById(Points.class, 32), Points.findById(Points.class, 32), Points.findById(Points.class, 1),
                Points.findById(Points.class, 2)));
        sitUps.add(new SitUps(18, 2, Points.findById(Points.class, 32),
                Points.findById(Points.class, 32), Points.findById(Points.class, 32), Points.findById(Points.class, 3),
                Points.findById(Points.class, 4)));
        sitUps.add(new SitUps(19, 2, Points.findById(Points.class, 32),
                Points.findById(Points.class, 32), Points.findById(Points.class, 1), Points.findById(Points.class, 5),
                Points.findById(Points.class, 6)));
        sitUps.add(new SitUps(20, 2, Points.findById(Points.class, 32),
                Points.findById(Points.class, 32), Points.findById(Points.class, 3), Points.findById(Points.class, 6),
                Points.findById(Points.class, 7)));
        sitUps.add(new SitUps(21, 2, Points.findById(Points.class, 32),
                Points.findById(Points.class, 1), Points.findById(Points.class, 5), Points.findById(Points.class, 7),
                Points.findById(Points.class, 8)));
        sitUps.add(new SitUps(22, 2, Points.findById(Points.class, 32),
                Points.findById(Points.class, 3), Points.findById(Points.class, 6), Points.findById(Points.class, 8),
                Points.findById(Points.class, 9)));
        sitUps.add(new SitUps(23, 2, Points.findById(Points.class, 1),
                Points.findById(Points.class, 5), Points.findById(Points.class, 7), Points.findById(Points.class, 9),
                Points.findById(Points.class, 10)));
        sitUps.add(new SitUps(24, 2, Points.findById(Points.class, 3),
                Points.findById(Points.class, 6), Points.findById(Points.class, 8), Points.findById(Points.class, 10),
                Points.findById(Points.class, 11)));



        int value = 5;
        for (int i = 25; i < 37; i++) {
            sitUps.add(new SitUps(i, 2, Points.findById(Points.class, value),
                        Points.findById(Points.class, (value + 2)), Points.findById(Points.class, (value + 4)), Points.findById(Points.class, (value + 6)),
                        Points.findById(Points.class, (value + 7))));
            value++;
        }

        value = 7;
        for (int i = 39; i < 49; i++) {
                sitUps.add(new SitUps(i, 1, Points.findById(Points.class,value),
                        Points.findById(Points.class, (value + 2)), Points.findById(Points.class, (value + 4)), Points.findById(Points.class, (value + 6)),
                        Points.findById(Points.class, (value + 7))));
                value++;
        }
        facade.setSitup(sitUps);
    }

    public void registerRunning(){
        ArrayList<Running> runnings = new ArrayList<>();
        int value = 1200;
        int gender = 1;
        for (int i = 0; i < 2; i++) {
            if (i == 1) {
                value = 1000;
            }
            runnings.add(new Running(value, gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 32), Points.findById(Points.class, 1),
                    Points.findById(Points.class, 2)));
            runnings.add(new Running((value + 50), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 32), Points.findById(Points.class, 3),
                    Points.findById(Points.class, 4)));
            runnings.add(new Running((value + 100), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 1), Points.findById(Points.class, 5),
                    Points.findById(Points.class, 6)));
            runnings.add(new Running((value + 150),gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 3), Points.findById(Points.class, 6),
                    Points.findById(Points.class, 7)));
            runnings.add(new Running((value + 200), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 1), Points.findById(Points.class, 5), Points.findById(Points.class, 7),
                    Points.findById(Points.class, 8)));
            runnings.add(new Running((value + 250), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 3), Points.findById(Points.class, 6), Points.findById(Points.class, 8),
                    Points.findById(Points.class, 9)));
            runnings.add(new Running((value + 300), gender, Points.findById(Points.class, 1),
                    Points.findById(Points.class, 5), Points.findById(Points.class, 7), Points.findById(Points.class, 9),
                    Points.findById(Points.class, 10)));
            runnings.add(new Running((value + 350), gender, Points.findById(Points.class, 3),
                    Points.findById(Points.class, 6), Points.findById(Points.class, 8), Points.findById(Points.class, 10),
                    Points.findById(Points.class, 11)));
            gender++;
        }

        value = 5;
        int i = 1600;
        while(i< 2600){
            runnings.add(new Running(i, 1, Points.findById(Points.class, (value)),
                    Points.findById(Points.class, (value + 2)), Points.findById(Points.class, (value + 4)), Points.findById(Points.class, (value + 6)),
                    Points.findById(Points.class, (value + 7))));
            runnings.add(new Running((i - 200), 2, Points.findById(Points.class, (value)),
                    Points.findById(Points.class, (value + 2)), Points.findById(Points.class, (value + 4)), Points.findById(Points.class, (value + 6)),
                    Points.findById(Points.class, (value + 7))));
            value++;
            i+=50;
        }
        gender = 1;
        value = 2600;
        for (i = 0; i < 2; i++) {
            if (i == 1) {
                value = 2400;
            }
            runnings.add(new Running(value, gender, Points.findById(Points.class, 25),
                    Points.findById(Points.class, 27), Points.findById(Points.class, 29), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            runnings.add(new Running((value + 50), gender, Points.findById(Points.class, 26),
                    Points.findById(Points.class, 28), Points.findById(Points.class, 30), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            runnings.add(new Running((value + 100), gender, Points.findById(Points.class, 27),
                    Points.findById(Points.class, 29), Points.findById(Points.class, 31), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            runnings.add(new Running((value + 150),gender, Points.findById(Points.class, 28),
                    Points.findById(Points.class, 30), Points.findById(Points.class, 31), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            runnings.add(new Running((value + 200), gender, Points.findById(Points.class, 29),
                    Points.findById(Points.class, 31), Points.findById(Points.class, 31), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            runnings.add(new Running((value + 250), gender, Points.findById(Points.class, 30),
                    Points.findById(Points.class, 31), Points.findById(Points.class, 31), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            runnings.add(new Running((value + 300), gender, Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31), Points.findById(Points.class, 31), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            gender++;
        }
        facade.setRunning(runnings);
    }

    public void registerMiles() {
        ArrayList<Miles> miles = new ArrayList<>();
        int value = -16;
        int gender = 1;
        int aux = 33;
        for (int i = 0; i < 2; i++) {
            if (i == 1) {
                value = -13;
                aux = 27;
            }
            miles.add(new Miles(value, gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 32), Points.findById(Points.class, 1),
                    Points.findById(Points.class, 2)));
            miles.add(new Miles((value + aux), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 32), Points.findById(Points.class, 3),
                    Points.findById(Points.class, 4)));
            miles.add(new Miles((value + (aux + 1)), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 1), Points.findById(Points.class, 5),
                    Points.findById(Points.class, 6)));
            miles.add(new Miles((value + (aux + 2)),gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 32), Points.findById(Points.class, 3), Points.findById(Points.class, 6),
                    Points.findById(Points.class, 7)));
            miles.add(new Miles((value + (aux + 3)), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 1), Points.findById(Points.class, 5), Points.findById(Points.class, 7),
                    Points.findById(Points.class, 8)));
            miles.add(new Miles((value + (aux + 4)), gender, Points.findById(Points.class, 32),
                    Points.findById(Points.class, 3), Points.findById(Points.class, 6), Points.findById(Points.class, 8),
                    Points.findById(Points.class, 9)));
            miles.add(new Miles((value + (aux + 5)), gender, Points.findById(Points.class, 1),
                    Points.findById(Points.class, 5), Points.findById(Points.class, 7), Points.findById(Points.class, 9),
                    Points.findById(Points.class, 10)));
            miles.add(new Miles((value + (aux + 6)), gender, Points.findById(Points.class, 3),
                    Points.findById(Points.class, 6), Points.findById(Points.class, 8), Points.findById(Points.class, 10),
                    Points.findById(Points.class, 11)));
            gender++;
        }

        miles.add(new Miles(28, 1, Points.findById(Points.class, 5),
                Points.findById(Points.class, 7), Points.findById(Points.class, 9), Points.findById(Points.class, 11),
                Points.findById(Points.class, 12)));
        miles.add(new Miles(25, 2, Points.findById(Points.class, 5),
                Points.findById(Points.class, 7), Points.findById(Points.class, 9), Points.findById(Points.class, 11),
                Points.findById(Points.class, 12)));

        value = 6;
        for(int i = 30; i< 49; i++){
            miles.add(new Miles(i, 1, Points.findById(Points.class, (value)),
                    Points.findById(Points.class, (value + 2)), Points.findById(Points.class, (value + 4)), Points.findById(Points.class, (value + 6)),
                    Points.findById(Points.class, (value + 7))));
            miles.add(new Miles((i - 4), 2, Points.findById(Points.class, (value)),
                    Points.findById(Points.class, (value + 2)), Points.findById(Points.class, (value + 4)), Points.findById(Points.class, (value + 6)),
                    Points.findById(Points.class, (value + 7))));
            value++;
        }

        gender = 1;
        value = 49;
        for (int i = 0; i < 2; i++) {
            if (i == 1) {
                value = 45;
            }
            miles.add(new Miles(value, gender, Points.findById(Points.class, 25),
                    Points.findById(Points.class, 27), Points.findById(Points.class, 29), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            miles.add(new Miles((value + 1), gender, Points.findById(Points.class, 26),
                    Points.findById(Points.class, 28), Points.findById(Points.class, 30), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            miles.add(new Miles((value + 2), gender, Points.findById(Points.class, 27),
                    Points.findById(Points.class, 29), Points.findById(Points.class, 31), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            miles.add(new Miles((value + 3),gender, Points.findById(Points.class, 28),
                    Points.findById(Points.class, 30), Points.findById(Points.class, 31), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            miles.add(new Miles((value + 4), gender, Points.findById(Points.class, 29),
                    Points.findById(Points.class, 31), Points.findById(Points.class, 31), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            miles.add(new Miles((value + 5), gender, Points.findById(Points.class, 30),
                    Points.findById(Points.class, 31), Points.findById(Points.class, 31), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            miles.add(new Miles((value + 6), gender, Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31), Points.findById(Points.class, 31), Points.findById(Points.class, 31),
                    Points.findById(Points.class, 31)));
            gender++;
        }
        facade.setMiles(miles);
    }
}
