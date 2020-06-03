package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CivilianShelterInformationActivity extends AppCompatActivity {
    private static final String TAG = "CivilianShelterInformationActivity";

    private String currentShelter;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private TextView Sheltername,laat,loon;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civilian_shelter_information);

        laat = (TextView) findViewById(R.id.infolat);
        loon = (TextView) findViewById(R.id.infolon);
        Sheltername = (TextView) findViewById(R.id.sheltername);
        btn=(Button)findViewById(R.id.infobtn);
        currentShelter = getIntent().getExtras().get("ShelterInfo").toString();
        Sheltername.setText("Shelter name : " + currentShelter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(CivilianShelterInformationActivity.this, CivilianShelterActivity.class);
                startActivity(first);

            }
        });



        db.collection("shelter").document(currentShelter).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Double lati = documentSnapshot.getDouble("lat");
                            Double longi = documentSnapshot.getDouble("lon");
                            laat.setText("Shelter latitude : "+String.valueOf(lati));
                            loon.setText("Shelter longitute : "+String.valueOf(longi));;
                        }

                    }

                });


    }
}