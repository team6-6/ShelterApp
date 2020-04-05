package com.example.shelter4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        EditText username,father,school;
        Button send;
        final TextView showpass ;

        username=(EditText)findViewById(R.id.usernameForgot);
        father=(EditText)findViewById(R.id.Fathername);
        school=(EditText)findViewById(R.id.school);
        send= (Button)findViewById(R.id.SendForgot);
        showpass= (TextView) findViewById(R.id.Showpass);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            //לקחת מידע ולהשוות אם  תואם אם כן להחזיר ססמא אם לא להציג שגיאה מתאימה

                String password ="Your password : " + "1234";
                showpass.setText(password);

            }
        });


    }
}
