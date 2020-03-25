package com.example.shelter4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText usernameId,passwordId;
    Button loginId,registerId;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);
        registerId=(Button)findViewById(R.id.registerBtn);
        usernameId=(EditText)findViewById(R.id.username);
        passwordId=(EditText)findViewById(R.id.password);
        loginId=(Button)findViewById(R.id.loginBtn);

        loginId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String user = usernameId.getText().toString().trim();
                String pwd = passwordId.getText().toString().trim();
                Boolean res = db.checkUser(user , pwd);

                if (user.equals("") || pwd.equals("")){
                    Toast.makeText(MainActivity.this, "One or more field are empty !", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (res == true) {
                        Toast.makeText(MainActivity.this, "User dose'nt exist !", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean passmatch = db.ReturnPasswordMatch(user,pwd);
                        if(passmatch==true) {
                            Toast.makeText(MainActivity.this, "Wrong password !", Toast.LENGTH_SHORT).show();
                        }
                        else{
                         //   Toast.makeText(MainActivity.this, "good login !", Toast.LENGTH_SHORT).show();


                            Intent first = new Intent(MainActivity.this, FirstActivity.class);
                            startActivity(first);


                        }

                    }
                }
            }

        });

        registerId.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent=new Intent(MainActivity.this, RegisterActivity.class );
                startActivity(intent);
             //   Toast.makeText(MainActivity.this,"dfvdsggfv !",Toast.LENGTH_SHORT).show();

            }
        });

    }


}
