package com.example.taf.taf_bm.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.taf.taf_bm.R;

public class HeartProblemsActivity extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null){
            super.onActivityCreated(savedInstanceState);
        }
        View view = inflater.inflate(R.layout.activity_heart_problems, container, false);

        TextView text = view.findViewById(R.id.description);
        text.setText("Infelizmente não podemos lhe ajudar ! \n\n Teste ergométrico, na esteira ou bicicleta, monitorizado eletrocardiograficamente e com " +
                "acompanhamento médico.");

        getActivity().setTitle("Problemas Cardiacos");

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
