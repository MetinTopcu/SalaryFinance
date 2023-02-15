package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BodyMassIndexActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnyes;
    private EditText txttall;
    public static String education;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_mass_index);

        toolbar = findViewById(R.id.toolbar);
        btnyes = findViewById(R.id.btngain);
        txttall = findViewById(R.id.txtspending3);

        toolbar.setTitle("Education");
        setSupportActionBar(toolbar);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                education = txttall.getText().toString();
                if (!education.isEmpty()){
                    startActivity(new Intent(com.metin.firebaselogindeneme.BodyMassIndexActivity.this, com.metin.firebaselogindeneme.LoginActivity.class));
                }
                else {
                    Toast.makeText(com.metin.firebaselogindeneme.BodyMassIndexActivity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}