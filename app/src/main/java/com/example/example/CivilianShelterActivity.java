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
import com.google.firebase.FirebaseApp;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CivilianShelterActivity extends AppCompatActivity {
    private static final String TAG = "CivilianShetlerActivity";
    public FirebaseFirestore db;
    ListView listView;
    ArrayAdapter<String> adpter;
    ArrayList<String> arrayList=new ArrayList<String>();
    TextView fieldsearch;
    Button backText;
    private String sessionId, sessionId2;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_civilian_shelters);
                FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
        sessionId = getIntent().getStringExtra("KIND_OF_PERMISSION");
         sessionId2 = getIntent().getStringExtra("EXTRA_SESSION_ID");
        //final String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        listView=(ListView) findViewById(R.id.list_sirens);
        backText=(Button) findViewById(R.id.listsirenbackmenu);
        final CollectionReference collectionReference = db.collection("shelter");
        //Calling the get() method with a callback function
        adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adpter);

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //Task is successful
                    //Running enhanced for loop to get each document
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        Shelter shelter=new Shelter();
                        String id=documentSnapshot.getId().toString();
                        String text=id;
                        shelter.setName(text);
                        arrayList.add(shelter.getName());
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
                sessionId = getIntent().getStringExtra("KIND_OF_PERMISSION");
                String currentShelter=adapterView.getItemAtPosition(position).toString();
                if(sessionId.equals("Civilian")) {
                    Intent InfoShelterIntent = new Intent(CivilianShelterActivity.this, CivilianShelterInformationActivity.class);
                    InfoShelterIntent.putExtra("EXTRA_SESSION_ID", sessionId2);
                    InfoShelterIntent.putExtra("ShelterInfo", currentShelter);
                    startActivity(InfoShelterIntent);
                }
                else if(sessionId.equals("Employee")) {
                    Intent InfoShelterIntent = new Intent(CivilianShelterActivity.this, EditShelterEmployeeActivity.class);
                    InfoShelterIntent.putExtra("EXTRA_SESSION_ID", sessionId2);
                    InfoShelterIntent.putExtra("ShelterInfo", currentShelter);
                    startActivity(InfoShelterIntent);
                }
            }
        });

        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sessionId.equals("Civilian")){
                    Intent first = new Intent(CivilianShelterActivity.this, MainCivilianActivity.class);
                    first.putExtra("KIND_OF_PERMISSION", sessionId);
                    first.putExtra("EXTRA_SESSION_ID", sessionId2);
                    startActivity(first);
                }
                else if (sessionId.equals("Employee")){
                    Intent first = new Intent(CivilianShelterActivity.this, MainEmployeeActivity.class);
                    String sessionId2 = getIntent().getStringExtra("EXTRA_SESSION_ID");
                    first.putExtra("KIND_OF_PERMISSION", sessionId);
                    first.putExtra("EXTRA_SESSION_ID", sessionId2);
                    startActivity(first);
                }


            }
        });

        fieldsearch=(TextView) findViewById(R.id.sirensid);
        fieldsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CivilianShelterActivity.this.adpter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

}
