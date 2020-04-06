package com.example.example;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.app.PendingIntent.getActivity;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> loginActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity loginActivity = null;

    @Before
    public void setUp() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        //this is the key part
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //this is the key part
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        loginActivity = loginActivityActivityTestRule.launchActivity(intent);
    }

    @Test
    public void checkLoginButton() throws Throwable {
        MainActivity d = null;
        onView(withId(R.id.registerBtn)).perform(click());
        assertTrue(true);
    }

    @After
    public void tearDown() throws Exception
    {
        Intent intent = new Intent(getActivity(), getActivity().getClass());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Removes other Activities from stack
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        loginActivity.startActivity(intent);

    }

}