package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.DialogInterface;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class RequestsAdminActivity extends AppCompatActivity {//בקשות של מחיקת ופתיחת מקלט

    String TAG="RequestsAdminActivity";
    public FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CoordinatorLayout coordinatorLayout;

    private ListView listView;
    private ListView listView1;
    private ArrayAdapter<String> adpter;
    private ArrayAdapter<String> adpter1;
    private ArrayList<String> arrayList=new ArrayList<String>();
    private ArrayList<String> arrayList1=new ArrayList<String>();
    private RadioGroup group;
    private RadioButton radioButton;
    private TextView fieldsearch;
    private Button BackMenu,Save;
    private EditText DetailsDanger;//description
    private int choose;
    private String textstring;
    private String sessionId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_admin);
        //final String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        listView=(ListView) findViewById(R.id.list_sirensinfoEmployee);
        group=findViewById(R.id.group_select);
        //coordinatorLayout= (CoordinatorLayout) findViewById(R.id.Coordlayout);
        DetailsDanger=(EditText)findViewById(R.id.DetailsRequestEmployee);
        DetailsDanger.setPadding(25,25 ,25,25);
        Save=(Button) findViewById(R.id.SaveInfoEmployee);
        BackMenu=(Button)findViewById(R.id.BackRequestEmployee);
        adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adpter);
        fieldsearch=(TextView) findViewById(R.id.sirens_idEmployee);
        fieldsearch.setVisibility(View.GONE);


        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                choose=checkedId;
                if (checkedId==R.id.radioButton){
                    adpter.clear();
                    adpter.notifyDataSetChanged();
                    fieldsearch.setVisibility(View.GONE);
                }
                else if (checkedId==R.id.radioButton2){
                    fieldsearch.setVisibility(View.VISIBLE);
                    final CollectionReference collectionReference = db.collection("shelter");

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            String currentShelter=adapterView.getItemAtPosition(position).toString();
                            fieldsearch.setText(currentShelter);
                        }
                    });
                    int h=group.getCheckedRadioButtonId();
                    radioButton=findViewById(h);
                    textstring=radioButton.getText().toString();
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

                    fieldsearch.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            RequestsAdminActivity.this.adpter.getFilter().filter(s);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });


                }
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choose==R.id.radioButton){
                    addcreateRequest();
                }
                else if (choose==R.id.radioButton2) {
                    addRequest();
                }
            }
        });

        BackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(RequestsAdminActivity.this, MainEmployeeActivity.class);
                sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
                first.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(first);

            }
        });

    }

    private void addcreateRequest() {
        final String descriptionrequest = DetailsDanger.getText().toString().trim();
        final String fieldcurrentshelter = " Empty";
        final String fieldRequestType = textstring;

        final Map<String, Object> request_details = new HashMap<>();

        if (descriptionrequest.equals("")) {
            Toast.makeText(RequestsAdminActivity.this, "Field is empty !", Toast.LENGTH_SHORT).show();

        } else {
            request_details.put("Description", descriptionrequest);
            request_details.put("Type_Request", "Adding shelter");
            request_details.put("Id_Shelter", fieldcurrentshelter);
            request_details.put("Sender", "Employee");
            request_details.put("Status", "Sent to treatment");


            db.collection("Requests")
                    .add(request_details)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(RequestsAdminActivity.this, "Request added", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RequestsAdminActivity.this, "Error adding request", Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            String RequestId = db.collection("Requests").document().getId();


            buildDialog(RequestId);



        }
    }


    private void addRequest() {


        final String descriptionrequest = DetailsDanger.getText().toString().trim();
        final String fieldcurrentshelter = fieldsearch.getText().toString().trim();
        final String fieldRequestType = textstring;
        final Map<String, Object> request_details = new HashMap<>();

        if (descriptionrequest.equals("") || fieldcurrentshelter.equals("") || fieldRequestType.equals("")) {
            Toast.makeText(RequestsAdminActivity.this, "Field is empty !", Toast.LENGTH_SHORT).show();

        } else {
            request_details.put("Description", descriptionrequest);
            request_details.put("Type_Request", fieldRequestType);
            request_details.put("Id_Shelter", fieldcurrentshelter);
            request_details.put("Sender", "Employee");
            request_details.put("Status", "Sent to treatment");


            db.collection("Requests")
                    .add(request_details)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(RequestsAdminActivity.this, "Request added", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RequestsAdminActivity.this, "Error adding request", Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            String RequestId = db.collection("Requests").document().getId();


            //Snackbar snackbar = Snackbar.make(coordinatorLayout,RequestId,Snackbar.LENGTH_INDEFINITE);
            //snackbar.show();

            Toast.makeText(RequestsAdminActivity.this, "Your request number is : "+RequestId, Toast.LENGTH_SHORT).show();
            Intent first = new Intent(RequestsAdminActivity.this, MainEmployeeActivity.class);
            sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
            first.putExtra("EXTRA_SESSION_ID", sessionId);
            startActivity(first);

        }

    }

    private void buildDialog(String requestid) {
        new AlertDialog.Builder(this)
                //.setTitle("Confirm")
                .setMessage("Your request number is : " + requestid)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent first = new Intent(RequestsAdminActivity.this, MainEmployeeActivity.class);
                        first.putExtra("EXTRA_SESSION_ID", sessionId);
                        startActivity(first);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

}
