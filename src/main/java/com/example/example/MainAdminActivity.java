package com.example.example;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        ImageView list,map,edit,numbers,rate,contant;
        TextView change ;


        map= (ImageView)findViewById(R.id.MapOption);
        list= (ImageView)findViewById(R.id.Shelterlist);
        edit= (ImageView)findViewById(R.id.EditUserAdmin);
        numbers= (ImageView)findViewById(R.id.EmergencyNumber);
        rate= (ImageView)findViewById(R.id.RateUs );
        contant= (ImageView)findViewById(R.id.ContantUs);
        change= (TextView) findViewById(R.id.changePass);
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

                Intent first = new Intent(MainAdminActivity.this, SheltersActivity.class);
                startActivity(first);
            }
        });

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainAdminActivity.this, NumbersActivity.class);
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

    }
}
