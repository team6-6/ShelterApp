package com.example.example;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@Config(sdk = 21, packageName="com.example.example")
@RunWith(RobolectricGradleTestRunner.class)
public class MainEmployeeActivityTestIntegration {

    //working!!
    @Test
    public void SendWhatYouGetAdminTest(){
        MainAdminActivity activity= Robolectric.buildActivity(MainAdminActivity.class).create().get();
        User use=new User();
        use.setName("dana13");
        use.setPassword("Dana1234");
        use.setPermission("B");
        activity.getIntent().putExtra("EXTRA_SESSION_ID",use.getName());
        activity.setSessionId();
        Intent intent=activity.ToChangePassword();
        assertEquals(intent.getExtras().getString("EXTRA_SESSION_ID"),use.getName());
    }



}