package com.example.taf.taf_bm.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.taf.taf_bm.R;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title));
        findViewById(R.id.option1).setOnClickListener(this);
        findViewById(R.id.option2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.option1:
                optionDialog();
                break;
            case R.id.option2:
                break;
        }
    }

    public void optionDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] opcoes = {"TAF Normal", "TAF Aluno BM","AFE Lesão Membros Sup.","AFE Lesão Membros Inf.","AFE Lesão Membros Sup. e Inf.",
                "AFE Lesão na Coluna Vertebral","AFE Lesão Memb. Sup. e Coluna Vertebral","AFE Lesão Memb. Inf. e Coluna Vertebral",
                "AFE Problemas Cardiacos"};
        builder.setTitle("Selecione um teste:")
                .setSingleChoiceItems(opcoes, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface d, int n) {
                        ListView lw = ((AlertDialog)d).getListView();
                        int item = lw.getSelectedItemPosition();
                        Intent i;
                        switch (n){
                            case 0:
                                i = new Intent(IndexActivity.this,TafActivity.class);
                                i.putExtra("taf_name","TAF Normal");
                                i.putExtra("test_type",1);
                                startActivity(i);
                                break;
                            case 1:
                                i = new Intent(IndexActivity.this,TafActivity.class);
                                i.putExtra("taf_name","TAF Aluno BM");
                                i.putExtra("test_type",2);
                                startActivity(i);
                                break;
                            case 2:
                                i = new Intent(IndexActivity.this,UpperLimbInjuriesActivity.class);
                                startActivity(i);
                                break;
                            case 3:
                                i = new Intent(IndexActivity.this,SitupsAndMiles.class);
                                i.putExtra("test","AFE Lesão Membros Inf.");
                                startActivity(i);
                                break;
                            case 4:
                                i = new Intent(IndexActivity.this,SitupsAndMiles.class);
                                i.putExtra("test","AFE Lesão Membros Sup. e Inf.");
                                startActivity(i);
                                break;
                            case 5:
                                i = new Intent(IndexActivity.this,MilesActivity.class);
                                i.putExtra("test","AFE Lesão na Coluna Vertebral");
                                startActivity(i);
                                break;
                            case 6:
                                i = new Intent(IndexActivity.this,MilesActivity.class);
                                i.putExtra("test","AFE Lesão Memb. Sup. e Coluna Vertebral");
                                startActivity(i);
                                break;
                            case 7:
                                i = new Intent(IndexActivity.this,MilesActivity.class);
                                i.putExtra("test","AFE Lesão Memb. Inf. e Coluna Vertebral");
                                startActivity(i);
                                break;
                            case 8:
                                i =  new Intent(IndexActivity.this,MilesActivity.class);
                                startActivity(i);
                                break;
                        }
                        d.dismiss();
                    }
                });
        builder.show();
    }


}
