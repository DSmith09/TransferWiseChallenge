package com.example.dmsmith.transferwisecodechallenge;

import com.example.dmsmith.transferwisecodechallenge.activity.LoginActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class LoginActivityTest {
    /**
     * Simple Test to test Capabilities of Roboletric
     */
    @Test
    public void testLoginButton() {
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        loginActivity.findViewById(R.id.login_button).performClick();
        ShadowActivity activity = shadowOf(loginActivity);
        assertNotNull(activity.getNextStartedActivity());
    }
}