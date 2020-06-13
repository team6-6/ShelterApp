package com.example.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ReportRatingActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private static final String TAG = "ReportRatingActivity";
    public int count;
    public float ans1,ans2,ans3,ans4,ans5,average;
    ArrayList<Rating> ratingArrayList=new ArrayList<>();

    TextView res1,res2,res3,res4,res5,res6;
    Button back;
    String sessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        db=FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_report_rating);
        res1 = (TextView) findViewById(R.id.res1);
        res2 = (TextView) findViewById(R.id.res2);
        res3 = (TextView) findViewById(R.id.res3);
        res4 = (TextView) findViewById(R.id.res4);
        res5 = (TextView) findViewById(R.id.res5);
        res6 = (TextView) findViewById(R.id.res6);
        back= (Button) findViewById(R.id.backac);
        sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

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
                        CalcRate1(rate1);
                        double rate2 = document.getDouble("Question2");
                        CalcRate2(rate2);
                        double rate3 = document.getDouble("Question3");
                        CalcRate3(rate3);
                        double rate4 = document.getDouble("Question4");
                        CalcRate4(rate4);
                        double rate5 = document.getDouble("Question5");
                        CalcRate5(rate5);
                    }

                    CalcRatingreport();

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportRatingActivity.this, ReportsActivity.class);
                intent.putExtra("EXTRA_SESSION_ID", sessionId);

                startActivity(intent);
            }
        });
    }

    void CalcRate1(double ratingnum){
        ans1 += ratingnum;
    }
    void CalcRate2(double ratingnum){
        ans2 += ratingnum;

    }
    void CalcRate3(double ratingnum){
        ans3 += ratingnum;

    }
    void CalcRate4(double ratingnum){
        ans4 += ratingnum;

    }
    void CalcRate5(double ratingnum){
        ans5 += ratingnum;

    }
    void CalcRatingreport(){
        res1.setText("Result: "+ans1/count);
        res2.setText("Result: "+ans2/count);
        res3.setText("Result: "+ans3/count);
        res4.setText("Result: "+ans4/count);
        res5.setText("Result: "+ans5/count);
        average= ((ans1/count)+(ans2/count)+(ans3/count)+(ans4/count)+(ans5/count))/5;
        res6.setText("Result: "+average);
    }
}
