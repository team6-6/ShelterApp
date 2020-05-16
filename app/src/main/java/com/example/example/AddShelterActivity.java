package com.example.example;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
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
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddShelterActivity extends AppCompatActivity {
    public FirebaseFirestore db;
    public FirebaseFirestore db2;
    private static final String TAG = "AddShelterActivity";
    EditText nameShelter, lat, lon;
    Button addShelter,back;
    private boolean flag=false;
    Checkfunction checkfunction=new Checkfunction();

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shelter);
        FirebaseApp.initializeApp(this);
        db= FirebaseFirestore.getInstance();
        db2 = FirebaseFirestore.getInstance();
        nameShelter = (EditText) findViewById(R.id.name);
        lat = (EditText) findViewById(R.id.lat);
        lon = (EditText) findViewById(R.id.lon);
        addShelter = (Button) findViewById(R.id.AddingBtn);
        back= (Button) findViewById(R.id.goback);

        addShelter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nameShelter.getText().toString().trim();
                Double newlat = Double.parseDouble(lat.getText().toString());
                Double newlon = Double.parseDouble(lon.getText().toString());
                addShelter(name, newlat, newlon, false);
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

    public boolean addShelter(final String name, Double newlat, Double newlon,boolean report) {

        if(report){
            if(checkfunction.notEmpty(name)==1){
                return true;
            }
        }

        try{
            FirebaseApp.initializeApp(this);
            db= FirebaseFirestore.getInstance();
            db2 = FirebaseFirestore.getInstance();

            if (name.equals("")) {
                setFlag(false);
                Toast.makeText(AddShelterActivity.this, "Field name is empty !", Toast.LENGTH_SHORT).show();
            }
            else{
                try {

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
                                                        showSuceesMessage();
                                                        setFlag(true);
                                                        Log.d(TAG, "DocumentSnapshot added with ID: " + name);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        setFlag(false);
                                                        showfailsMessage();
                                                        Log.w(TAG, "Error adding document", e);

                                                    }
                                                });
                                    }
                                    else{
                                        setFlag(false);
                                        Toast.makeText(AddShelterActivity.this, "Shelter alredy exist", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    setFlag(false);
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                } catch (Exception e) {
                    setFlag(false);
                    Toast.makeText(AddShelterActivity.this, "already exist!", Toast.LENGTH_SHORT).show();
                }

            }
        }

        catch (NumberFormatException e ){
            setFlag(false);
            Toast.makeText( AddShelterActivity.this, "Waypoint should be number !", Toast.LENGTH_SHORT).show();
        }
        return flag;
    }


    public void showSuceesMessage() {
        Toast.makeText(AddShelterActivity.this, "Shelter added", Toast.LENGTH_SHORT).show();

    }

    public void showfailsMessage() {
        Toast.makeText(AddShelterActivity.this, "Already Exist!", Toast.LENGTH_SHORT).show();

    }

    @VisibleForTesting
    protected FirebaseFirestore fireBaseAuthMock(){
        FirebaseApp.initializeApp(this);
        FirebaseFirestore firebaseAuth = FirebaseFirestore.getInstance();
        return firebaseAuth;
    }

}
