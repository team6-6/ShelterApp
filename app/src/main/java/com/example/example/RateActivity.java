package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RateActivity extends AppCompatActivity {
    RatingBar ratingBar1,ratingBar2,ratingBar3,ratingBar4,ratingBar5;
    Button submit;
    TextView back;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG = "RateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        final String user = getIntent().getStringExtra("EXTRA_SESSION_ID2");
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar1);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar2);
        ratingBar3 = (RatingBar) findViewById(R.id.ratingBar3);
        ratingBar4 = (RatingBar) findViewById(R.id.ratingBar4);
        ratingBar5 = (RatingBar) findViewById(R.id.ratingBar5);
        back= (TextView) findViewById(R.id.backactivity);
        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float num1=ratingBar1.getRating();
                float num2=ratingBar2.getRating();
                float num3=ratingBar3.getRating();
                float num4=ratingBar4.getRating();
                float num5=ratingBar5.getRating();

                if (num1==0 || num2==0 || num3==0 || num4==0 || num5==0){
                    Toast.makeText(RateActivity.this, "Please answer all the questions", Toast.LENGTH_SHORT).show();
                }
                else {
                    Map<String, Object> data = new HashMap<>();
                    data.put("Question1", num1);
                    data.put("Question2", num2);
                    data.put("Question3", num3);
                    data.put("Question4", num4);
                    data.put("Question5", num5);

                    db.collection("Rating")
                            .add(data)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(RateActivity.this, "Your rating is accepted", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                                    Intent intent = new Intent(RateActivity.this, MainCivilianActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//check id user

                                    Intent intent = new Intent(RateActivity.this, MainCivilianActivity.class);
                                    intent.putExtra("EXTRA_SESSION_ID", user);
                                    startActivity(intent);

            }
        });
    }

}
