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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ReportRequestActivity extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    ListView listView;
    private ArrayAdapter<String> adpter;
    ArrayList<String> arrayList=new ArrayList<String>();
    String sessionId;

    private static final String TAG = "ReportRequestActivity";
    TextView fieldsearch;
    Button backText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_request);
        final CollectionReference collectionReference = db.collection("Requests");
        //Calling the get() method with a callback function
        backText= (Button) findViewById(R.id.listrequestbackmenureport);
        listView=(ListView) findViewById(R.id.list_requestreport);
        adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adpter);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");



        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //Task is successful
                    //Running enhanced for loop to get each document
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        Request r1=new Request();
                        String id=documentSnapshot.getId().toString();
                        String text=id;
                        r1.setId(text);
                        arrayList.add(r1.getId());
                        adpter.notifyDataSetChanged();

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

                    Intent InfoRequestIntent = new Intent(ReportRequestActivity.this,ResultSearchRequestActivity.class);
                    InfoRequestIntent.putExtra("RequestInfo",currentRequest);
                    InfoRequestIntent.putExtra("per", "AA");
                    InfoRequestIntent.putExtra("EXTRA_SESSION_ID",sessionId);

                    startActivity(InfoRequestIntent);



            }
        });


        backText.setOnClickListener(new View.OnClickListener() {//לבדוק לגבי כל משתמש
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(ReportRequestActivity.this, ReportsActivity.class);
                    intent.putExtra("EXTRA_SESSION_ID",sessionId);

                    startActivity(intent);


            }
        });

        fieldsearch=(TextView) findViewById(R.id.requestidreport);
        fieldsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ReportRequestActivity.this.adpter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
}}
