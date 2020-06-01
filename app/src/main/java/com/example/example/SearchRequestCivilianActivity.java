package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SearchRequestCivilianActivity extends AppCompatActivity {

    private static final String TAG = "SearchRequestCivilianActivity";
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText fieldRequestID;
    private Button Back, Search;
    private Checkfunction checkfunction = new Checkfunction();
    String sessionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_request_civilian);

        fieldRequestID = (EditText) findViewById(R.id.IDRequest);
        fieldRequestID.setPadding(25, 25, 25, 25);
        Search = (Button) findViewById(R.id.Search_but);
        Back = (Button) findViewById(R.id.Baack);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        // private Checkfunction checkfunction=new Checkfunction();


        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(SearchRequestCivilianActivity.this, RequestCivilianActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);

            }
        });





        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ReqID = fieldRequestID.getText().toString().trim();


                if (checkfunction.notEmpty(ReqID)==1 ){
                    Toast.makeText(SearchRequestCivilianActivity.this, "Enter a Request Number !", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(SearchRequestCivilianActivity.this, "ID = "+ReqID, Toast.LENGTH_SHORT).show();

                }
                else {

                    db.collection("Requests").document(ReqID).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        Intent SearchRequestIntent = new Intent(SearchRequestCivilianActivity.this,ResultSearchRequestCivilianActivity.class);
                                        SearchRequestIntent.putExtra("EXTRA_SESSION_ID", sessionId);
                                        SearchRequestIntent.putExtra("RequestInfo",ReqID);
                                        startActivity(SearchRequestIntent);



                                    }
                                    else{

                                        Toast.makeText(SearchRequestCivilianActivity.this, "Enter an exist Request Number !", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });










    }


}