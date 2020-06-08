package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ContantUsAdminActivity extends AppCompatActivity {
    private static final String TAG = "ContantUsAdminActivity";

    private String currentShelter;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private TextView headline,typerequest,idshelter,description,back,status;
    private CheckBox checkBox;
    String sessionId,sessionId2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contant_us_admin);
        headline = (TextView) findViewById(R.id.headline);
        typerequest = (TextView) findViewById(R.id.TypeRequest);
        idshelter = (TextView) findViewById(R.id.idshelter);
        description = (TextView) findViewById(R.id.description);
        back = (TextView) findViewById(R.id.backinfocontant);
        checkBox = (CheckBox) findViewById(R.id.checkboox);
        currentShelter = getIntent().getExtras().get("RequestInfo").toString();
        //status=(TextView) findViewById(R.id.Status);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");
        sessionId2= getIntent().getStringExtra("per");



        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buildDialog();

            }
        });



        db.collection("Requests").document(currentShelter).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String ShelterName=documentSnapshot.get("Id_Shelter").toString();
                            String TypeRequest=documentSnapshot.get("Type_Request").toString();
                            String Description=documentSnapshot.get("Description").toString();
                            //String Status=documentSnapshot.get("Status").toString();

                            description.setText("Description : \n"+Description);
                            idshelter.setText("Shelter ID:  \n"+ShelterName);
                            headline.setText(currentShelter);
                            typerequest.setText("Request's type :\n "+TypeRequest);
                            //status.setText("Status : "+Status);


                        }}

                });










        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(ContantUsAdminActivity.this, ContactUsActivity.class);
                first.putExtra("EXTRA_SESSION_ID","A");
                first.putExtra("per", sessionId2);

                startActivity(first);

            }
        });





    }
    private void buildDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Are you sure you done with this request?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DocumentReference db1=db.collection("Requests").document(currentShelter);
                        db1.update(
                                "Status","Done"

                        ).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ContantUsAdminActivity.this, "Request has done", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });
                        Intent first = new Intent(ContantUsAdminActivity.this, ContactUsActivity.class);
                        first.putExtra("EXTRA_SESSION_ID", sessionId);
                        first.putExtra("per", sessionId2);

                        startActivity(first);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        checkBox.setChecked(false);

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}
