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
public class MainCivilianActivityTestIntegration {

    //working!!
    @Test
    public void SendWhatYouGetAdminTest(){
        MainAdminActivity activity= Robolectric.buildActivity(MainAdminActivity.class).create().get();
        User use=new User();
        use.setName("yaelbu90");
        use.setPassword("Aa123456");
        use.setPermission("C");
        activity.getIntent().putExtra("EXTRA_SESSION_ID",use.getName());
        activity.setSessionId();
        Intent intent=activity.ToChangePassword();
        assertEquals(intent.getExtras().getString("EXTRA_SESSION_ID"),use.getName());
    }



}