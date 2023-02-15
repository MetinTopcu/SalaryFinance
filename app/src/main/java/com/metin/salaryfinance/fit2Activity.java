package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class fit2Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnyes,btnno;
    private EditText txtkilos;
    public static String kilos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit2);

        toolbar = findViewById(R.id.toolbar);
        btnyes = findViewById(R.id.btngain);
        btnno = findViewById(R.id.btnlose);
        txtkilos = findViewById(R.id.txthowold);

        toolbar.setTitle("Welcome to Fit Diyet");
        setSupportActionBar(toolbar);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kilos = txtkilos.getText().toString();
                if (!kilos.isEmpty()){
                    startActivity(new Intent(com.metin.firebaselogindeneme.fit2Activity.this, com.metin.firebaselogindeneme.fit3Activity.class));
                }
                else{
                    Toast.makeText(com.metin.firebaselogindeneme.fit2Activity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}