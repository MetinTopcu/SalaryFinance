package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class GainWeightActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnyes;
    private EditText txttall;
    public static String tall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gain_weight);

        toolbar = findViewById(R.id.toolbar);
        btnyes = findViewById(R.id.btngain);
        txttall = findViewById(R.id.txtspending);

        toolbar.setTitle("Travel");
        setSupportActionBar(toolbar);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tall = txttall.getText().toString();
                if (!tall.isEmpty()){
                    startActivity(new Intent(com.metin.firebaselogindeneme.GainWeightActivity.this, com.metin.firebaselogindeneme.LoginActivity.class));
                }
                else {
                    Toast.makeText(com.metin.firebaselogindeneme.GainWeightActivity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}