package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ContantUsAdminActivity extends AppCompatActivity {
    private static final String TAG = "ContantUsAdminActivity";

    private String currentShelter;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private TextView headline,typerequest,idshelter,description,back,status;
    private CheckBox checkBox;
    String sessionID,sessionId2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contant_us_admin);
        headline = (TextView) findViewById(R.id.headline);
        typerequest = (TextView) findViewById(R.id.TypeRequest);
        idshelter = (TextView) findViewById(R.id.idshelter);
        description = (TextView) findViewById(R.id.description);
        back = (TextView) findViewById(R.id.backinfocontant);
        currentShelter = getIntent().getExtras().get("RequestInfo").toString();
        status=(TextView) findViewById(R.id.Status);
        sessionID = getIntent().getStringExtra("EXTRA_SESSION_ID");
        sessionId2= getIntent().getStringExtra("per");







        db.collection("Requests").document(currentShelter).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String ShelterName=documentSnapshot.get("Id_Shelter").toString();
                            String TypeRequest=documentSnapshot.get("Type_Request").toString();
                            String Description=documentSnapshot.get("Description").toString();
                            String Status=documentSnapshot.get("Status").toString();

                            description.setText("Description : \n"+Description);
                            idshelter.setText("Shelter ID:  \n"+ShelterName);
                            headline.setText(currentShelter);
                            typerequest.setText("Request's type :\n "+TypeRequest);
                            status.setText("Status : "+Status);


                        }}

                });










        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(ContantUsAdminActivity.this, ContactUsActivity.class);
                first.putExtra("EXTRA_SESSION_ID","A");
                first.putExtra("per", sessionId2);

                startActivity(first);

            }
        });

    }


}
