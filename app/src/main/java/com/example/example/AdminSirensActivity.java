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
    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminsirens);
        addSiren = (Button) findViewById(R.id.Addbotn);
        deleteSiren = (Button) findViewById(R.id.DeleteBotn);
        back= (TextView) findViewById(R.id.goback);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

        addSiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminSirensActivity.this, AddSirenActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });

        deleteSiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminSirensActivity.this, DeleteSirenActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSirensActivity.this, MainAdminActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });
    }
}

