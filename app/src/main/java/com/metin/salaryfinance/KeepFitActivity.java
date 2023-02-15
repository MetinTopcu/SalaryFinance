package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class KeepFitActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnyes,btnno;
    private EditText txtexercise;
    public static String exercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_fit);

        toolbar = findViewById(R.id.toolbar);
        btnyes = findViewById(R.id.btngain);
        btnno = findViewById(R.id.btnlose);
        txtexercise = findViewById(R.id.txtspending5);

        toolbar.setTitle("Kids");
        setSupportActionBar(toolbar);
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise = txtexercise.getText().toString();
                if (!exercise.isEmpty()){
                    startActivity(new Intent(com.metin.firebaselogindeneme.KeepFitActivity.this, com.metin.firebaselogindeneme.LoginActivity.class));
                }
                else {
                    Toast.makeText(com.metin.firebaselogindeneme.KeepFitActivity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}