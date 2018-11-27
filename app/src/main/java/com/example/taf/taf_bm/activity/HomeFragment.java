package com.example.taf.taf_bm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taf.taf_bm.R;


public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        getActivity().setTitle(R.string.app_name);
        TextView textView = view.findViewById(R.id.description);
        textView.setText("Simule seu Teste de Aptidão Física, com o Simulador TAF BM.\n\n" +
                "Baseado nos testes aplicados na Brigada Militar do Rio Grande do Sul.");
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        //getActivity().setTitle("");
    }
}
