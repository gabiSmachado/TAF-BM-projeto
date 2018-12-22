package com.example.taf.taf_bm.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taf.taf_bm.R;
import com.example.taf.taf_bm.facade.Facade;
import com.example.taf.taf_bm.helpers.BaseBackPressedListener;
import com.example.taf.taf_bm.helpers.Concepts;
import com.example.taf.taf_bm.helpers.MyOnItemSelectedListener;
import com.example.taf.taf_bm.helpers.SetUpViews;
import com.example.taf.taf_bm.model.PullUps;
import com.example.taf.taf_bm.model.PushUps;
import com.example.taf.taf_bm.model.Running;
import com.example.taf.taf_bm.model.SitUps;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

public class TafActivity extends Fragment implements RadioGroup.OnCheckedChangeListener,View.OnClickListener,
        AdapterView.OnItemSelectedListener{
    private NiceSpinner spinnerTest, spinnerSitUp,spinnerRunning,spinnerAge;
    private Facade facade;
    private List<PullUps> pullUps;
    private List<PushUps> pushUps;
    private List<SitUps> sitUps;
    private List<Running> runnings;
    private TextView testPoints,sitUpPoints,runningPoints;
    private TextView testName;
    private int userAge,gender,userTest, points;
    private SetUpViews setUp;
    private Concepts concept;
    private  RadioGroup radio;
    private ArrayList<Integer> ages;
    private FragmentActivity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null){
            super.onActivityCreated(savedInstanceState);
        }

        activity = getActivity();
        ((MainActivity)activity).setOnBackPressedListener(new BaseBackPressedListener(activity));

        View view = inflater.inflate(R.layout.activity_taf, container, false);
        testName = view.findViewById(R.id.test);

        facade = Facade.getInstance();
        setUp = new SetUpViews();

        radio = view.findViewById(R.id.gendersRadio);
        radio.setOnCheckedChangeListener(this);

        spinnerAge = (NiceSpinner) view.findViewById(R.id.age);
        ages = new ArrayList<>();
        for (int i = 18; i<=90;i++){
            ages.add(i);
        }
        setUp.spinnerAdapter(getActivity().getApplicationContext(),spinnerAge,ages);
        spinnerAge.setOnItemSelectedListener(this);

        spinnerTest = (NiceSpinner) view.findViewById(R.id.testQuant);
        testPoints = view.findViewById(R.id.testPoints);
        spinnerSitUp = (NiceSpinner) view.findViewById(R.id.sitUpQuant);
        sitUpPoints = view.findViewById(R.id.sitUpPoints);
        spinnerRunning = (NiceSpinner) view.findViewById(R.id.runningQuant);
        runningPoints = view.findViewById(R.id.runningPoints);

        view.findViewById(R.id.result).setOnClickListener(this);
        userTest = getArguments().getInt("test_type", 1);
        concept = new Concepts();

        gender = radio.getCheckedRadioButtonId();
        userAge = ages.get(0);
        setTest();
        setUpInformation();

        getActivity().setTitle(getArguments().getString("test"));

        return view;
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        gender = checkedId;
        setTest();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userAge = ages.get(position);
        setTest();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void setTest(){
        switch (gender){
            case R.id.woman:
                testName.setText("Apoio:");
                pushUps = facade.findPushUps(2);
                sitUps = facade.findSitUps(2);
                runnings = facade.findRunnings(2);
                setUpInformation();
                break;
            case R.id.man:
                if (userAge <36){
                    testName.setText("Barra:");
                    pullUps = facade.getPullUps();
                }else{
                    testName.setText("Apoio:");
                    pushUps = facade.findPushUps(1);
                }
                sitUps = facade.findSitUps(1);
                runnings = facade.findRunnings(1);
                setUpInformation();
                break;
        }
        testPoints.setText("0");
        sitUpPoints.setText("0");
        runningPoints.setText("0");
    }

    public void setUpInformation(){
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
        for (int i=0; i< sitUps.size();i++){
            integers1.add( sitUps.get(i).getQuantity());
        }
        setUp.spinnerAdapter(getActivity().getApplicationContext(),spinnerSitUp,integers1);
        spinnerSitUp.addOnItemClickListener(new MyOnItemSelectedListener(integers1,sitUpPoints,sitUps,userAge));

        ArrayList<Integer> integers2 = new ArrayList<>();
        for (int i=0; i< runnings.size();i++){
            integers2.add( runnings.get(i).getQuantity());
        }
        setUp.spinnerAdapter(getActivity().getApplicationContext(),spinnerRunning,integers2);
        spinnerRunning.addOnItemClickListener(new MyOnItemSelectedListener(integers2,runningPoints,runnings,userAge));
    }

    @Override
    public void onClick(View v) {
        String results = null;
        int test = Integer.valueOf(testPoints.getText().toString());
        int sitUp = Integer.valueOf(sitUpPoints.getText().toString());
        int run = Integer.valueOf(runningPoints.getText().toString());
        if ((test == 0) || (sitUp == 0) || (run == 0)){
            points = 0;
        }else{
            points = test + sitUp + run;
        }

        if (userTest == 1) {
            String conceptR = concept.getConcept(points);
            results  = ("Pontuação: " + points + "\n" +
                              "Conceito: " + conceptR + "\n" +
                              "Resultado: " + concept.getResult(conceptR));
            if (points < 211){
                results+=("\n\nFalta " + concept.totalP(points) + " pontos para você atingir um resultado Apto.");
            }
        }else if (userTest == 2){
            double resul = (points*10)/300;
            results  = ("Pontuação: " + points + "\n" +
                        "Nota: " + resul);
        }
        setUp.dialog(results,getContext());
        //setUp.refresh(TafActivity.class, getArguments().getString("test"),getArguments().getInt("test_type", 1),getFragmentManager());
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
