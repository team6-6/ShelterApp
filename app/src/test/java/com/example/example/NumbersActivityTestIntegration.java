package com.example.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@Config(sdk = 27)
@RunWith(RobolectricTestRunner.class)
public class NumbersActivityTestIntegration {


    @Test
    public void TransferInfoTest(){
        NumbersActivity activity= Robolectric.buildActivity(NumbersActivity.class).create().get();
        User use=new User();
        use.setName("yaelbu90");
        use.setPassword("Aa123456");
        use.setPermission("C");
        activity.setUser1(use);
        assertEquals(activity.CheckPermissions(activity.user1.permission),"Civilian");
    }


    @Test
    public void ViewOnScreenTest(){
        NumbersActivity activity= Robolectric.buildActivity(NumbersActivity.class).create().get();
        assertTrue(activity.viewData());
    }



}
