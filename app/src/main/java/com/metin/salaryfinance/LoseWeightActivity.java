package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LoseWeightActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btngain;
    private EditText txtexercise;
    public static String purpose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose_weight);

        toolbar = findViewById(R.id.toolbar);
        btngain = findViewById(R.id.btngain);
        txtexercise = findViewById(R.id.txtspending6);

        toolbar.setTitle("General");
        setSupportActionBar(toolbar);

        btngain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                purpose = txtexercise.getText().toString();
                if (!purpose.isEmpty()){
                    startActivity(new Intent(com.metin.firebaselogindeneme.LoseWeightActivity.this, com.metin.firebaselogindeneme.LoginActivity.class));
                }
                else {
                    Toast.makeText(com.metin.firebaselogindeneme.LoseWeightActivity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}