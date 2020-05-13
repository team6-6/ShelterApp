package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class DeleteShelterActivity extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText nameShelter;
    Button deleteShelter,back;
    private Checkfunction checkfunction=new Checkfunction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_shelter);
        nameShelter = (EditText) findViewById(R.id.shelname);
        deleteShelter = (Button) findViewById(R.id.deleteshel);
        back= (Button) findViewById(R.id.goback);

        deleteShelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteShelter();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeleteShelterActivity.this, AdminSheltersActivity.class);
                startActivity(intent);
            }
        });
    }

    private void deleteShelter() {
        final String name = nameShelter.getText().toString().trim();
        if ( checkfunction.notEmpty(name)==1) {
            Toast.makeText(DeleteShelterActivity.this, "Field name is empty !", Toast.LENGTH_SHORT).show();
        }
        else{

                db.collection("shelter").document(name)
                        .delete().addOnSuccessListener(new OnSuccessListener < Void > () {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(DeleteShelterActivity.this, "Data deleted !",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

}
