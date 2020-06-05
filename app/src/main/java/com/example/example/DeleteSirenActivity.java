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

public class DeleteSirenActivity extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText sirenId;
    Button deleteSiren,back;
    private Checkfunction checkfunction=new Checkfunction();
    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_siren);
        sirenId = (EditText) findViewById(R.id.idsiren);
        deleteSiren = (Button) findViewById(R.id.deleteid);
        back= (Button) findViewById(R.id.goback);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

        deleteSiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSiren();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( DeleteSirenActivity.this, AdminSirensActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });
    }

    private void deleteSiren() {
        final String id = sirenId.getText().toString().trim();
        if (checkfunction.notEmpty(id)==1) {
            Toast.makeText(DeleteSirenActivity.this, "Field name is empty !", Toast.LENGTH_SHORT).show();
        }



        else{

            db.collection("zofar").document(id).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {

                                db.collection("zofar").document(id)
                                        .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override

                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(DeleteSirenActivity.this, "Data deleted !",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                            else{
                                Toast.makeText(DeleteSirenActivity.this, "siren not exist", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("DeleteSirenActivity", "Error adding document", e);
                        }
                    });
        }
    }
}
