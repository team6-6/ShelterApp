package com.example.example;

import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;
import static org.testng.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, packageName="com.example.example")

public class NumbersActivityTestIntegration {


    @Test
    public void TransferInfoTest(){

        //add login
        User use=new User();
        use.setPermission("C");
        use.setName("yaelbu90");
        use.setPassword("Ss123456");
        MainActivity main = Robolectric.buildActivity(MainActivity.class).create().get();
        ((TextView)main.findViewById(R.id.username)).setText(use.name);
        ((TextView)main.findViewById(R.id.password)).setText(use.password);
        main.setUserInfo(main.usernameId.getText().toString(),main.passwordId.getText().toString());
        main.setUserPermission("C");
        ((Button)main.findViewById(R.id.loginBtn)).performClick();
        main.CheckPermissions(main.user.permission);
        shadowOf(Looper.getMainLooper()).idle();
        assertEquals(main.isFinishing(),true);
        NumbersActivity activity= Robolectric.buildActivity(NumbersActivity.class).create().get();
        activity.setUser1(use);
        assertEquals(activity.CheckPermissions(activity.user1.permission),"Civilian");

    }
}
