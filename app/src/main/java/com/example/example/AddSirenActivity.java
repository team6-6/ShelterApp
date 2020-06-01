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

import java.util.HashMap;
import java.util.Map;

public class AddSirenActivity extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public FirebaseFirestore db2 = FirebaseFirestore.getInstance();
    private static final String TAG = "AddSirenActivity";
    EditText sirenId,Neibor, lat, lon;
    Button addSiren,back;
    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_siren);

        sirenId = (EditText) findViewById(R.id.idsiren);
        Neibor = (EditText) findViewById(R.id.neibor);
        lat = (EditText) findViewById(R.id.lat);
        lon = (EditText) findViewById(R.id.lon);
        addSiren = (Button) findViewById(R.id.Addbutton);
        back= (Button) findViewById(R.id.goback);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

        addSiren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSiren();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSirenActivity.this, AdminSirensActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);
                startActivity(intent);
            }
        });
    }

    private void addSiren() {
        final String siren = sirenId.getText().toString().trim();
        final String Neiborhood =Neibor.getText().toString().trim();
        try{
            Double newlat = Double.parseDouble(lat.getText().toString());
            Double newlon = Double.parseDouble(lon.getText().toString());

            if (siren.equals("") || Neiborhood.equals("")) {
                Toast.makeText(AddSirenActivity.this, "Field is empty !", Toast.LENGTH_SHORT).show();
            }
            else{
                try {

                    // Create a new shelter
                    final Map<String, Object> siren_details = new HashMap<>();
                    siren_details.put("Neiborhood", Neiborhood);
                    siren_details.put("lat", newlat);
                    siren_details.put("lon", newlon);



                    db.collection("zofar").document(siren).get()
                            .addOnSuccessListener(new
                                          OnSuccessListener<DocumentSnapshot>()
                                          {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (!documentSnapshot.exists()) {
                                        db2.collection("zofar").document(siren).set(siren_details)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void documentReference) {
                                                        Toast.makeText(AddSirenActivity.this, "Siren added", Toast.LENGTH_SHORT).show();
                                                        Log.d(TAG, "DocumentSnapshot added with ID: " + siren);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error adding document", e);
                                                        Toast.makeText(AddSirenActivity.this, "Already Exist!", Toast.LENGTH_SHORT).show();

                                                    }
                                                });
                                    }
                                    else{
                                        Toast.makeText(AddSirenActivity.this, "Siren alredy exist", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                } catch (Exception e) {
                    Toast.makeText(AddSirenActivity.this, "already exist!", Toast.LENGTH_SHORT).show();
                }
            }
        }

        catch (NumberFormatException e ){
            Toast.makeText( AddSirenActivity.this, "Waypoint should be number !", Toast.LENGTH_SHORT).show();
        }
    }
}
