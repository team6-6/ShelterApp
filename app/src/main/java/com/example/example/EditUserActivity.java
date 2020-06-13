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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

public class EditUserActivity extends AppCompatActivity {
    EditText user,pass,per;
    Button back,changeNsave;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userEdit ,  password ,permission,sessionId;
    Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
    Pattern lowerCasePatten = Pattern.compile("[a-z ]");
    Pattern digitCasePatten = Pattern.compile("[0-9 ]");
    String us="users";
    private Checkfunction checkfunction=new Checkfunction();


    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        back= (Button) findViewById(R.id.goback);
        changeNsave= (Button) findViewById(R.id.changeNsave);
        user = (EditText) findViewById(R.id.useredit);
        pass= (EditText) findViewById(R.id.editpass);
        per= (EditText) findViewById(R.id.editper);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");


        changeNsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEdit = user.getText().toString().trim();
                password = pass.getText().toString().trim();
                permission = per.getText().toString().trim();

                if (checkfunction.notEmpty(userEdit)==1){
                    Toast.makeText(EditUserActivity.this, "write user name !", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.collection(us).document(userEdit).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {

                                        if(checkfunction.notEmpty(password)==1  && checkfunction.notEmpty(permission)==1) {
                                            Toast.makeText(EditUserActivity.this, "write atleast one of the changes  !", Toast.LENGTH_SHORT).show();
                                        }
                                        else if (checkfunction.RangeValues(8,12,password)==true) {
                                            Toast.makeText(EditUserActivity.this, "Password lenght must be 8-12 character !!", Toast.LENGTH_SHORT).show();
                                        } else if (!UpperCasePatten.matcher(password).find() && !lowerCasePatten.matcher(password).find() && !digitCasePatten.matcher(password).find()) {
                                            Toast.makeText(EditUserActivity.this, "Password must contains only letters & digits !!", Toast.LENGTH_SHORT).show();

                                        } else if (!UpperCasePatten.matcher(password).find()) {
                                            Toast.makeText(EditUserActivity.this, "Password must have atleast one upper character !!", Toast.LENGTH_SHORT).show();

                                        } else if (!lowerCasePatten.matcher(password).find()) {
                                            Toast.makeText(EditUserActivity.this, "Password must have atleast one lower character !!", Toast.LENGTH_SHORT).show();

                                        } else if (!digitCasePatten.matcher(password).find()) {
                                            Toast.makeText(EditUserActivity.this, "Password must have atleast one digit !!", Toast.LENGTH_SHORT).show();

                                        }
                                        else if (checkfunction.CheckPermission(permission) && !permission.equals("")) {
                                            Toast.makeText(EditUserActivity.this, "Choose only A / B / C", Toast.LENGTH_SHORT).show();
                                        }else if(checkfunction.notEmpty(permission)==0&& checkfunction.notEmpty(password)==0)
                                        {

                                            DocumentReference db1=db.collection(us).document(userEdit);
                                            db1.update(
                                                    "password",password,
                                                    "permission",permission

                                            ).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(EditUserActivity.this, "user detail update", Toast.LENGTH_SHORT).show();

                                                    }
                                                    else{
                                                        Toast.makeText(EditUserActivity.this, "Try again", Toast.LENGTH_SHORT).show();

                                                    }

                                                }
                                            });

                                        }
                                        else if(checkfunction.notEmpty(password)==0 &&  checkfunction.notEmpty(permission)==1)
                                        {

                                            DocumentReference db1=db.collection(us).document(userEdit);
                                            db1.update(
                                                    "password",password

                                            ).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(EditUserActivity.this, "user detail update", Toast.LENGTH_SHORT).show();

                                                    }
                                                    else{
                                                        Toast.makeText(EditUserActivity.this, "Try again", Toast.LENGTH_SHORT).show();

                                                    }

                                                }
                                            });

                                        }
                                        else if(checkfunction.notEmpty(permission)==0 && checkfunction.notEmpty(password)==1)
                                        {

                                            DocumentReference db1=db.collection(us).document(userEdit);
                                            db1.update(
                                                    "permission",permission

                                            ).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(EditUserActivity.this, "user detail update", Toast.LENGTH_SHORT).show();

                                                    }
                                                    else{
                                                        Toast.makeText(EditUserActivity.this, "Try again", Toast.LENGTH_SHORT).show();

                                                    }

                                                }
                                            });

                                        }


                                    }
                                    else{

                                        Toast.makeText(EditUserActivity.this, "enter exist username !", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditUserActivity.this, MainAdminActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });


    }
}
