package com.doordash;

import android.content.Context;

import com.doordash.database.DbHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by SwethaAmbati on 5/24/17.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21)
public class DbHelperTest {
    private DbHelper dbHelper;
    Context context;

    @Before
    public void setup() {
        context = RuntimeEnvironment.application;
        dbHelper = new DbHelper(context);

    }

    @After
    public void tearDown() {
        dbHelper.close();
    }

    @Test
    public void testDbInsertion() {



    }
}