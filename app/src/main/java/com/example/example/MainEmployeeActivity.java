package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainEmployeeActivity extends AppCompatActivity {

    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_employee);
        ImageView list_shelters,list_sirens,map,requests,requests_admin,numbers,contant;
        TextView change,logout ;

        list_shelters=(ImageView)findViewById(R.id.Shelterlist) ;
        list_sirens= (ImageView)findViewById(R.id.Sirenlist);
        map= (ImageView)findViewById(R.id.MapOption);
        requests_admin=(ImageView)findViewById(R.id.Requests_Admin);
        numbers= (ImageView)findViewById(R.id.EmergencyNumber);
        contant= (ImageView)findViewById(R.id.ContantUs);
        change= (TextView) findViewById(R.id.changePass);
        logout= (TextView) findViewById(R.id.logout);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

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

                ToChangePassword();
            }
        });

        list_shelters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, CivilianShelterActivity.class);
                first.putExtra("KIND_OF_PERMISSION", "Employee");
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });

        list_sirens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, EmployeeSirensActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });



        requests_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, RequestsAdminActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });

        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, NumbersActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });

        contant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(MainEmployeeActivity.this, ContactUsActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                first.putExtra("per", "B");
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
        Intent first1 = new Intent(MainEmployeeActivity.this, ChangeActivity.class);
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
