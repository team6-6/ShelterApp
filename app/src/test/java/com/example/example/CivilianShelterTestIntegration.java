package com.example.example;

import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.robolectric.Shadows.shadowOf;
import static org.testng.Assert.assertEquals;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 21, packageName="com.example.example")
public class CivilianShelterTestIntegration {

    @Test
    public void ViewShelterListTest() {
        MainActivity main = Robolectric.buildActivity(MainActivity.class).create().get();
        ((TextView)main.findViewById(R.id.username)).setText("yaelbu90");
        ((TextView)main.findViewById(R.id.password)).setText("Ss123456");
        main.setUserInfo(main.usernameId.getText().toString(),main.passwordId.getText().toString());
        main.setUserPermission("C");
        ((Button)main.loginId).performClick();
        main.CheckPermissions(main.user.permission);
        shadowOf(Looper.getMainLooper()).idle();
        assertEquals(main.isFinishing(),true);
        CivilianShelterActivity activity= Robolectric.buildActivity(CivilianShelterActivity.class).create().get();
        Shelter shelter1 = new Shelter();
        shelter1.setName("b2");
        shelter1.setLat(0.009);
        shelter1.setLon(12.99);
        Shelter shelter2 = new Shelter();
        shelter2.setName("a2");
        shelter2.setLat(1.01);
        shelter2.setLon(2.22);
        Shelter shelter3 = new Shelter();
        shelter3.setName("a1");
        shelter3.setLat(12.0);
        shelter3.setLon(12.0);
        activity.arrayList.add(shelter1.getName());
        activity.arrayList.add(shelter2.getName());
        activity.arrayList.add(shelter3.getName());
        activity.fieldsearch.setText("a");
        assertEquals(activity.listView.getAdapter().getCount(),2,0);
    }
}
