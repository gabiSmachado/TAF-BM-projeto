package com.example.taf.taf_bm.activity;

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
import com.example.taf.taf_bm.model.Running;
import com.example.taf.taf_bm.model.SitUps;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

public class UpperLimbInjuriesActivity extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    private List<SitUps> sitUps;
    private List<Running> runnings;
    private TextView sitUpPoints,runningPoints;
    private int userAge,gender, points;
    private SetUpViews setUp;
    private Concepts concept;
    private Facade facade;
    private NiceSpinner spinnerSitUp,spinnerRunning, spinnerAge;
    private ArrayList<Integer> ages;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_upper_limb_injuries, container, false);

        facade = Facade.getInstance();
        setUp = new SetUpViews();

        RadioGroup radio = view.findViewById(R.id.gendersRadio);
        radio.setOnCheckedChangeListener(this);

        spinnerAge = view.findViewById(R.id.age);
        ages = new ArrayList<>();
        for (int i = 18; i<=90;i++){
            ages.add(i);
        }
        setUp.spinnerAdapter(getActivity().getApplicationContext(),spinnerAge,ages);
        spinnerAge.setOnItemSelectedListener(this);
        spinnerSitUp = (NiceSpinner) view.findViewById(R.id.sitUpQuant);
        sitUpPoints = view.findViewById(R.id.sitUpPoints);
        spinnerRunning = (NiceSpinner) view.findViewById(R.id.runningQuant);
        runningPoints = view.findViewById(R.id.runningPoints);

        view.findViewById(R.id.result).setOnClickListener(this);
        concept = new Concepts();

        gender = radio.getCheckedRadioButtonId();
        setTest();
        setUpInformation();

        getActivity().setTitle("AFE Lesão Membros Sup.");

        return  view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        gender = checkedId;
        setTest();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void setTest(){
        switch (gender){
            case R.id.woman:
                sitUps = facade.findSitUps(2);
                runnings = facade.findRunnings(2);
                setUpInformation();
                break;
            case R.id.man:
                sitUps = facade.findSitUps(1);
                runnings = facade.findRunnings(1);
                setUpInformation();
                break;
        }
        sitUpPoints.setText("0");
        runningPoints.setText("0");
    }

    public void setUpInformation(){
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
        int sitUp = Integer.valueOf(sitUpPoints.getText().toString());
        int run = Integer.valueOf(runningPoints.getText().toString());
        if ((sitUp == 0) || (run == 0)){
            points = 0;
        }else{
            points = (sitUp*2) + run;
        }
        String conceptR = concept.getConcept(points);
        String results  = ("Pontuação: " + points + "\n" +
                           "Conceito: " + conceptR + "\n" +
                            "Resultado: " + concept.getResult(conceptR));
        if (points < 211){
            results+=("\n\n Falta " + concept.totalP(points) + " pontos para você atingir um resultado Apto.");
        }
        setUp.dialog(results,getContext());
        //setUp.refresh(UpperLimbInjuriesActivity.class, getArguments().getString("test"),getArguments().getInt("test_type", 1),getFragmentManager());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        userAge = ages.get(position);
        setTest();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
