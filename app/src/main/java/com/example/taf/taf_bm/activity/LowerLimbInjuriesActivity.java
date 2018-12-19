package com.example.taf.taf_bm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.taf.taf_bm.R;
import com.example.taf.taf_bm.facade.Facade;
import com.example.taf.taf_bm.helpers.Concepts;
import com.example.taf.taf_bm.helpers.MyOnItemSelectedListener;
import com.example.taf.taf_bm.helpers.SetUpViews;
import com.example.taf.taf_bm.model.Miles;
import com.example.taf.taf_bm.model.PullUps;
import com.example.taf.taf_bm.model.PushUps;
import com.example.taf.taf_bm.model.SitUps;

import org.angmarch.views.NiceSpinner;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LowerLimbInjuriesActivity extends Fragment implements RadioGroup.OnCheckedChangeListener,View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Facade facade;
    private SetUpViews setUp;
    private Concepts concept;
    private RadioGroup radio;
    private TextView sitUpPoints, testPoints,testName;
    private int userAge,gender,userGender,calGender,userWeight, points,hr;
    private NiceSpinner spinnerTest,sec,min,rate,weight,spinnerSitUp,spinnerAge;
    private ArrayList<Integer> weights,seconds,minutes,heartRate;
    private List<Miles> miles;
    private double time;
    private List<SitUps> sitUps;
    private ArrayList<Integer> ages;
    private List<PullUps> pullUps;
    private List<PushUps> pushUps;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lower_limb_injuries, container, false);

        facade = Facade.getInstance();
        setUp = new SetUpViews();

        radio = view.findViewById(R.id.gendersRadio);
        radio.setOnCheckedChangeListener(this);

        spinnerAge = view.findViewById(R.id.age);
        ages = setArray(18,90);
        setUp.spinnerAdapter(getActivity().getApplicationContext(),spinnerAge,ages);
        spinnerAge.setOnItemSelectedListener(this);

        view.findViewById(R.id.resulM).setOnClickListener(this);
        concept = new Concepts();

        testName = view.findViewById(R.id.test);
        spinnerTest = (NiceSpinner) view.findViewById(R.id.testQuant);
        testPoints = view.findViewById(R.id.testPoints);

        sec = (NiceSpinner) view.findViewById(R.id.seconds);
        min = (NiceSpinner) view.findViewById(R.id.minutes);
        rate = (NiceSpinner) view.findViewById(R.id.heart_rate);
        weight = (NiceSpinner) view.findViewById(R.id.weight);
        spinnerSitUp = (NiceSpinner) view.findViewById(R.id.sitUpQuant);
        sitUpPoints = view.findViewById(R.id.sitUpsPoints);

        weights = setArray(40,200);
        seconds= setArray(0,59);
        minutes = setArray(1,59);
        heartRate = setArray(60,200);

        setUp.spinnerAdapter(getActivity().getApplicationContext(),sec,seconds);
        setUp.spinnerAdapter(getActivity().getApplicationContext(),min,minutes);
        setUp.spinnerAdapter(getActivity().getApplicationContext(),rate,heartRate);
        setUp.spinnerAdapter(getActivity().getApplicationContext(),weight,weights);

        weight.setOnItemSelectedListener(this);
        rate.setOnItemSelectedListener(this);
        min.setOnItemSelectedListener(this);
        sec.setOnItemSelectedListener(this);

        gender = radio.getCheckedRadioButtonId();
        setTest();
        setUpInformation();
        userWeight = weights.get(0);
        time = minutes.get(0);
        hr = heartRate.get(0);

        getActivity().setTitle("AFE Lesão Membros Inf.");

        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        gender = checkedId;
        setTest();
    }

    public void setTest() {
        switch (gender){
            case R.id.man:
                if (userAge <36){
                    testName.setText("Barra:");
                    pullUps = facade.getPullUps();
                }else{
                    testName.setText("Apoio:");
                    pushUps = facade.findPushUps(1);
                }
                calGender = 1;
                userGender = 1;
                sitUps = facade.findSitUps(1);
                System.out.println(sitUps.get(0).getQuantity());
                setUpInformation();
                break;
            case R.id.woman:
                testName.setText("Apoio:");
                pushUps = facade.findPushUps(2);
                calGender = 0;
                userGender = 2;
                sitUps = facade.findSitUps(2);
                setUpInformation();
                break;
        }
        testPoints.setText("0");
        sitUpPoints.setText("0");
    }

    public void setUpInformation() {
        ArrayList<Integer> integers = new ArrayList<>();
        if ((gender == R.id.man) && (userAge <36)){
            for (int i=0; i<pullUps.size();i++){
                integers.add(pullUps.get(i).getQuantity());
            }
            setUp.spinnerAdapter(getActivity().getApplicationContext(),spinnerTest,integers);
            spinnerTest.addOnItemClickListener(new MyOnItemSelectedListener(integers,testPoints,pullUps,userAge));
        }else{
            for (int i=0; i< pushUps.size();i++){
                integers.add( pushUps.get(i).getQuantity());
            }
            setUp.spinnerAdapter(getActivity().getApplicationContext(),spinnerTest,integers);
            spinnerTest.addOnItemClickListener(new MyOnItemSelectedListener(integers,testPoints,pushUps,userAge));
        }

        ArrayList<Integer> integers1 = new ArrayList<>();
        for (int i = 0; i < sitUps.size(); i++) {
            integers1.add(sitUps.get(i).getQuantity());
        }
        setUp.spinnerAdapter(getActivity().getApplicationContext(), spinnerSitUp, integers1);
        spinnerSitUp.addOnItemClickListener(new MyOnItemSelectedListener(integers1, sitUpPoints, sitUps, userAge));
    }

    @Override
    public void onClick(View v) {
        double libra = 2.20462262;
        double pc = userWeight * libra;
        double aux = (132.853 - (0.0796*pc) - (0.387*userAge) + (6.315*calGender) - (3.2649*time) - (0.1565*hr));
        int vo2 = (int) Math.floor(aux);
        if((userGender == 1) && (vo2 > 55)){
            points = 150;
            System.out.println(points);
        }else if((userGender == 2) && (vo2 > 51)){
            points = 150;
        }else {
            miles = facade.findMiles(userGender);
            int position = concept.miles(vo2,userGender);
            if(miles.size() != 0) {
                if (userAge <= 27)
                    points = miles.get(position).getTwentySeven().getValue();
                else if (userAge >= 28 && userAge <= 35)
                    points = miles.get(position).getThirtyFive().getValue();
                else if (userAge >= 36 && userAge <= 44)
                    points = miles.get(position).getFortyFour().getValue();
                else if (userAge >= 45 && userAge <= 50)
                    points = miles.get(position).getFifteen().getValue();
                else if (userAge > 50)
                    points = miles.get(position).getMoreFifteen().getValue();
            }
        }

        int test = Integer.valueOf(testPoints.getText().toString());
        int sitUp = Integer.valueOf(sitUpPoints.getText().toString());
        if ((test == 0) || (sitUp == 0)){
            points = 0;
        }else{
            points+=(test + sitUp);
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String conceptR = concept.getConcept(points);
        String results = ("VO2Máx: " + df.format(aux) + "\n" +
                "Pontuação: " + points + "\n" +
                "Conceito: " + conceptR + "\n" +
                "Resultado: " + concept.getResult(conceptR));
        if (points < 211){
            results+=("\n\nFalta " + concept.totalP(points) + " pontos para você atingir um resultado Apto.");
        }
        setUp.dialog(results,getContext());
        points = 0;
        //setUp.refresh(LowerLimbInjuriesActivity.class, getArguments().getString("test"),getArguments().getInt("test_type", 1),getFragmentManager());
    }

    public ArrayList<Integer> setArray(int begin, int end){
        ArrayList<Integer> array = new ArrayList();
        for (int i = begin; i<=end; i++){
            array.add(i);
        }
        return array;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
                System.out.println(userAge);
                setTest();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
