package com.example.example;

import android.content.Intent;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;
@Config(sdk = 21,packageName="com.example.example")
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTestIntegration {

    String name;
    //working!!
    @Test
    public void TransferInfoTest(){
        MainActivity activity= Robolectric.buildActivity(MainActivity.class).create().get();
        Intent intent=new Intent(activity,MainAdminActivity.class);
        User use=new User();
        use.setName("edenda2");
        use.setPassword("Aa123456");
        use.setPermission("A");
        activity.setUserInfo(use.name,use.password);
        activity.setUserPermission(use.permission);
        activity.CheckLogin(intent);
        assertEquals(intent.getExtras().getString("EXTRA_SESSION_ID"),"edenda2");
    }




}