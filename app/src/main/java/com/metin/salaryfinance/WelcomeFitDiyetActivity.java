package com.metin.salaryfinance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WelcomeFitDiyetActivity extends AppCompatActivity {
    private Toolbar toolbar;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_fit_diyet);

        toolbar = findViewById(R.id.toolbar);
        ValueActivity amICovidActivity = new ValueActivity();
        toolbar.setTitle("Welcome to Fit Diyet");
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (mUser != null){
                    mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()){
                                com.metin.firebaselogindeneme.LoginActivity loginActivity = new com.metin.firebaselogindeneme.LoginActivity();
                                loginActivity.loginis = 1;
                                Intent intent = new Intent(com.metin.firebaselogindeneme.WelcomeFitDiyetActivity.this, com.metin.firebaselogindeneme.LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable,100);
    }

    public void Login(View view) {
        startActivity(new Intent(com.metin.firebaselogindeneme.WelcomeFitDiyetActivity.this, com.metin.firebaselogindeneme.LoginAsilActivity.class));
    }

    public void register(View view) {
        startActivity(new Intent(com.metin.firebaselogindeneme.WelcomeFitDiyetActivity.this, fit1Activity.class));
    }



}