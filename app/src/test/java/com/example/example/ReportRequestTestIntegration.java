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
public class ReportRequestTestIntegration {
    @Test
    public void ViewListReportTest() {
        MainActivity main = Robolectric.buildActivity(MainActivity.class).create().get();
        ((TextView)main.findViewById(R.id.username)).setText("edenda2");
        ((TextView)main.findViewById(R.id.password)).setText("Aa123456");
        main.setUserInfo(main.usernameId.getText().toString(),main.passwordId.getText().toString());
        main.setUserPermission("A");
        ((Button)main.findViewById(R.id.loginBtn)).performClick();
        main.CheckPermissions(main.user.permission);
        shadowOf(Looper.getMainLooper()).idle();
        assertEquals(main.isFinishing(),true);
        ReportRequestActivity activity= Robolectric.buildActivity(ReportRequestActivity.class).create().get();
        ArrayList<Request> meetList = new ArrayList<Request>();
        Request meet1 = new Request();
        meet1.setId("1");
        meet1.setMessage("hello");
        Request meet2 = new Request();
        meet2.setId("2");
        meet2.setMessage("yessss");
        Request meet3 = new Request();
        meet3.setId("3");
        meet3.setMessage("hoooo");
        activity.arrayList.add(meet1.getId());
        activity.arrayList.add(meet2.getId());
        activity.arrayList.add(meet3.getId());
        activity.fieldsearch.setText("1");
        assertEquals(activity.listView.getAdapter().getCount(),1,0);
    }
}
