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
import com.google.firebase.FirebaseApp;
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
    private Intent intent;
    private FirebaseFirestore db;
    private List<String> arrayList = new ArrayList<String>();
    User user1 = new User();
    boolean flag=true;
    String user ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user= getIntent().getStringExtra("EXTRA_SESSION_ID");
        func(user);
        FirebaseApp.initializeApp(this);
        viewData();
        TextView backText;
        backText = (TextView) findViewById(R.id.backEmergency);
        backText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("users").document(user).get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                if (documentSnapshot.exists()) {

                                    final String permission = documentSnapshot.getString("permission");

                                    CheckPermissions(permission);
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

    public boolean viewData() {


        db = FirebaseFirestore.getInstance();
        ListView listView;
        setContentView(R.layout.activity_numbers);
        listView = (ListView) findViewById(R.id.listEmergency);
        final CollectionReference collectionReference = db.collection("Emergency");
        //Calling the get() method with a callback function
        adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adpter);

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    //Task is successful
                    //Running enhanced for loop to get each document
                    String text1 = "\nOrganization                       Number";
                    arrayList.add(text1);
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        String id = documentSnapshot.getId();
                        String i = documentSnapshot.get("number").toString();
                        int space=23+(12-id.length());
                        String text = id ;
                        for(int j=0;j<=space;j++){text+=" ";}
                        text+=i;
                        arrayList.add(text);
                        adpter.notifyDataSetChanged();

                        //Printing data of each document to log
                        Log.i(TAG, "onComplete: query data: " + documentSnapshot.getData());
                    }
                    flag=true;
                } else {
                    flag=false;
                    //Task was not successful
                    Log.e(TAG, "onComplete: ERROR: " + task.getException().getLocalizedMessage());
                }
            }
        });
        return flag;
    }


public void func(String val){
        user1.name=val;

}

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void putIntent(Class activity){
        intent=new Intent(this,activity);
        CheckLogin(intent,user1.name);
        intent.putExtra("EXTRA_SESSION_ID", user);
        startActivity(intent);
    }

    public void CheckLogin(Intent i,String m){
        i.putExtra("EXTRA_SESSION_ID",m);
    }

    public String CheckPermissions(String permission){

        if(permission.equals("A")){
            putIntent(MainAdminActivity.class);
            setUser1(user1);
            return "Admin";

        }
        if(permission.equals("B")){
            putIntent(MainEmployeeActivity.class);
            setUser1(user1);

            return "Employee";

        }
        else if(permission.equals("C")){
            putIntent(MainCivilianActivity.class);
            setUser1(user1);
            return "Civilian";
        }
        return "NULL";
    }

}
