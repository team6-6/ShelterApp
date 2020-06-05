package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

        public FirebaseFirestore db = FirebaseFirestore.getInstance();
        public FirebaseFirestore db2 = FirebaseFirestore.getInstance();
        private Checkfunction checkfunction=new Checkfunction();


    private static final String TAG = "RegisterActivity";
        EditText userId, passwordId, confirmId, q1, q2;
        Button registerId;
        boolean flag=true;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            userId = (EditText) findViewById(R.id.username);
            passwordId = (EditText) findViewById(R.id.password);
            confirmId = (EditText) findViewById(R.id.confirm);
            registerId = (Button) findViewById(R.id.registerBtn);
            q1=(EditText) findViewById(R.id.confirm2);
            q2=(EditText) findViewById(R.id.confirm3);
            registerId.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addUser();
                }
            });

        }

        protected void addUser(){

            final String user = userId.getText().toString().trim();
            String pwd = passwordId.getText().toString().trim();
            String cfrm = confirmId.getText().toString().trim();
            String quetion1=q1.getText().toString().trim();
            String quetion2=q2.getText().toString().trim();
            Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
            Pattern lowerCasePatten = Pattern.compile("[a-z ]");
            Pattern digitCasePatten = Pattern.compile("[0-9 ]");

            if (checkfunction.notEmpty(user)==1 || checkfunction.notEmpty(pwd)==1 ||checkfunction.notEmpty(cfrm)==1){
                Toast.makeText(RegisterActivity.this, "Field are empty !", Toast.LENGTH_SHORT).show();
            }
            else if(checkfunction.notEmpty(quetion1)==1||checkfunction.notEmpty(quetion2)==1){
                Toast.makeText(RegisterActivity.this, "Field are empty !", Toast.LENGTH_SHORT).show();
            }
            else if (checkfunction.RangeValue(3,user)==true ) {
                Toast.makeText(RegisterActivity.this, "user lenght must be atleast 3 character !!", Toast.LENGTH_SHORT).show();
            }
            else if (checkfunction.RangeValues(8,12,pwd)==true) {
                Toast.makeText(RegisterActivity.this, "Password lenght must be 8-12 character !!", Toast.LENGTH_SHORT).show();
            }
            else if (!checkfunction.CheckPassword(UpperCasePatten,pwd) && !checkfunction.CheckPassword(lowerCasePatten,pwd) && !checkfunction.CheckPassword(digitCasePatten,pwd)) {
                Toast.makeText(RegisterActivity.this, "Password must contains only letters & digits !!", Toast.LENGTH_SHORT).show();

            }
            else if (!checkfunction.CheckPassword(UpperCasePatten,pwd)) {
                Toast.makeText(RegisterActivity.this, "Password must have atleast one upper character !!", Toast.LENGTH_SHORT).show();

            }
            else if (!checkfunction.CheckPassword(lowerCasePatten,pwd)) {
                Toast.makeText(RegisterActivity.this, "Password must have atleast one lower character !!", Toast.LENGTH_SHORT).show();

            }
            else if (!checkfunction.CheckPassword(digitCasePatten,pwd)) {
                Toast.makeText(RegisterActivity.this, "Password must have atleast one digit !!", Toast.LENGTH_SHORT).show();

            }
            else {
                try {


                    // Create a new user with a first and last name
                    final Map<String, Object> user_details = new HashMap<>();
                    user_details.put("password", pwd);
                    user_details.put("permission", "C");
                    user_details.put("Father", quetion1);
                    user_details.put("School", quetion2);

                    db.collection("users").document(user).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (!documentSnapshot.exists()) {
                                        db2.collection("users").document(user).set(user_details)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void documentReference) {
                                                        Toast.makeText(RegisterActivity.this, "User registered", Toast.LENGTH_SHORT).show();
                                                        Log.d(TAG, "DocumentSnapshot added with ID: " + user);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error adding document", e);
                                                        Toast.makeText(RegisterActivity.this, "Already Exist!", Toast.LENGTH_SHORT).show();

                                                    }
                                                });
                                    }
                                    else{
                                        Toast.makeText(RegisterActivity.this, "Already Exist!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {

                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, "Already Exist!", Toast.LENGTH_SHORT).show();
                                }
                            });

                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this, "already exist!", Toast.LENGTH_SHORT).show();
                }
            }

        }
}

