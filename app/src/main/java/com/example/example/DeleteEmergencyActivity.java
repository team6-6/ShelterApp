package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DeleteEmergencyActivity extends AppCompatActivity {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText nameOrganization;
    Button deleteNumber,back;
    private FirebaseFirestore db2=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_emergency);
        nameOrganization = (EditText) findViewById(R.id.nameNumDelete);
        deleteNumber = (Button) findViewById(R.id.deletenum);
        back= (Button) findViewById(R.id.gobacknum);

        deleteNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNum();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteEmergencyActivity.this, EmergencyMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void deleteNum() {
        final String name = nameOrganization.getText().toString().trim();
        if (name.equals("")) {
            Toast.makeText(DeleteEmergencyActivity.this, "Field name is empty !", Toast.LENGTH_SHORT).show();
        }
        else{
            db2.collection("Emergency").document(name).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {

                                db.collection("Emergency").document(name)
                                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override

                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(DeleteEmergencyActivity.this, "Data deleted !",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                            else{
                                Toast.makeText(DeleteEmergencyActivity.this, "Number not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("DeleteEmergencyActivity", "Error adding document", e);
                        }
                    });
        }
    }
}
