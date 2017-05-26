package com.doordash;

        import android.content.Context;
        import android.test.RenamingDelegatingContext;

        import com.doordash.database.DbHelper;

        import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.robolectric.RobolectricGradleTestRunner;
        import org.robolectric.RuntimeEnvironment;
        import org.robolectric.annotation.Config;

        import static org.junit.Assert.assertEquals;

/**
 * Created by SwethaAmbati on 5/24/17.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21)
class Dbtest {
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

        // Given
        int id1 = 123;
        int id2 = 234;

        // When
        dbHelper.addToFavorites(id1);
        dbHelper.addToFavorites(id2);

        // Then
        assertEquals(dbHelper.getAllFavorites(),"id1-id2-");
    }
}