package com.example.traindemo.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 汤洪 on 2018/3/17.
 */

public class GuestDao {
    private DBHelper mHelper;
    private static final String TABLE_NAME = "guest";

    public GuestDao() {
        mHelper = new DBHelper();
    }

    public int insertGuest(ContentValues values) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int row = (int) db.insert(TABLE_NAME, null, values);
        db.close();
        return row;
    }


    public Cursor queryGuest(String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db.query(false, TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
    }

    public int updateGuest(ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int deleteGuest(String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }
}
