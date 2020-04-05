package com.example.shelter4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText userId, passwordId, confirmId;
    Button registerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        db = new DatabaseHelper(this);
        userId = (EditText) findViewById(R.id.username);
        passwordId = (EditText) findViewById(R.id.password);
        confirmId = (EditText) findViewById(R.id.confirm);
        registerId = (Button) findViewById(R.id.registerBtn);

        Intent intent = getIntent();
/*
        registerId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = userId.getText().toString().trim();
                String pwd = passwordId.getText().toString().trim();
                String cfrm = confirmId.getText().toString().trim();
                Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
                Pattern lowerCasePatten = Pattern.compile("[a-z ]");
                Pattern digitCasePatten = Pattern.compile("[0-9 ]");

                if (user.equals("") || pwd.equals("")||cfrm.equals("")){
                    Toast.makeText(RegisterActivity.this, "Field are empty !", Toast.LENGTH_SHORT).show();
                }
                else if (user.length() <3 ) {
                    Toast.makeText(RegisterActivity.this, "user lenght must be atleast 3 character !!", Toast.LENGTH_SHORT).show();
                }
                else if (pwd.length() < 8 || pwd.length() > 12) {
                    Toast.makeText(RegisterActivity.this, "Password lenght must be 8-12 character !!", Toast.LENGTH_SHORT).show();
                }
                else if (!UpperCasePatten.matcher(pwd).find() && !lowerCasePatten.matcher(pwd).find() && !digitCasePatten.matcher(pwd).find()) {
                    Toast.makeText(RegisterActivity.this, "Password must contains only letters & digits !!", Toast.LENGTH_SHORT).show();

                }
                else if (!UpperCasePatten.matcher(pwd).find()) {
                    Toast.makeText(RegisterActivity.this, "Password must have atleast one upper character !!", Toast.LENGTH_SHORT).show();

                }
                else if (!lowerCasePatten.matcher(pwd).find()) {
                    Toast.makeText(RegisterActivity.this, "Password must have atleast one lower character !!", Toast.LENGTH_SHORT).show();

                }
                else if (!digitCasePatten.matcher(pwd).find()) {
                    Toast.makeText(RegisterActivity.this, "Password must have atleast one digit !!", Toast.LENGTH_SHORT).show();

                }

                else {

                    if (pwd.equals(cfrm)) {
                        long val = db.addUser(user, pwd);
                        if (val > 0) {
                            Toast.makeText(RegisterActivity.this, "You have registered !", Toast.LENGTH_SHORT).show();
                            Intent Tologin = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(Tologin);
                        } else {
                            Toast.makeText(RegisterActivity.this, "User already exist !", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password not matching !", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    */
    }
    public void OnReg(View view){

        String user = userId.getText().toString().trim();
            String pwd = passwordId.getText().toString().trim();
            String cfrm = confirmId.getText().toString().trim();
            String type = "register";

            BackgroundWorker back = new BackgroundWorker(this);
            back.execute(type,user,pwd);
        Toast.makeText(RegisterActivity.this, "Password not matching !", Toast.LENGTH_SHORT).show();


    }
}