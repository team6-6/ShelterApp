package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EmployeeSirensActivity extends AppCompatActivity {

    private static final String TAG = "EmployeeSirensActivity";
    public FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ArrayAdapter<String> adpter;
    private List<String> arrayList=new ArrayList<String>();
    String sessionId2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_sirens);
        ListView listView;
        TextView backText, fieldsearch;
        listView=(ListView) findViewById(R.id.list_sirens);
        backText=(TextView) findViewById(R.id.listsirenbackmenu);
        final CollectionReference collectionReference = db.collection("zofar");
        //Calling the get() method with a callback function
        adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        sessionId2 = getIntent().getStringExtra("EXTRA_SESSION_ID");


        listView.setAdapter(adpter);

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //Task is successful
                    //Running enhanced for loop to get each document
                    String text1="\nid                     Neiborhood";
                    arrayList.add(text1);
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        String id=documentSnapshot.getId();
                        String i=documentSnapshot.get("Neiborhood").toString();
                        String text=id+"                     "+i;
                        arrayList.add(text);
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

        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(EmployeeSirensActivity.this, MainEmployeeActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId2);
                startActivity(first);

            }
        });

        fieldsearch=(TextView) findViewById(R.id.sirensid);
        fieldsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EmployeeSirensActivity.this.adpter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }



}

