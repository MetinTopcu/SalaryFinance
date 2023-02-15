package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SportsChooseActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnyes;
    private EditText txtfatmass;
    public static String fatmass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_choose);

        toolbar = findViewById(R.id.toolbar);
        btnyes = findViewById(R.id.btngain);
        txtfatmass = findViewById(R.id.txtspending2);

        toolbar.setTitle("Rent");
        setSupportActionBar(toolbar);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fatmass = txtfatmass.getText().toString();
                if (!fatmass.isEmpty()){
                    startActivity(new Intent(com.metin.firebaselogindeneme.SportsChooseActivity.this, com.metin.firebaselogindeneme.LoginActivity.class));
                }
                else {
                    Toast.makeText(com.metin.firebaselogindeneme.SportsChooseActivity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}