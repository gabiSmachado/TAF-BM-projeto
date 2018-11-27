package com.example.taf.taf_bm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.example.taf.taf_bm.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitle("Simulador");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.taf1).setOnClickListener(this);
        findViewById(R.id.taf2).setOnClickListener(this);
        findViewById(R.id.afe1).setOnClickListener(this);
        findViewById(R.id.afe2).setOnClickListener(this);
        findViewById(R.id.afe3).setOnClickListener(this);
        findViewById(R.id.afe4).setOnClickListener(this);
        findViewById(R.id.afe5).setOnClickListener(this);
        findViewById(R.id.afe6).setOnClickListener(this);
        findViewById(R.id.afe7).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.taf1:
                i = new Intent(this,TafActivity.class);
                i.putExtra("taf_name","TAF Normal");
                i.putExtra("test_type",1);
                startActivity(i);
                break;
            case R.id.taf2:
                i = new Intent(this,TafActivity.class);
                i.putExtra("taf_name","TAF Aluno BM");
                i.putExtra("test_type",2);
                startActivity(i);
                break;
            case R.id.afe1:
                i = new Intent(this,UpperLimbInjuriesActivity.class);
                startActivity(i);
                break;
            case R.id.afe2:
                i = new Intent(this,SitupsAndMiles.class);
                i.putExtra("test","AFE Lesão Membros Inf.");
                startActivity(i);
                break;
            case R.id.afe3:
                i = new Intent(this,SitupsAndMiles.class);
                i.putExtra("test","AFE Lesão Membros Sup. e Inf.");
                startActivity(i);
                break;
            case R.id.afe4:
                i = new Intent(this,MilesActivity.class);
                i.putExtra("test","AFE Lesão na Coluna Vertebral");
                startActivity(i);
                break;
            case R.id.afe5:
                i = new Intent(this,MilesActivity.class);
                i.putExtra("test","AFE Lesão Memb. Sup. e Coluna Vertebral");
                startActivity(i);
                break;
            case R.id.afe6:
                i = new Intent(this,MilesActivity.class);
                i.putExtra("test","AFE Lesão Memb. Inf. e Coluna Vertebral");
                startActivity(i);
                break;
            case R.id.afe7:
                i = new Intent(this,HeartProblemsActivity.class);
                startActivity(i);
                break;
        }
    }
}
