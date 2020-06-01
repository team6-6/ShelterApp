package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainCivilianActivity extends AppCompatActivity {

    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_civilian);

        ImageView list,map,numbers,rate,signal;
        TextView change,logout ;


        signal=(ImageView)findViewById(R.id.Reportdanger);
        map= (ImageView)findViewById(R.id.MapOption);
        list= (ImageView)findViewById(R.id.Shelterlist);
        numbers= (ImageView)findViewById(R.id.EmergencyNumber);
        rate=(ImageView)findViewById(R.id.RateUs);
        change= (TextView) findViewById(R.id.changePass);
        logout= (TextView) findViewById(R.id.logout);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");


        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainCivilianActivity.this, MapsActivity.class);
                startActivity(first);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToChangePassword();

            }
        });


        signal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSessionId();
                Intent first = new Intent(MainCivilianActivity.this, RequestCivilianActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });


        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainCivilianActivity.this, CivilianShelterActivity.class);
                first.putExtra("KIND_OF_PERMISSION", "Civilian");
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSessionId();
                Intent first = new Intent(MainCivilianActivity.this, NumbersActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);

            }
        });



        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainCivilianActivity.this, RateActivity.class);
                first.putExtra("EXTRA_SESSION_ID2", sessionId);
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
        Intent first1 = new Intent(MainCivilianActivity.this, ChangeActivity.class);
        first1.putExtra("EXTRA_SESSION_ID2", sessionId);
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
