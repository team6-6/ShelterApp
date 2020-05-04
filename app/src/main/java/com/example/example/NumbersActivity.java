package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    private static final String TAG = "NumbersActivity";
    private ArrayAdapter<String> adpter;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private List<String> arrayList=new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         ListView listView;
         TextView backText;
        setContentView(R.layout.activity_numbers);
        listView=(ListView) findViewById(R.id.listEmergency);
        backText=(TextView) findViewById(R.id.backEmergency);
        final CollectionReference collectionReference = db.collection("Emergency");
        //Calling the get() method with a callback function
        adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adpter);

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //Task is successful
                    //Running enhanced for loop to get each document
                    String text1="\nOrganization                     Number";
                    arrayList.add(text1);
                    for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                        String id=documentSnapshot.getId();
                        String i=documentSnapshot.get("number").toString();
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
                final String user = getIntent().getStringExtra("EXTRA_SESSION_ID2");


                db.collection("users").document(user).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    final String permission=documentSnapshot.getString("permission");
                                    if(permission.equals("C")){
                                        Intent first = new Intent(NumbersActivity.this, MainCivilianActivity.class);
                                        first.putExtra("EXTRA_SESSION_ID", user);
                                        startActivity(first);
                                    }
                                    else if(permission.equals("B")){
                                        Intent first = new Intent(NumbersActivity.this, MainEmployeeActivity.class);
                                        first.putExtra("EXTRA_SESSION_ID", user);
                                        startActivity(first);
                                    }
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NumbersActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                    }
                });






            }
        });

    }
}
