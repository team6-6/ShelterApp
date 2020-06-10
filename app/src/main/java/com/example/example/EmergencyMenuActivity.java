package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EmergencyMenuActivity extends AppCompatActivity {
    Button addNumber,deleteNumber, viewlist,back;
    String sessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_menu);
        addNumber = (Button) findViewById(R.id.AddEmer);
        deleteNumber = (Button) findViewById(R.id.DeleteEmer);
        back= (Button) findViewById(R.id.backEmer);
        viewlist=(Button) findViewById(R.id.ViewList);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

        addNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EmergencyMenuActivity.this, AddEmergencyActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });

        deleteNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EmergencyMenuActivity.this, DeleteEmergencyActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });

        viewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencyMenuActivity.this, ViewAdminNumActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(EmergencyMenuActivity.this, MainAdminActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID", sessionId);
                    startActivity(intent);

            }
        });


    }
}
