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

    private String currentShelter,sessionId2;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private TextView Sheltername,laat,loon,infoo;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civilian_shelter_information);

        laat = (TextView) findViewById(R.id.infolat);
        loon = (TextView) findViewById(R.id.infolon);
        infoo = (TextView) findViewById(R.id.MoreInfo);
        Sheltername = (TextView) findViewById(R.id.sheltername);
        btn=(Button)findViewById(R.id.infobtn);
        currentShelter = getIntent().getExtras().get("ShelterInfo").toString();
        Sheltername.setText("Shelter name : " + currentShelter);
        sessionId2 = getIntent().getStringExtra("EXTRA_SESSION_ID");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(CivilianShelterInformationActivity.this, CivilianShelterActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId2);
                first.putExtra("KIND_OF_PERMISSION", "Civilian");
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
                            String moreInformation = documentSnapshot.get("info").toString();
                            laat.setText("Shelter latitude : "+String.valueOf(lati));
                            loon.setText("Shelter longitute : "+String.valueOf(longi));
                            infoo.setText("More Details : "+ moreInformation);


                        }

                    }

                });


    }
}