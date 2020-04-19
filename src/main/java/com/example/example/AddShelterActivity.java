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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class AddShelterActivity extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public FirebaseFirestore db2 = FirebaseFirestore.getInstance();
    private static final String TAG = "AddShelterActivity";
    EditText nameShelter, lat, lon;
    Button addShelter,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shelter);
        nameShelter = (EditText) findViewById(R.id.name);
        lat = (EditText) findViewById(R.id.lat);
        lon = (EditText) findViewById(R.id.lon);
        addShelter = (Button) findViewById(R.id.AddingBtn);
        back= (Button) findViewById(R.id.goback);

        addShelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShelter();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddShelterActivity.this, AdminSheltersActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addShelter() {
        final String name = nameShelter.getText().toString().trim();
        try{
            Double newlat = Double.parseDouble(lat.getText().toString());
            Double newlon = Double.parseDouble(lon.getText().toString());

            if (name.equals("")) {
                Toast.makeText(AddShelterActivity.this, "Field name is empty !", Toast.LENGTH_SHORT).show();
            }
            else{
                try {
                    DocumentReference noteref = db.collection("shelter").document(name);

                    // Create a new shelter
                    final Map<String, Object> shelter_details = new HashMap<>();
                    shelter_details.put("lat", newlat);
                    shelter_details.put("lon", newlon);



                    db.collection("shelter").document(name).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (!documentSnapshot.exists()) {
                                        db2.collection("shelter").document(name).set(shelter_details)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void documentReference) {
                                                        Toast.makeText(AddShelterActivity.this, "Shelter added", Toast.LENGTH_SHORT).show();
                                                        Log.d(TAG, "DocumentSnapshot added with ID: " + name);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error adding document", e);
                                                        Toast.makeText(AddShelterActivity.this, "Already Exist!", Toast.LENGTH_SHORT).show();

                                                    }
                                                });
                                    }
                                    else{
                                        Toast.makeText(AddShelterActivity.this, "Shelter alredy exist", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(AddShelterActivity.this, "already exist!", Toast.LENGTH_SHORT).show();
                }

            }
        }
        catch (NullPointerException e ){
            Toast.makeText( AddShelterActivity.this, "Field is empty !", Toast.LENGTH_SHORT).show();
        }
        catch (NumberFormatException e ){
            Toast.makeText( AddShelterActivity.this, "Waypoint should be number !", Toast.LENGTH_SHORT).show();
        }

    }
}
