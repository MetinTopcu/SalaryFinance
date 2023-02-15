package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class fit5Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnyes;
    public static String frequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit5);

        toolbar = findViewById(R.id.toolbar);
        btnyes = findViewById(R.id.btngain);

        toolbar.setTitle("Welcome to Fit Diyet");
        setSupportActionBar(toolbar);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequency="lots";
                startActivity(new Intent(com.metin.firebaselogindeneme.fit5Activity.this, com.metin.firebaselogindeneme.fit6Activity.class));
            }
        });
    }
}