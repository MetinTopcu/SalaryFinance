package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SuggestionsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnyes;
    private EditText txtkilos;
    public static String kilos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Eating");
        setSupportActionBar(toolbar);

        btnyes = findViewById(R.id.btngain);
        txtkilos = findViewById(R.id.txtspending1);;

        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kilos = txtkilos.getText().toString();
                if (!kilos.isEmpty()){
                    startActivity(new Intent(com.metin.firebaselogindeneme.SuggestionsActivity.this, com.metin.firebaselogindeneme.LoginActivity.class));
                }
                else{
                    Toast.makeText(com.metin.firebaselogindeneme.SuggestionsActivity.this, "İçleri boş method", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}