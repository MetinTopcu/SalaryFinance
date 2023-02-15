package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SportsProgramActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnyes;
    private EditText txttall;
    public static String transport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_program);

        toolbar = findViewById(R.id.toolbar);
        btnyes = findViewById(R.id.btngain);
        txttall = findViewById(R.id.txtspending7);

        toolbar.setTitle("Transport");
        setSupportActionBar(toolbar);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transport = txttall.getText().toString();
                if (!transport.isEmpty()){
                    startActivity(new Intent(com.metin.firebaselogindeneme.SportsProgramActivity.this, com.metin.firebaselogindeneme.LoginActivity.class));
                }
                else {
                    Toast.makeText(com.metin.firebaselogindeneme.SportsProgramActivity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}