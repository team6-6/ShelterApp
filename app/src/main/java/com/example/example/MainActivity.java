package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

    User user=new User();
    EditText usernameId,passwordId;
    Button loginId,registerId;
    TextView forgot;
    Intent intentq;

    public FirebaseFirestore db;
    private Checkfunction checkfunction=new Checkfunction();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

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
                setUserInfo(user,pwd);
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
                                            CheckPermissions(permission);
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserInfo(String name, String pas) {

        this.user.setName(name);
        this.user.setPassword(pas);

    }

    public void setUserPermission(String per) {
        this.user.setPermission(per);
    }

    public void putIntent(Class activity){
        intentq=new Intent(this,activity);
        CheckLogin(intentq);
        startActivity(intentq);
        finish();
    }

    public void CheckLogin(Intent i){
        i.putExtra("EXTRA_SESSION_ID",user.name);
    }

    public String CheckPermissions(String permission){
        if(permission.equals("A")){
            setUserPermission("A");
            putIntent(MainAdminActivity.class);
            return "Admin User";
        }
        else if(permission.equals("B")){
            setUserPermission("B");
            putIntent(MainEmployeeActivity.class);
            return "Employee User";

        }
        else if(permission.equals("C")){
            setUserPermission("C");
            putIntent(MainCivilianActivity.class);
            return "Civilian User";
        }
        return "NULL";
    }
}

