package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminSheltersActivity extends AppCompatActivity {//עבור מנהל-הוספה ועדכון מקלט
    Button addShelter,deleteShelter,back;
    String sessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminshelters);
        addShelter = (Button) findViewById(R.id.AddBtn);
        deleteShelter = (Button) findViewById(R.id.DeleteBotn);
        back= (Button) findViewById(R.id.goback);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");


        addShelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminSheltersActivity.this, AddShelterActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });

        deleteShelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminSheltersActivity.this, DeleteShelterActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSheltersActivity.this, MainAdminActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });


    }
}
