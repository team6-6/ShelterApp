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

@Config(sdk = 21,packageName="com.example.example")
@RunWith(RobolectricGradleTestRunner.class)
public class EmployeeSirensActivityTestIntegration {

    @Test
    public void ViewSirenListTest() {
        MainActivity main = Robolectric.buildActivity(MainActivity.class).create().get();
        ((TextView)main.findViewById(R.id.username)).setText("dana14");
        ((TextView)main.findViewById(R.id.password)).setText("Dana1234");
        main.setUserInfo(main.usernameId.getText().toString(),main.passwordId.getText().toString());
        main.setUserPermission("B");
        ((Button)main.findViewById(R.id.loginBtn)).performClick();
        main.CheckPermissions(main.user.permission);
        shadowOf(Looper.getMainLooper()).idle();
        assertEquals(main.isFinishing(),true);
        EmployeeSirensActivity activity= Robolectric.buildActivity(EmployeeSirensActivity.class).create().get();
        ArrayList<Request> meetList = new ArrayList<Request>();
        Siren siren1 = new Siren();
        siren1.setName("22");
        siren1.setNeiborhood("ramot");
        Siren siren2 = new Siren();
        siren2.setName("23");
        siren2.setNeiborhood("a");
        Siren siren3 = new Siren();
        siren3.setName("90");
        siren3.setNeiborhood("h");
        activity.arrayList.add(siren1.getName());
        activity.arrayList.add(siren2.getName());
        activity.arrayList.add(siren3.getName());
        activity.fieldsearch.setText("90");
        assertEquals(activity.listView.getAdapter().getCount(),1,0);
    }

}
