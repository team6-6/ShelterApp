package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminSirensActivity extends AppCompatActivity {
    Button addSiren,deleteSiren;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminsirens);
        addSiren = (Button) findViewById(R.id.Addbotn);
        deleteSiren = (Button) findViewById(R.id.DeleteBotn);
        back= (TextView) findViewById(R.id.goback);

        addSiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminSirensActivity.this, AddSirenActivity.class);
                startActivity(intent);
            }
        });

        deleteSiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminSirensActivity.this, DeleteSirenActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSirensActivity.this, MainAdminActivity.class);
                startActivity(intent);
            }
        });
    }
}

