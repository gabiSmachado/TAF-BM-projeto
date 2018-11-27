package com.example.taf.taf_bm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.taf.taf_bm.R;
import com.example.taf.taf_bm.helpers.SetUpDB;
import com.orm.SugarContext;


public class SplashScreenActivity extends AppCompatActivity {
    private  SetUpDB setUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handle = new Handler();
        setUp = new SetUpDB();
        SugarContext.init(this);
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                setUp.checkDataBase();
                loadMenu();
            }
        }, 3000);
    }

    private void loadMenu() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
