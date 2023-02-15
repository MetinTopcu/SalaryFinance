package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView textProfile, txtstr;
    public static int loginis;
    public static int fatmasslogin = 0;
    private FloatingActionButton flbt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.toolbar);
        flbt = findViewById(R.id.flabtn);

        flbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.metin.firebaselogindeneme.LoginActivity.this,ProfileActivity.class));
            }
        });
        if (loginis == 0){
            textProfile.setVisibility(View.INVISIBLE);
            txtstr.setVisibility(View.INVISIBLE);
        }
        else {
        }

        toolbar.setTitle("Welcome to FitDiyet");
        setSupportActionBar(toolbar);
    }



    public void eating(View view) {
        startActivity(new Intent(com.metin.firebaselogindeneme.LoginActivity.this,SuggestionsActivity.class));
    }

    public void travel(View view) {
        startActivity(new Intent(com.metin.firebaselogindeneme.LoginActivity.this,GainWeightActivity.class));
    }

    public void rent(View view) {
        startActivity(new Intent(com.metin.firebaselogindeneme.LoginActivity.this,SportsChooseActivity.class));
    }

    public void clothes(View view) {
        startActivity(new Intent(com.metin.firebaselogindeneme.LoginActivity.this,WinWeightActivity.class));
    }

    public void kids(View view) {
        startActivity(new Intent(com.metin.firebaselogindeneme.LoginActivity.this,KeepFitActivity.class));
    }

    public void general(View view) {
        startActivity(new Intent(com.metin.firebaselogindeneme.LoginActivity.this,LoseWeightActivity.class));
    }

    public void education(View view) {
        startActivity(new Intent(com.metin.firebaselogindeneme.LoginActivity.this,BodyMassIndexActivity.class));
    }

    public void transport(View view) {
        startActivity(new Intent(com.metin.firebaselogindeneme.LoginActivity.this,SportsProgramActivity.class));
    }

    public void health(View view) {
        startActivity(new Intent(com.metin.firebaselogindeneme.LoginActivity.this, com.metin.firebaselogindeneme.SportsProgram1Activity.class));

    }
}