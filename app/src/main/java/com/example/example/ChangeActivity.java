package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChangeActivity extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
     EditText userChange,Oldpass,newPass,confirmnewPass;
     Button changepwd;
     User user1=new User();
    private Checkfunction checkfunction=new Checkfunction();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        Oldpass=(EditText)findViewById(R.id.Oldpassword);
        newPass=(EditText)findViewById(R.id.newPwd);
        confirmnewPass=(EditText)findViewById(R.id.confirmNewPwd);
        changepwd=(Button)findViewById(R.id.createnewpwd);
        final String user = getIntent().getStringExtra("EXTRA_SESSION_ID");
        user1.setName(user);

        changepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // final String user = userChange.getText().toString().trim();
                final String old = Oldpass.getText().toString().trim();
                final String newpwd = newPass.getText().toString().trim();
                final String confirm = confirmnewPass.getText().toString().trim();

                if (checkfunction.notEmpty(old)==1|| checkfunction.notEmpty(newpwd)==1|| checkfunction.notEmpty(confirm)==1) {
                    Toast.makeText(ChangeActivity.this, "One or more field are empty !", Toast.LENGTH_SHORT).show();
                } else {


                    db.collection("users").document(user).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        String oldPws = documentSnapshot.getString("password");
                                        final String permission=documentSnapshot.getString("permission");
                                        if (old.equals(oldPws)) {
                                            if(checkfunction.SamePassword(newpwd,confirm)) {
                                                DocumentReference db1=db.collection("users").document(user);
                                                db1.update(
                                                        "password",newpwd
                                                ).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            Toast.makeText(ChangeActivity.this, "its been update!", Toast.LENGTH_SHORT).show();
                                                            if(permission.equals("A")){
                                                                Intent intent = new Intent(ChangeActivity.this, MainAdminActivity.class);
                                                                intent.putExtra("EXTRA_SESSION_ID", user);

                                                                startActivity(intent);
                                                            }
                                                            else if(permission.equals("B")){
                                                                Intent intent = new Intent(ChangeActivity.this, MainEmployeeActivity.class);
                                                                intent.putExtra("EXTRA_SESSION_ID", user);

                                                                startActivity(intent);
                                                            }
                                                            else if(permission.equals("C")){
                                                                Intent intent = new Intent(ChangeActivity.this, MainCivilianActivity.class);
                                                                intent.putExtra("EXTRA_SESSION_ID", user);

                                                                startActivity(intent);
                                                            }
                                                        }
                                                        else {
                                                            Toast.makeText(ChangeActivity.this, "something wrong ! try again!", Toast.LENGTH_SHORT).show();

                                                        }

                                                    }
                                                });


                                            }else{
                                                Toast.makeText(ChangeActivity.this, "password dont match!", Toast.LENGTH_SHORT).show();
                                            }

                                        } else {
                                            Toast.makeText(ChangeActivity.this, "Wrong old password!", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ChangeActivity.this, "username not exist!", Toast.LENGTH_SHORT).show();

                                }
                            });


                }


            }
        });

    }

    public User getUser1() {
        return user1;
    }
}
