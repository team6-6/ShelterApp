package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactUsActivity extends AppCompatActivity {
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        back= (Button) findViewById(R.id.goback);

        back.setOnClickListener(new View.OnClickListener() {//לבדוק לגבי כל משתמש
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactUsActivity.this, MainAdminActivity.class);
                startActivity(intent);
            }
        });
    }
}
