package com.example.example;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ContactUsActivity extends AppCompatActivity {
    private static final String TAG = "ContactUsActivity";
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ListView listView;
    private ArrayAdapter<String> adpter;
    private ArrayList<String> arrayList=new ArrayList<String>();
    private TextView fieldsearch;
    private Button backText;
    String sessionId,sessionId2;
    String perm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        backText= (Button) findViewById(R.id.listrequestbackmenu);
        listView=(ListView) findViewById(R.id.list_request);
        final CollectionReference collectionReference = db.collection("Requests");
        //Calling the get() method with a callback function
        adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adpter);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        sessionId2= getIntent().getStringExtra("per");

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //Task is successful
                    //Running enhanced for loop to get each document
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()) {
                        String id = documentSnapshot.getId().toString();
                        String text = id;
                        String status = documentSnapshot.getString("Status");
                        String sender = documentSnapshot.getString("Sender");

                        if (status.equals("Sent to treatment")){
                            if (sessionId2.equals("A")) {
                                if (sender.equals("Employee")) {
                                    arrayList.add(text);
                                    adpter.notifyDataSetChanged();
                                }
                            }
                            if (sessionId2.equals("B")) {
                                if (sender.equals("Civilian")) {
                                    arrayList.add(text);
                                    adpter.notifyDataSetChanged();
                                }
                            }
                        }
                        //Printing data of each document to log
                        Log.i(TAG, "onComplete: query data: " + documentSnapshot.getData());
                    }
                }else{
                    //Task was not successful
                    Log.e(TAG, "onComplete: ERROR: " + task.getException().getLocalizedMessage() );
                }
            }
        });





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String currentRequest=adapterView.getItemAtPosition(position).toString();

                if (sessionId2.equals("B")){
                    Intent InfoRequestIntent = new Intent(ContactUsActivity.this,ContantUsInfoActivity.class);
                    InfoRequestIntent.putExtra("RequestInfo",currentRequest);
                    InfoRequestIntent.putExtra("per", "B");
                    InfoRequestIntent.putExtra("EXTRA_SESSION_ID",sessionId);

                    startActivity(InfoRequestIntent);
                }
                else if(sessionId2.equals("A")){
                    Intent InfoRequestIntent = new Intent(ContactUsActivity.this,ContantUsAdminActivity.class);
                    InfoRequestIntent.putExtra("RequestInfo",currentRequest);
                    InfoRequestIntent.putExtra("per", "A");
                    InfoRequestIntent.putExtra("EXTRA_SESSION_ID",sessionId);

                    startActivity(InfoRequestIntent);


                }
            }
        });


        backText.setOnClickListener(new View.OnClickListener() {//לבדוק לגבי כל משתמש
            @Override
            public void onClick(View v) {

                if(sessionId2.equals("B")) {
                    Intent intent = new Intent(ContactUsActivity.this, TreatmentRequestActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID",sessionId);
                    intent.putExtra("per", "B");
                    startActivity(intent);
                }
                else if(sessionId2.equals("A")) {
                    Intent intent = new Intent(ContactUsActivity.this, MainAdminActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID",sessionId);
                    intent.putExtra("per", "A");
                    startActivity(intent);
                }

            }
        });

        fieldsearch=(TextView) findViewById(R.id.requestid);
        fieldsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ContactUsActivity.this.adpter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}