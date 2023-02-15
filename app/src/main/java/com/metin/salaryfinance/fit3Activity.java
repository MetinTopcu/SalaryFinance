package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class fit3Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnyes,btnno;
    private EditText txttall;
    public static String tall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit3);

        toolbar = findViewById(R.id.toolbar);
        btnyes = findViewById(R.id.btngain);
        btnno = findViewById(R.id.btnlose);
        txttall = findViewById(R.id.txtkilos);

        toolbar.setTitle("Welcome to how many percent covid");
        setSupportActionBar(toolbar);

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tall = txttall.getText().toString();
                if (!tall.isEmpty()){
                    startActivity(new Intent(com.metin.firebaselogindeneme.fit3Activity.this, fit4Activity.class));
                }
                else {
                    Toast.makeText(com.metin.firebaselogindeneme.fit3Activity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}