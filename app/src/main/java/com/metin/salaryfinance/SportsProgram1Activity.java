package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SportsProgram1Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnyes;
    private EditText txttall;
    public static String health;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_program1);

        toolbar = findViewById(R.id.toolbar);
        btnyes = findViewById(R.id.btngain);
        txttall = findViewById(R.id.txtspending8);

        toolbar.setTitle("Health");
        setSupportActionBar(toolbar);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                health = txttall.getText().toString();
                if (!health.isEmpty()){
                    startActivity(new Intent(com.metin.firebaselogindeneme.SportsProgram1Activity.this, LoginActivity.class));
                }
                else {
                    Toast.makeText(com.metin.firebaselogindeneme.SportsProgram1Activity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}