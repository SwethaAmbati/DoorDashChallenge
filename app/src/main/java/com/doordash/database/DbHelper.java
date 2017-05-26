package com.doordash.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SwethaAmbati on 5/24/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = "DbHelper";
    private static DbHelper mDbHelper;
    // Database Info
    private static final String DATABASE_NAME = "FavoritesDatabase";
    private static final int DATABASE_VERSION = 4;

    //Table Names
    private static final String FAVORITE_TABLE = "favorite_table";

    //favorite_table Table Columns
    private static final String ID = "_id";


    public static synchronized DbHelper getInstance(Context context) {
        if (mDbHelper == null) {
            mDbHelper = new DbHelper(context.getApplicationContext());
        }
        return mDbHelper;
    }

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate called");
        String CREATE_FAVORITE_TABLE = "CREATE TABLE " + FAVORITE_TABLE +
                "(" +
                ID + " INTEGER PRIMARY KEY " +
                ")";
        sqLiteDatabase.execSQL(CREATE_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int x, int y) {
        if (x != y) {
            // Simplest implementation is to drop all old tables and recreate them
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FAVORITE_TABLE);

            onCreate(sqLiteDatabase);
        }
    }

    //  add ids of favorite restaurants to the FAVORITE_TABLE
    public void addToFavorites(int id) {
        Log.d(TAG, "addToFavorites called");
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(ID, id);
            sqLiteDatabase.insertOrThrow(FAVORITE_TABLE, null, values);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    //  retrieve all ids of favorite restaurants from the FAVORITE_TABLE
    public List<Integer> getAllFavorites() {
        Log.d(TAG, "getAllFavorites called");
        List<Integer> favIds = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + FAVORITE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            do {
                favIds.add(cursor.getInt(cursor.getColumnIndex(ID)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return favIds;
    }

    //  delete id of favorite restaurant in the FAVORITE_TABLE
    public void removeFromFavorite(int id) {
        Log.d(TAG, "removeFromFavorite called");
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.execSQL("delete from " + FAVORITE_TABLE + " where _id ='" + id + "'");
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d(TAG, "Error while trying to delete  users detail");
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }
}



