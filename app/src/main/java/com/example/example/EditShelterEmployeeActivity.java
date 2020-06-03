package com.example.example;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditShelterEmployeeActivity extends AppCompatActivity {

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG = "EditShelterEmployeeActivity";
    String currentShelter;
    private TextView Sheltername,lat,lon, information;
    private Button edit;
    private TextView btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shelter_employee);
        currentShelter = getIntent().getExtras().get("ShelterInfo").toString();

        lat = (TextView) findViewById(R.id.lanShelterEdit);
        lon = (TextView) findViewById(R.id.lonShelterEdit);
        Sheltername = (TextView) findViewById(R.id.nameshelterEdit);
        information=(TextView) findViewById(R.id.TextEditPage);
        btn=(TextView)findViewById(R.id.BackEdit);
        edit=(Button) findViewById(R.id.EditButton);
        currentShelter = getIntent().getExtras().get("ShelterInfo").toString();
        Sheltername.setText("Shelter name : " + currentShelter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent first = new Intent(EditShelterEmployeeActivity.this, CivilianShelterActivity.class);
                first.putExtra("KIND_OF_PERMISSION", "Employee");
                startActivity(first);

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textupdate=information.getText().toString();
                db.collection("shelter").document(currentShelter).update("info",textupdate).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(EditShelterEmployeeActivity.this, "Edit Information!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(EditShelterEmployeeActivity.this, "something wrong ! try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        db.collection("shelter").document(currentShelter).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Double lati = documentSnapshot.getDouble("lat");
                            Double longi = documentSnapshot.getDouble("lon");
                            String text = documentSnapshot.getString("info");
                            lat.setText("Shelter latitude : "+String.valueOf(lati));
                            lon.setText("Shelter longitute : "+String.valueOf(longi));
                            information.setText(text);

                        }

                    }

                });

    }



}