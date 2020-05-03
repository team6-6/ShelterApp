package com.example.example;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        ImageView list,list_sirens,map,edit,numbers,rate,contant;
        TextView change,logout ;


        map= (ImageView)findViewById(R.id.MapOption);
        list= (ImageView)findViewById(R.id.Shelterlist);
        list_sirens= (ImageView)findViewById(R.id.Sirenlist);
        edit= (ImageView)findViewById(R.id.EditUserAdmin);
        numbers= (ImageView)findViewById(R.id.EmergencyNumber);
        rate= (ImageView)findViewById(R.id.RateUs );
        contant= (ImageView)findViewById(R.id.ContantUs);
        change= (TextView) findViewById(R.id.changePass);
        logout= (TextView) findViewById(R.id.logout);
        final String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, MapsActivity.class);
                startActivity(first);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, EditUserActivity.class);
                startActivity(first);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, ChangeActivity.class);
                first.putExtra("EXTRA_SESSION_ID2", sessionId);
                startActivity(first);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, AdminSheltersActivity.class);
                startActivity(first);
            }
        });

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, EmergencyMenuActivity.class);
                startActivity(first);
            }
        });

        contant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, ContactUsActivity.class);
                startActivity(first);
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, RateActivity.class);
                startActivity(first);
            }
        });

        list_sirens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, AdminSirensActivity.class);
                startActivity(first);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, MainActivity.class);
                startActivity(first);
            }
        });

    }
}
