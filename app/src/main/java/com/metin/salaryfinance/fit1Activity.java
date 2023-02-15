package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class fit1Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnyes,btnno;
    private EditText txtold;
    public static String howold;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit1);

        toolbar = findViewById(R.id.toolbar);
        btnyes = findViewById(R.id.btngain);
        btnno= findViewById(R.id.btnlose);
        txtold = findViewById(R.id.txtold);

        toolbar.setTitle("Welcome to Fit Diyet");
        setSupportActionBar(toolbar);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howold = txtold.getText().toString();
                if (!howold.isEmpty()){
                    startActivity(new Intent(com.metin.firebaselogindeneme.fit1Activity.this, com.metin.firebaselogindeneme.RegisterActivity.class));
                }
                else{
                    Toast.makeText(com.metin.firebaselogindeneme.fit1Activity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}