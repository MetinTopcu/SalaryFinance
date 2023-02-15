package com.metin.salaryfinance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private Button btnRegLogin;

    private LoginButton login;
    private Toolbar toolbar;
    public Uri imageUri;

    private Button btnkayitolmadan , btnregister;
    private EditText inputPassword, inputEmail;

    private FirebaseAuth mAuth;
    private SignInButton btnGoogle;

    GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 101;

    CallbackManager callbackManager;

    FirebaseUser mUser;
    DatabaseReference mRef;

    ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");

        btnRegLogin = findViewById(R.id.btnRegLogin);
        btnregister = findViewById(R.id.btnRegister);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnGoogle = findViewById(R.id.btnGoogle);
        mLoadingBar = new ProgressDialog(this);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (email.isEmpty() || !email.contains("@")){
                    showError(inputEmail,"Email is not valid");
                }
                else if (password.isEmpty() || password.length()<7){
                    showError(inputPassword,"Password must be 7 character");
                }
                else {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Successfully Registration",Toast.LENGTH_SHORT).show();
                                LoginActivity loginActivity = new LoginActivity();
                                loginActivity.loginis = 1;

                                btnregister.setVisibility(View.INVISIBLE);
                                btnRegLogin.setVisibility(View.VISIBLE);
                                //Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                //startActivity(intent);
                            }
                            else {
                                Toast.makeText(RegisterActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        btnRegLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(RegisterActivity.this,GoogleSignInActivity.class);
                //startActivity(intent);
                signIn();
            }
        });

        //printKeyHash();
        login = findViewById(R.id.login);
        toolbar = findViewById(R.id.toolbar);
        btnkayitolmadan = findViewById(R.id.btnkayitolmadan);

        toolbar.setTitle("Fit Diyet");
        setSupportActionBar(toolbar);

        callbackManager = CallbackManager.Factory.create();

        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //LoginManager.getInstance().logInWithReadPermissions(RegisterActivity.this, Arrays.asList("public_profile"));
                handleFacebookAccessToken(loginResult.getAccessToken());
                //AccessToken accessToken = AccessToken.getCurrentAccessToken();
                //boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
            }

            @Override
            public void onCancel() {
                Toast.makeText(com.metin.firebaselogindeneme.RegisterActivity.this, "facebook:onCancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(com.metin.firebaselogindeneme.RegisterActivity.this, "facebook:onError", Toast.LENGTH_SHORT).show();
            }
        });


        btnkayitolmadan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.metin.firebaselogindeneme.LoginActivity loginActivity = new com.metin.firebaselogindeneme.LoginActivity();
                loginActivity.loginis = 0;
                startActivity(new Intent(com.metin.firebaselogindeneme.RegisterActivity.this, com.metin.firebaselogindeneme.ValueActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void SaveData(){
        String howold = fit1Activity.howold;
        String kilos = com.metin.firebaselogindeneme.fit2Activity.kilos;
        String tall = com.metin.firebaselogindeneme.fit3Activity.tall;
        String fatmass= fit4Activity.fatmass;
        String frequency = fit5Activity.frequency;
        String exercise = com.metin.firebaselogindeneme.fit6Activity.exercise;
        String purpose = com.metin.firebaselogindeneme.fit7Activity.purpose;

        if (howold.isEmpty() || kilos.isEmpty() || tall.isEmpty() || fatmass.isEmpty() || frequency.isEmpty() || exercise.isEmpty()
        || purpose.isEmpty()) {
            Toast.makeText(com.metin.firebaselogindeneme.RegisterActivity.this, "İçleri boş Register Activity SaveData methodu", Toast.LENGTH_SHORT).show();
        }
        else {
            mLoadingBar.setTitle("adding Setup Profile");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            HashMap hashMap = new HashMap();
            hashMap.put("howold",howold);
            hashMap.put("kilos",kilos);
            hashMap.put("tall",tall);
            hashMap.put("fatmass",fatmass);
            hashMap.put("frequency",frequency);
            hashMap.put("exercise",exercise);
            hashMap.put("purpose",purpose);

            hashMap.put("username","Users");
            hashMap.put("okeimage","0");
            hashMap.put("okeusername","0");
            hashMap.put("status","offline");

            mAuth = FirebaseAuth.getInstance();
            mUser = mAuth.getCurrentUser();
            mRef = FirebaseDatabase.getInstance().getReference().child("Users");
            if (mUser == null){
                SenUserToLoginActivity();
            }
            else {
                mRef.child(mUser.getUid()).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Intent intent = new Intent(com.metin.firebaselogindeneme.RegisterActivity.this, com.metin.firebaselogindeneme.ValueActivity.class);
                        startActivity(intent);
                        mLoadingBar.dismiss();
                        Toast.makeText(com.metin.firebaselogindeneme.RegisterActivity.this, "Setup Profile completed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mLoadingBar.dismiss();
                        Toast.makeText(com.metin.firebaselogindeneme.RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }

    }

    private void SenUserToLoginActivity(){
        Intent intent = new Intent(com.metin.firebaselogindeneme.RegisterActivity.this, com.metin.firebaselogindeneme.ValueActivity.class);
        startActivity(intent);
        finish();
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            com.metin.firebaselogindeneme.LoginActivity loginActivity = new com.metin.firebaselogindeneme.LoginActivity();
                            loginActivity.loginis = 1;
                            SaveData();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(com.metin.firebaselogindeneme.RegisterActivity.this, ""+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void showError(EditText input, String s){
        input.setError(s);;
        input.requestFocus();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            firebaseAuthWithGoogle(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(com.metin.firebaselogindeneme.RegisterActivity.this,user.getEmail()+user.getDisplayName(),Toast.LENGTH_SHORT).show();
                            com.metin.firebaselogindeneme.LoginActivity loginActivity = new com.metin.firebaselogindeneme.LoginActivity();
                            loginActivity.loginis = 1;
                            SaveData();
                        } else {
                            Toast.makeText(com.metin.firebaselogindeneme.RegisterActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Intent intent = new Intent(com.metin.firebaselogindeneme.RegisterActivity.this, com.metin.firebaselogindeneme.ValueActivity.class);
        startActivity(intent);
    }

}