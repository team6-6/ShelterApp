package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EmergencyMenuActivity extends AppCompatActivity {
    Button addNumber,deleteNumber, viewlist;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_menu);
        addNumber = (Button) findViewById(R.id.AddEmer);
        deleteNumber = (Button) findViewById(R.id.DeleteEmer);
        back= (TextView) findViewById(R.id.backEmer);
        viewlist=(Button) findViewById(R.id.ViewList);

        addNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EmergencyMenuActivity.this, AddEmergencyActivity.class);
                startActivity(intent);
            }
        });

        deleteNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EmergencyMenuActivity.this, DeleteEmergencyActivity.class);
                startActivity(intent);
            }
        });

        viewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencyMenuActivity.this, ViewAdminNumActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencyMenuActivity.this, MainAdminActivity.class);
                startActivity(intent);
            }
        });


    }
}
