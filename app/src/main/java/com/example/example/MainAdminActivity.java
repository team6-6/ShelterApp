package com.example.example;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdminActivity extends AppCompatActivity {

  //  Intent first1;
    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        ImageView list,list_sirens,map,edit,numbers,reports,contant;
        TextView change,logout ;


        map= (ImageView)findViewById(R.id.MapOption);
        list= (ImageView)findViewById(R.id.Shelterlist);
        list_sirens= (ImageView)findViewById(R.id.Sirenlist);
        edit= (ImageView)findViewById(R.id.EditUserAdmin);
        numbers= (ImageView)findViewById(R.id.EmergencyNumber);
        reports= (ImageView)findViewById(R.id.reports);
        change= (TextView) findViewById(R.id.changePass);
        logout= (TextView) findViewById(R.id.logout);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

        contant= (ImageView)findViewById(R.id.ContantUs);


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, MapsActivity.class);
                startActivity(first);
            }
        });

        contant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, ContactUsActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                first.putExtra("per", "A");
                startActivity(first);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, EditUserActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToChangePassword();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, AdminSheltersActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, EmergencyMenuActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, ReportsActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);

                startActivity(first);
            }
        });


        list_sirens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, AdminSirensActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Logout();
            }
        });

    }

    public void setSessionId() {
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
    }

    public Intent ToChangePassword(){
        Intent first1 = new Intent(MainAdminActivity.this, ChangeActivity.class);
        first1.putExtra("EXTRA_SESSION_ID", sessionId);
        startActivity(first1);
        return first1;
    }

    public boolean Logout(){
        try {
            Intent first = new Intent(this, MainActivity.class);
            startActivity(first);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
