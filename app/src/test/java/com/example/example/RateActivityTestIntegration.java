package com.example.example;

import android.content.Intent;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@Config(sdk = 21, packageName="com.example.example")
@RunWith(RobolectricGradleTestRunner.class)
public class RateActivityTestIntegration {

    //working!!
    @Test
    public void SendWhatYouGetAdminTest(){

        ReportRatingActivity rateActivity= Robolectric.buildActivity(ReportRatingActivity.class).create().get();
        Rating rat1=new Rating(3,3,3,3,3);
        rateActivity.ratingArrayList.add(rat1);
        Rating rat2=new Rating(1,1,1,1,1);
        rateActivity.ratingArrayList.add(rat2);
        Rating rat3=new Rating(5,5,5,5,5);
        rateActivity.ratingArrayList.add(rat3);
        for (int i=0;i<rateActivity.ratingArrayList.size();i++){
            rateActivity.CalcRate1(rateActivity.ratingArrayList.get(i).getQ1());
            rateActivity.CalcRate2(rateActivity.ratingArrayList.get(i).getQ2());
            rateActivity.CalcRate3(rateActivity.ratingArrayList.get(i).getQ3());
            rateActivity.CalcRate4(rateActivity.ratingArrayList.get(i).getQ4());
            rateActivity.CalcRate5(rateActivity.ratingArrayList.get(i).getQ5());
        }
        rateActivity.count=rateActivity.ratingArrayList.size();
        rateActivity.CalcRatingreport();
        assertEquals(rateActivity.average,3.0,0.01);

    }

}
