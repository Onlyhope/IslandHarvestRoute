package com.example.aaron.islandharvestroute;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aaron on 4/18/2016.
 */
public class IHDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "IslandHarvestDatabase.db";
    private static final String TABLE_NAME = "food_log";
    private static final String COL1_ID = "ID";
    private static final String COL2_TYPE = "Type";
    private static final String COL3_QUANTITY = "Quantity";
    private static final String COL4_DESCRIPTION = "Description";

    public IHDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY," +
                "TYPE TEXT, QUANTITY DOUBLE PRECISION, DESCRIPTION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String insertData(String type, String description, double quantity) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_TYPE, type);
        contentValues.put(COL3_QUANTITY, quantity);
        contentValues.put(COL4_DESCRIPTION, description);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return "Data inserted! " + result;
    }
}
