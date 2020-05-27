package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RequestCivilianActivity extends AppCompatActivity {


    Button search,create,back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_civilian);

        search= (Button)findViewById(R.id.SearchButton);
        create= (Button)findViewById(R.id.CreateButton);
        back=  (Button)findViewById(R.id.baaack);



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestCivilianActivity.this, CreateRequestCivilianActivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(RequestCivilianActivity.this, SearchRequestCivilianActivity.class);
                //startActivity(intent);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestCivilianActivity.this, MainCivilianActivity.class);
                startActivity(intent);
            }
        });
    }



}
