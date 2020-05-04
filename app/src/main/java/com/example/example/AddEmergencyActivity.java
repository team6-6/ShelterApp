package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddEmergencyActivity extends AppCompatActivity {

    private static final String TAG = "AddEmergencyActivity";
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private FirebaseFirestore db2=FirebaseFirestore.getInstance();
    EditText nameOrganization, num;
    Button addNum;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emergency);
        nameOrganization = (EditText) findViewById(R.id.AddOrganization);
        num = (EditText) findViewById(R.id.addNumber);
        addNum = (Button) findViewById(R.id.buttonaddEmerg);
        back= (TextView) findViewById(R.id.backaddemerg);

        addNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddEmergencyActivity.this, EmergencyMenuActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addNumber() {
        final String name = nameOrganization.getText().toString().trim();
        try{
            Double Numberadding = Double.parseDouble(num.getText().toString());

            if (name.equals("")) {
                Toast.makeText(AddEmergencyActivity.this, "Field name is empty !", Toast.LENGTH_SHORT).show();
            }
            else{
                try {

                    // Create a new shelter
                    final Map<String, Object> numbers_details = new HashMap<>();
                    numbers_details.put("number", Numberadding);



                    db.collection("Emergency").document(name).get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (!documentSnapshot.exists()) {
                                        db2.collection("Emergency").document(name).set(numbers_details)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void documentReference) {
                                                        Toast.makeText(AddEmergencyActivity.this, "Number added", Toast.LENGTH_SHORT).show();
                                                        Log.d(TAG, "DocumentSnapshot added with ID: " + name);
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Log.w(TAG, "Error adding document", e);
                                                        Toast.makeText(AddEmergencyActivity.this, "Already Exist!", Toast.LENGTH_SHORT).show();

                                                    }
                                                });
                                    }
                                    else{
                                        Toast.makeText(AddEmergencyActivity.this, "Number alredy exist", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(AddEmergencyActivity.this, "already exist!", Toast.LENGTH_SHORT).show();
                }

            }
        }

        catch (NumberFormatException e ){
            Toast.makeText( AddEmergencyActivity.this, "Waypoint should be number !", Toast.LENGTH_SHORT).show();
        }

    }
}
