package com.example.taf.taf_bm.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;

import com.example.taf.taf_bm.R;
import com.example.taf.taf_bm.facade.Facade;
import com.example.taf.taf_bm.helpers.Concepts;
import com.example.taf.taf_bm.helpers.SetUpViews;
import com.example.taf.taf_bm.model.Miles;

import org.angmarch.views.NiceSpinner;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MilesActivity extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemClickListener{
    private Facade facade;
    private SetUpViews setUp;
    private Concepts concept;
    private RadioGroup radio;
    private int userAge,gender,userGender,userWeight, points,hr;
    private NiceSpinner sec,min,rate,weight, spinnerAge;
    private ArrayList<Integer> weights,seconds,minutes,heartRate;
    private List<Miles> miles;
    private double time;
    private ArrayList<Integer> ages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_miles, container, false);

        facade = Facade.getInstance();
        setUp = new SetUpViews();

        radio = view.findViewById(R.id.gendersRadio);
        radio.setOnCheckedChangeListener(this);

        spinnerAge = view.findViewById(R.id.age);
        ages = setArray(18,90);
        setUp.spinnerAdapter(getActivity().getApplicationContext(),spinnerAge,ages);

        view.findViewById(R.id.resulM).setOnClickListener(this);
        concept = new Concepts();

        sec = (NiceSpinner) view.findViewById(R.id.seconds);
        min = (NiceSpinner) view.findViewById(R.id.minutes);
        rate = (NiceSpinner) view.findViewById(R.id.heart_rate);
        weight = (NiceSpinner) view.findViewById(R.id.weight);

        weights = setArray(40,200);
        seconds= setArray(0,59);
        minutes = setArray(1,59);
        heartRate = setArray(60,200);

        setUp.spinnerAdapter(getActivity().getApplicationContext(),sec,seconds);
        setUp.spinnerAdapter(getActivity().getApplicationContext(),min,minutes);
        setUp.spinnerAdapter(getActivity().getApplicationContext(),rate,heartRate);
        setUp.spinnerAdapter(getActivity().getApplicationContext(),weight,weights);

        weight.addOnItemClickListener(this);
        rate.addOnItemClickListener(this);
        min.addOnItemClickListener(this);
        sec.addOnItemClickListener(this);

        gender = 0;
        userGender = 2;
        userWeight = weights.get(0);
        userAge = ages.get(0);
        time = minutes.get(0);
        hr = heartRate.get(0);

        getActivity().setTitle(getArguments().getString("test"));
        return  view;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.man:
                gender = 1;
                userGender = 1;
                break;
            case R.id.woman:
                gender = 0;
                userGender = 2;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        double libra = 2.20462262;
        double pc = userWeight * libra;
        double aux =  ((132.853 - (0.0796*pc)) - (0.387*userAge) + (6.315*gender) - (3.2649*time) - (0.1565*hr));
        int vo2 = (int) Math.round(aux);
        if((userGender == 1) && (vo2 > 55)){
            points = 150;
        }else if((userGender == 2) && (vo2 > 51)){
            points = 150;
        }else {
            miles = facade.findMiles(userGender,(vo2));
            if(miles.size() != 0) {
                if (userAge <= 27)
                    points = miles.get(0).getTwentySeven().getValue();
                else if (userAge >= 28 && userAge <= 35)
                    points = miles.get(0).getThirtyFive().getValue();
                else if (userAge >= 36 && userAge <= 44)
                    points = miles.get(0).getFortyFour().getValue();
                else if (userAge >= 45 && userAge <= 50)
                    points = miles.get(0).getFifteen().getValue();
                else if (userAge > 50)
                    points = miles.get(0).getMoreFifteen().getValue();
            }else{
                points = 0;
            }
        }
        points+=150;
        DecimalFormat df = new DecimalFormat("#.00");
        String conceptR = concept.getConcept(points);
        String results = ("VO2Máx: " + df.format(aux) + "\n" +
                          "Pontuação: " + points + "\n" +
                          "Conceito: " + conceptR + "\n" +
                          "Resultado: " + concept.getResult(conceptR));

        setUp.dialog(results,getContext());
    }


    public ArrayList<Integer> setArray(int begin, int end){
        ArrayList<Integer> array = new ArrayList();
        for (int i = begin; i<=end; i++){
            array.add(i);
        }
        return array;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.weight:
                userWeight = weights.get(position);
                break;
            case R.id.minutes:
                time = minutes.get(position);
                break;
            case R.id.seconds:
                double aux = seconds.get(position);
                time+= (aux/10);
                break;
            case R.id.heart_rate:
                hr = heartRate.get(position);
                break;
            case R.id.age:
                userAge = ages.get(position);
                break;
        }
    }
}
