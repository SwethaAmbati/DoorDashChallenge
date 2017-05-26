package com.doordash;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.test.ApplicationTestCase;

import com.doordash.fragment.DiscoverFragment;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by SwethaAmbati on 5/24/17.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21)
public class FragmentTest extends ApplicationTestCase<Application> {

    public FragmentTest() {
        super(Application.class);
    }

    MainActivity mainActivity;
    DiscoverFragment discoverFragment;

    @Before
    public void setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        discoverFragment = new DiscoverFragment();
        startFragment(discoverFragment);
    }

    @Test
    public void testMainActivity() {
        Assert.assertNotNull(mainActivity);
    }


    private void startFragment( Fragment fragment ) {
        FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null );
        fragmentTransaction.commit();
    }
}