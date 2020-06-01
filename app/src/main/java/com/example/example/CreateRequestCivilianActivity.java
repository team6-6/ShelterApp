package com.example.example;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateRequestCivilianActivity extends AppCompatActivity {


    private static final String TAG = "DangerActivity";
    public FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CoordinatorLayout coordinatorLayout;

    private ListView listView;
    private ListView listView1;
    private ArrayAdapter<String> adpter;
    private ArrayAdapter<String> adpter1;
    private ArrayList<String> arrayList=new ArrayList<String>();
    private ArrayList<String> arrayList1=new ArrayList<String>();

    private TextView  fieldsearch,fieldsearch1;
    private Button BackMenu,Save;
    private EditText DetailsDanger;//description
    String sessionId;
    //String sessionId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request_civilian);
        //final String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        listView=(ListView) findViewById(R.id.list_sirensinfo);
        listView1=(ListView)findViewById(R.id.list_typeRequest);
        //coordinatorLayout= (CoordinatorLayout) findViewById(R.id.Coordlayout);
        DetailsDanger=(EditText)findViewById(R.id.DetailsDanger);
        DetailsDanger.setPadding(25,25 ,25,25);
        Save=(Button) findViewById(R.id.SaveInfo);
        BackMenu=(Button)findViewById(R.id.BackMenu);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

        final CollectionReference collectionReference = db.collection("shelter");
        final CollectionReference collectionReference1=db.collection("Type_Request");



        adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adpter);
        adpter1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList1);
        listView1.setAdapter(adpter1);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRequest();
            }
        });

        BackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(CreateRequestCivilianActivity.this, RequestCivilianActivity.class);
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String currentShelter=adapterView.getItemAtPosition(position).toString();
                fieldsearch.setText(currentShelter);
            }
        });




        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String CurrentTypeRequest = adapterView.getItemAtPosition(position).toString();
                fieldsearch1.setText(CurrentTypeRequest);
            }
        });






        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //Task is successful
                    //Running enhanced for loop to get each document
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        String id=documentSnapshot.getId().toString();
                        String text=id;
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


        collectionReference1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //Task is successful
                    //Running enhanced for loop to get each document
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        String id=documentSnapshot.getId().toString();
                        String text=id;
                        arrayList1.add(text);
                        adpter1.notifyDataSetChanged();

                        //Printing data of each document to log
                        Log.i(TAG, "onComplete: query data: " + documentSnapshot.getData());
                    }
                }else{
                    //Task was not successful
                    Log.e(TAG, "onComplete: ERROR: " + task.getException().getLocalizedMessage() );
                }
            }
        });


        fieldsearch=(TextView) findViewById(R.id.sirens_id);
        fieldsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CreateRequestCivilianActivity.this.adpter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        fieldsearch1=(TextView) findViewById(R.id.TypeRequest_id);
        fieldsearch1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CreateRequestCivilianActivity.this.adpter1.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }



    private void addRequest() {


        final String descriptionrequest = DetailsDanger.getText().toString().trim();
        final String fieldcurrentshelter = fieldsearch.getText().toString().trim();
        final String fieldRequestType = fieldsearch1.getText().toString().trim();
        final Map<String, Object> request_details = new HashMap<>();

        if (descriptionrequest.equals("") || fieldcurrentshelter.equals("") || fieldRequestType.equals("")) {
            Toast.makeText(CreateRequestCivilianActivity.this, "Field is empty !", Toast.LENGTH_SHORT).show();

        } else {
            request_details.put("Description", descriptionrequest);
            request_details.put("Type_Request", fieldRequestType);
            request_details.put("Id_Shelter", fieldcurrentshelter);
            request_details.put("Sender", "Civilian");
            request_details.put("Status", "Sent to treatment");


            db.collection("Requests")
                    .add(request_details)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(CreateRequestCivilianActivity.this, "Request added", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CreateRequestCivilianActivity.this, "Error adding request", Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            String RequestId = db.collection("Requests").document().getId();


            //Snackbar snackbar = Snackbar.make(coordinatorLayout,RequestId,Snackbar.LENGTH_INDEFINITE);
            //snackbar.show();

            Toast.makeText(CreateRequestCivilianActivity.this, "Your request number is : "+RequestId, Toast.LENGTH_SHORT).show();
            Intent first = new Intent(CreateRequestCivilianActivity.this, MainCivilianActivity.class);
            first.putExtra("EXTRA_SESSION_ID", sessionId);
            startActivity(first);

        }

    }
}
