package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResultSearchRequestActivity extends AppCompatActivity {


    private static final String TAG = "ResultSearchRequestCivilianActivity";

    private String currentRequest;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private TextView RequestID,ShelterID,Status,Type_Req,DescriptionReq;
    private Button btn;
    private  String sessionId,sessionId2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search_request_civilian);

        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        sessionId2 =getIntent().getStringExtra("per");
        DescriptionReq=(TextView) findViewById(R.id.descriptionRequest);
        RequestID = (TextView) findViewById(R.id.request_id);
        ShelterID = (TextView) findViewById(R.id.Shelter_name);
        Status = (TextView) findViewById(R.id.StatusReq);
        Type_Req = (TextView) findViewById(R.id.type_req);
        btn=(Button)findViewById(R.id.button_back);
        currentRequest = getIntent().getExtras().get("RequestInfo").toString();
        RequestID.setText(currentRequest);
        RequestID.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sessionId2.equals("AA")){
                    Intent first = new Intent(ResultSearchRequestActivity.this, ReportRequestActivity.class);
                    first.putExtra("EXTRA_SESSION_ID", sessionId);
                    first.putExtra("per", sessionId2);
                    startActivity(first);
                }
                else{
                Intent first = new Intent(ResultSearchRequestActivity.this, SearchRequestActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                first.putExtra("per", sessionId2);
                startActivity(first);
                }

            }
        });



        db.collection("Requests").document(currentRequest).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                            String ShelterName=documentSnapshot.get("Id_Shelter").toString();
                            String StatusRequest=documentSnapshot.get("Status").toString();
                            String TypeRequest=documentSnapshot.get("Type_Request").toString();
                            String description=documentSnapshot.get("Description").toString();

                            DescriptionReq.setText("Description : "+description);
                            ShelterID.setText("Shelter Name : "+ShelterName);
                            Status.setText("Request Status : "+StatusRequest);
                            Type_Req.setText("Request Type : "+TypeRequest);

                        }

                    }

                });

    }
}
