package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ReportsActivity extends AppCompatActivity {
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG = "ReportsActivity";
    private Button ReportRequest,ReportRating,btn;

    String sessionId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        ReportRating=(Button)findViewById(R.id.reportRating);
        ReportRequest=(Button)findViewById(R.id.reportRequest);

        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        btn=(Button)findViewById(R.id.btnback);


        ReportRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(ReportsActivity.this, ReportRequestActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent first = new Intent(ReportsActivity.this, MainAdminActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);

            }
        });


        ReportRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(ReportsActivity.this, ReportRatingActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);
            }
        });

    }
}
