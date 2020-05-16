package com.example.example;

import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.junit.runners.model.InitializationError;
import android.os.Build;

import static org.junit.Assert.*;

@Config(sdk = 21)
//@RunWith(RobolectricTestRunner.class)
public class NumbersActivityTestIntegration extends RobolectricTestRunner{


    public NumbersActivityTestIntegration(Class<?> testClass) throws InitializationError {
        super(testClass);
    }
    
    
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
