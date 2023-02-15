package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class fit4Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnyes,btnno;
    private EditText txtfatmass;
    public static String fatmass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit4);

        toolbar = findViewById(R.id.toolbar);
        btnyes = findViewById(R.id.btngain);
        btnno = findViewById(R.id.btnlose);
        txtfatmass = findViewById(R.id.txtfatmass);

        toolbar.setTitle("Welcome to Fit Diyet");
        setSupportActionBar(toolbar);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fatmass = txtfatmass.getText().toString();
                if (!fatmass.isEmpty()){
                    com.metin.firebaselogindeneme.ValueActivity valueActivityActivity = new com.metin.firebaselogindeneme.ValueActivity();
                    valueActivityActivity.Howfitmass = Integer.parseInt(fatmass);
                    com.metin.firebaselogindeneme.LoginActivity loginActivity = new com.metin.firebaselogindeneme.LoginActivity();
                    loginActivity.fatmasslogin = Integer.parseInt(fatmass);
                    startActivity(new Intent(com.metin.firebaselogindeneme.fit4Activity.this, com.metin.firebaselogindeneme.fit5Activity.class));
                }
                else {
                    Toast.makeText(com.metin.firebaselogindeneme.fit4Activity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}