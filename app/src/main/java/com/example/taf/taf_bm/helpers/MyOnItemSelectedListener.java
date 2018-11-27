package com.example.taf.taf_bm.helpers;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.taf.taf_bm.R;
import com.example.taf.taf_bm.model.PullUps;
import com.example.taf.taf_bm.model.PushUps;
import com.example.taf.taf_bm.model.Running;
import com.example.taf.taf_bm.model.SitUps;

import java.util.ArrayList;
import java.util.List;

public class MyOnItemSelectedListener<T> implements AdapterView.OnItemClickListener{
    private  ArrayList<Integer> values;
    private List<T> list;
    private TextView t;
    private int userAge;

    public  MyOnItemSelectedListener(ArrayList<Integer> values, TextView t,List<T> list,int userAge) {
        this.values = values;
        this.t = t;
        this.list = list;
        this.userAge = userAge;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.sitUpQuant:
                List<SitUps> sitUps = (List<SitUps>) list;
                int integer = values.get(position);
                for (int i=0; i<sitUps.size();i++){
                    if (sitUps.get(i).getQuantity() == integer){
                        integer = i;
                    }
                }
                if (userAge <= 27)
                    t.setText(String.valueOf(sitUps.get(integer).getTwentySeven().getValue()));
                else if(userAge >= 28 && userAge<= 35)
                    t.setText(String.valueOf(sitUps.get(integer).getThirtyFive().getValue()));
                else if(userAge >= 36 && userAge<= 44)
                    t.setText(String.valueOf(sitUps.get(integer).getFortyFour().getValue()));
                else if( userAge>= 45 && userAge <=50)
                    t.setText(String.valueOf(sitUps.get(integer).getFifteen().getValue()));
                else if (userAge > 50)
                    t.setText(String.valueOf(sitUps.get(integer).getMoreFifteen().getValue()));

                System.out.println(String.valueOf(sitUps.get(integer).getTwentySeven().getValue()));
                break;

            case R.id.runningQuant:
                List<Running> runnings = (List<Running>) list;
                int integer1 = values.get(position);
                for (int i=0; i<runnings.size();i++){
                    if (runnings.get(i).getQuantity() == integer1){
                        integer1 = i;
                    }
                }
                if (userAge <= 27)
                    t.setText(String.valueOf(runnings.get(integer1).getTwentySeven().getValue()));
                else if(userAge >= 28 && userAge<= 35)
                    t.setText(String.valueOf(runnings.get(integer1).getThirtyFive().getValue()));
                else if(userAge >= 36 && userAge<= 44)
                    t.setText(String.valueOf(runnings.get(integer1).getFortyFour().getValue()));
                else if( userAge>= 45 && userAge <=50)
                    t.setText(String.valueOf(runnings.get(integer1).getFifteen().getValue()));
                else if (userAge > 50)
                    t.setText(String.valueOf(runnings.get(integer1).getMoreFifteen().getValue()));

                System.out.println(String.valueOf(runnings.get(integer1).getMoreFifteen().getValue()));
                break;

            case R.id.testQuant:
                if (list.get(0) instanceof PullUps ){
                    List<PullUps> pullUps = (List<PullUps>) list;
                    int q  = values.get(position);
                    for (int i=0;i<pullUps.size();i++){
                        if (pullUps.get(i).getQuantity() == q){
                            q = i;
                        }
                    }
                    if (userAge <= 27)
                        t.setText(String.valueOf(pullUps.get(q).getTwentySeven().getValue()));
                    else if(userAge >= 28 && userAge<= 35)
                        t.setText(String.valueOf(pullUps.get(q).getThirtyFive().getValue()));
                    System.out.println(String.valueOf(pullUps.get(q).getTwentySeven().getValue()));

                }else if(list.get(0) instanceof PushUps ){
                    List<PushUps> pushUps =(List<PushUps>) list;
                    int value = values.get(position);
                    for (int i=0;i< pushUps.size();i++){
                        if (pushUps.get(i).getQuantity() == value){
                            value = i;
                        }
                    }
                    if (userAge <= 27)
                        t.setText(String.valueOf(pushUps.get(value).getTwentySeven().getValue()));
                    else if(userAge >= 28 && userAge<= 35 )
                        t.setText(String.valueOf(pushUps.get(value).getThirtyFive().getValue()));
                    else if(userAge >= 36 && userAge<= 44)
                        t.setText(String.valueOf(pushUps.get(value).getFortyFour().getValue()));
                    else if( userAge>= 45 && userAge <=50)
                        t.setText(String.valueOf(pushUps.get(value).getFifteen().getValue()));
                    else if (userAge > 50)
                        t.setText(String.valueOf(pushUps.get(value).getMoreFifteen().getValue()));
                    System.out.println(String.valueOf(pushUps.get(value).getFortyFour().getValue()));
                }

                break;
        }
    }


}
