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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAdminNumActivity extends AppCompatActivity {

    private static final String TAG = "ViewAdminNumActivity";
    private ArrayAdapter<String> adpter;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private List<String> arrayList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_admin_num);
        ListView listView;
         TextView backText;
        listView=(ListView) findViewById(R.id.listEmergency2);
        backText=(TextView) findViewById(R.id.backEmergency2);
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

                Intent first = new Intent(ViewAdminNumActivity.this, EmergencyMenuActivity.class);
                startActivity(first);

            }
        });

    }
}
