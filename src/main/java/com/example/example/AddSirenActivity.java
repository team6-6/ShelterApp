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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddSirenActivity extends AppCompatActivity {
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "AddSirenActivity";
    EditText sirenId,Neibor, lat, lon;
    Button addSiren,back;

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
                    DocumentReference noteref = db.collection("zofar").document(siren);

                    // Create a new shelter
                    Map<String, Object> siren_details = new HashMap<>();
                    siren_details.put("Neiborhood", Neiborhood);
                    siren_details.put("lat", newlat);
                    siren_details.put("lon", newlon);


                    // Add a new document with a generated ID
                    db.collection("zofar").document(siren).set(siren_details)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + siren);
                                    Toast.makeText(AddSirenActivity.this, "Siren added to list", Toast.LENGTH_SHORT).show();

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
        catch (NullPointerException e ){
            Toast.makeText( AddSirenActivity.this, "Field is empty !", Toast.LENGTH_SHORT).show();
        }
        catch (NumberFormatException e ){
            Toast.makeText( AddSirenActivity.this, "Waypoint should be number !", Toast.LENGTH_SHORT).show();
        }
    }
}
