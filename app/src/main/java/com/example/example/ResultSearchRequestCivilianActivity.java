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

public class ResultSearchRequestCivilianActivity extends AppCompatActivity {


    private static final String TAG = "ResultSearchRequestCivilianActivity";

    private String currentRequest;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private TextView RequestID,ShelterID,Status,Type_Req,DescriptionReq;
    private Button btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search_request_civilian);



        DescriptionReq=(TextView) findViewById(R.id.descriptionRequest);
        RequestID = (TextView) findViewById(R.id.request_id);
        ShelterID = (TextView) findViewById(R.id.Shelter_name);
        Status = (TextView) findViewById(R.id.StatusReq);
        Type_Req = (TextView) findViewById(R.id.type_req);
        btn=(Button)findViewById(R.id.button_back);
        currentRequest = getIntent().getExtras().get("RequestInfo").toString();
        RequestID.setText("Request : " + currentRequest);
        RequestID.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(ResultSearchRequestCivilianActivity.this, RequestCivilianActivity.class);
                final String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);

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
