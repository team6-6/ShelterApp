package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_employee);
        ImageView list_shelters,list_sirens,map,requests,requests_admin,numbers,contant;
        TextView change,logout ;

        list_shelters=(ImageView)findViewById(R.id.Shelterlist) ;
        list_sirens= (ImageView)findViewById(R.id.Sirenlist);
        map= (ImageView)findViewById(R.id.MapOption);
        requests=(ImageView)findViewById(R.id.Requests);
        requests_admin=(ImageView)findViewById(R.id.Requests_Admin);
        numbers= (ImageView)findViewById(R.id.EmergencyNumber);
        contant= (ImageView)findViewById(R.id.ContantUs);
        change= (TextView) findViewById(R.id.changePass);
        logout= (TextView) findViewById(R.id.logout);
        final String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, MapsActivity.class);
                startActivity(first);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, ChangeActivity.class);
                first.putExtra("EXTRA_SESSION_ID2", sessionId);
                startActivity(first);
            }
        });

        list_shelters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, AdminSheltersActivity.class);
                startActivity(first);
            }
        });

        list_sirens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, AdminSirensActivity.class);
                startActivity(first);
            }
        });

        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, RequestsActivity.class);
                startActivity(first);
            }
        });

        requests_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, RequestsAdminActivity.class);
                startActivity(first);
            }
        });

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, NumbersActivity.class);
                startActivity(first);
            }
        });

        contant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, ContactUsActivity.class);
                startActivity(first);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, MainActivity.class);
                startActivity(first);
            }
        });
    }
}
