package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ReportsActivity extends AppCompatActivity {
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private static final String TAG = "ReportsActivity";
    public int count;
    public double ans1,ans2,ans3,ans4,ans5,average;
    TextView res1,res2,res3,res4,res5,res6,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        res1 = (TextView) findViewById(R.id.res1);
        res2 = (TextView) findViewById(R.id.res2);
        res3 = (TextView) findViewById(R.id.res3);
        res4 = (TextView) findViewById(R.id.res4);
        res5 = (TextView) findViewById(R.id.res5);
        res6 = (TextView) findViewById(R.id.res6);
        back= (TextView) findViewById(R.id.backac);
        db.collection("Rating").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                   ans1=0;
                   ans2=0;
                   ans3=0;
                   ans4=0;
                   ans5=0;
                   average=0;
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        count++;
                        double rate1 = document.getDouble("Question1");
                        ans1 += rate1;
                        double rate2 = document.getDouble("Question2");
                        ans2 += rate2;
                        double rate3 = document.getDouble("Question3");
                        ans3 += rate3;
                        double rate4 = document.getDouble("Question4");
                        ans4 += rate4;
                        double rate5 = document.getDouble("Question5");
                        ans5 += rate5;
                    }
                    res1.setText("Result: "+ans1/count);
                    res2.setText("Result: "+ans2/count);
                    res3.setText("Result: "+ans3/count);
                    res4.setText("Result: "+ans4/count);
                    res5.setText("Result: "+ans5/count);
                    average= ((ans1/count)+(ans2/count)+(ans3/count)+(ans4/count)+(ans5/count))/5;
                    res6.setText("Result: "+average);


                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportsActivity.this, MainAdminActivity.class);
                startActivity(intent);
            }
        });
    }
}
