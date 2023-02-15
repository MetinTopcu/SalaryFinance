package com.metin.salaryfinance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class SaveFragment extends Fragment {

    private static final int REQUEST_CODE = 101;
    private Uri imageUri;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference mRef;
    StorageReference StorgaeRef;
    ProgressDialog mLoadingBar;
    Map<String,Object> bilgiler = new HashMap<>();
    private Button btnasilsave;
    CircleImageView profileasil_image;
    private EditText editusername;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_save,container,false);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mRef = FirebaseDatabase.getInstance().getReference().child("Users");
        StorgaeRef = FirebaseStorage.getInstance().getReference().child("ProfileImage");
        mLoadingBar = new ProgressDialog(getContext());
        btnasilsave = rootView.findViewById(R.id.btnasilsave);
        profileasil_image = rootView.findViewById(R.id.profileasil_image);
        editusername = rootView.findViewById(R.id.editusername);
        profileasil_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        btnasilsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveData();
            }
        });
        return rootView;
    }

    private void SaveData(){
        if (imageUri == null) {

            if (editusername != null){
                String username = editusername.getText().toString();
                bilgiler.put("username",username);
            }
            else {
                mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String username = snapshot.child("username").getValue().toString();
                        bilgiler.put("username",username);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            mRef.child(mUser.getUid()).updateChildren(bilgiler);

            FragmentManager manager = getFragmentManager();
            com.metin.firebaselogindeneme.SaveFragment fa = (com.metin.firebaselogindeneme.SaveFragment) manager.findFragmentByTag("A");
            FragmentTransaction transaction = manager.beginTransaction();
            if (fa != null){
                transaction.remove(fa);
                transaction.commit();
            }
            else {
                Toast.makeText(getActivity(), "Fragment Don't Exit", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            mLoadingBar.setTitle("adding Setup Profile");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            StorgaeRef.child(mUser.getUid()).putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){
                        StorgaeRef.child(mUser.getUid()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                if (editusername != null){
                                    String username = editusername.getText().toString();
                                    bilgiler.put("username",username);
                                }
                                else {
                                    mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String username = snapshot.child("username").getValue().toString();
                                            bilgiler.put("username",username);
                                        }
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                                bilgiler.put("profileImage",uri.toString());

                                mRef.child(mUser.getUid()).updateChildren(bilgiler).addOnSuccessListener(new OnSuccessListener() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        bilgiler.put("okeimage","1");
                                        mRef.child(mUser.getUid()).updateChildren(bilgiler);

                                        FragmentManager manager = getFragmentManager();
                                        com.metin.firebaselogindeneme.SaveFragment fa = (com.metin.firebaselogindeneme.SaveFragment) manager.findFragmentByTag("A");
                                        FragmentTransaction transaction = manager.beginTransaction();
                                        if (fa != null){
                                            transaction.remove(fa);
                                            transaction.commit();
                                        }
                                        else {
                                            Toast.makeText(getActivity(), "Fragment Don't Exit", Toast.LENGTH_SHORT).show();
                                        }
                                        mLoadingBar.dismiss();
                                        Toast.makeText(getActivity(), "Setup Profile Completed", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        mLoadingBar.dismiss();
                                        Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            profileasil_image.setImageURI(imageUri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        mRef.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.child("username").getValue().toString();
                editusername.setText(username);
                String okemi = snapshot.child("okeimage").getValue().toString();
                if (okemi.equals("1")){
                    String profileImageUrlV = snapshot.child("profileImage").getValue().toString();
                    Picasso.get().load(profileImageUrlV).into(profileasil_image);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}