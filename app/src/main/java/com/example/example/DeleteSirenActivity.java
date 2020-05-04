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

public class DeleteSirenActivity extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText sirenId;
    Button deleteSiren,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_siren);
        sirenId = (EditText) findViewById(R.id.idsiren);
        deleteSiren = (Button) findViewById(R.id.deleteid);
        back= (Button) findViewById(R.id.goback);

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
                startActivity(intent);
            }
        });
    }

    private void deleteSiren() {
        final String id = sirenId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(DeleteSirenActivity.this, "Field name is empty !", Toast.LENGTH_SHORT).show();
        }
        else{

            db.collection("zofar").document(id)
                    .delete().addOnSuccessListener(new OnSuccessListener< Void >() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(DeleteSirenActivity.this, "Data deleted !",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
