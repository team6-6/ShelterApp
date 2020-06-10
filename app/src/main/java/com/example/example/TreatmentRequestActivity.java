package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class TreatmentRequestActivity extends AppCompatActivity {

    private static final String TAG = "TreatmentRequestActivity";

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private Button TreatmentButton,TrackingButton,btn;
    String sessionId,sessionId2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_request);

        TreatmentButton=(Button)findViewById(R.id.TreatmentRequest) ;
        TrackingButton= (Button)findViewById(R.id.TrackRequest);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        sessionId2= getIntent().getStringExtra("per");
        btn=(Button)findViewById(R.id.btnReturn);


        TreatmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(TreatmentRequestActivity.this, ContactUsActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                first.putExtra("per", sessionId2);

                startActivity(first);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent first = new Intent(TreatmentRequestActivity.this, MainEmployeeActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                first.putExtra("per", sessionId2);
                startActivity(first);

            }
        });


        TrackingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(TreatmentRequestActivity.this, SearchRequestActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                first.putExtra("per", sessionId2);
                startActivity(first);
            }
        });

    }



}
