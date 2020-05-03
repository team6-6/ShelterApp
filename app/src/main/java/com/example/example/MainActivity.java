package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    EditText usernameId,passwordId;
    Button loginId,registerId;
    TextView change,forgot;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Checkfunction checkfunction=new Checkfunction();
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerId = (Button) findViewById(R.id.registerBtn);
        usernameId = (EditText) findViewById(R.id.username);
        passwordId = (EditText) findViewById(R.id.password);
        loginId = (Button) findViewById(R.id.loginBtn);
        forgot = (TextView) findViewById(R.id.forgot);



        loginId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = usernameId.getText().toString().trim();
                String pwd = passwordId.getText().toString().trim();
                if (checkfunction.notEmpty(user)==1 || checkfunction.notEmpty(pwd)==1){
                    Toast.makeText(MainActivity.this, "One or more field are empty !", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.collection("users").document(user).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        String pass=documentSnapshot.getString("password");
                                        if (pass.equals(passwordId.getText().toString().trim())){
                                            String permission = documentSnapshot.getString("permission");
                                            if(permission.equals("A")){
                                                Intent intent = new Intent(MainActivity.this, MainAdminActivity.class);
                                                intent.putExtra("EXTRA_SESSION_ID", user);
                                                startActivity(intent);
                                            }
                                            else if(permission.equals("B")){
                                                Intent intent = new Intent(MainActivity.this, MainEmployeeActivity.class);
                                                intent.putExtra("EXTRA_SESSION_ID", user);
                                                startActivity(intent);
                                            }
                                            else if(permission.equals("C")){
                                                Intent intent = new Intent(MainActivity.this, MainCivilianActivity.class);
                                                intent.putExtra("EXTRA_SESSION_ID", user);
                                                startActivity(intent);
                                            }

                                        }
                                        else {
                                            Toast.makeText(MainActivity.this, "password incorrect", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                    else{
                                        Toast.makeText(MainActivity.this, "username doesnt exist", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity.this, "username or password incorrect", Toast.LENGTH_SHORT).show();
                                }
                            });
                }


            }


        });

        registerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this, ForgotActivity.class);
                startActivity(intent);
            }
        });



    }

}

