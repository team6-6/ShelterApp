package com.example.shelter4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        EditText username,oldpwd,newpwd,comfirmpwd;
        Button change;

        username=(EditText)findViewById(R.id.usernameChange);
        oldpwd=(EditText)findViewById(R.id.Oldpassword);
        newpwd=(EditText)findViewById(R.id.newPwd);
        comfirmpwd=(EditText)findViewById(R.id.confirmNewPwd);
        change=(Button) findViewById(R.id.createnewpwd);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //good
                Toast.makeText(ChangeActivity.this,"Password change successfully  !",Toast.LENGTH_SHORT).show();
                Intent first = new Intent(ChangeActivity.this, MainActivity.class);
                startActivity(first);
             //לעשות בדיקות

            }
        });

    }
}
