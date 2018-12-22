package com.example.taf.taf_bm.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taf.taf_bm.R;

public class InformationActivity extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information, container, false);
        getActivity().setTitle("Sobre");
        TextView t = view.findViewById(R.id.title);
        t.setText("As Avaliação de Aptidão Física são conjunto de testes físicos utilizados para " +
                "medir a capacidade dos Militares do Rio Grande do Sul em condições normais e especiais de saúde. " +
                "Para mais informações, leia os seguintes documentos:");
        TextView desc = view.findViewById(R.id.description);
        String value = "<html> <a href=\"https://drive.google.com/open?id=1xQqNgnISrpHwfNqgGAxXCqUl1o5clG5a\">Nota de instrução</a><br></html><br>" +
        "<html> <a href=\"https://drive.google.com/open?id=1qtqhnop5tUyVYiK7mJlpHVB6bjFL4bsT\">Descrição dos Exercícios</a><br></html><br>" +
        "<html> <a href=\"https://drive.google.com/open?id=1CNrUiYzFW8YIoWagZ40eKOz5opFjdN_-\">Avaliação Física Especial - AFE</a><br></html><br>" +
                "<html> <a href=\"https://drive.google.com/open?id=1y_w0mQhZX7RXHM48RaEGI3NAkF4YjrIp\">Tabela Única de Avaliação Física</a></body> </html>";
        desc.setText(Html.fromHtml(value));
        desc.setMovementMethod(LinkMovementMethod.getInstance());

        TextView text = view.findViewById(R.id.text);
        text.setText("Simulador TAF BM foi desenvolvido como trabalho de conclusão de curso, do curso de Análise e Desenvolvimento de Sistemas, " +
                "do Instituto Federal do Rio Grande do Sul Campus Osório.");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        //getActivity().setTitle("");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}