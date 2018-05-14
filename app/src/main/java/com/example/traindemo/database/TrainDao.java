package com.example.traindemo.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 汤洪 on 2018/3/19.
 */

public class TrainDao {
    private DBHelper mHelper;
    private static final String TABLE_NAME = "trains";

    public TrainDao() {
        mHelper = new DBHelper();
    }

    public int insertTrain(ContentValues values) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int row = (int) db.insert(TABLE_NAME, null, values);
        db.close();
        return row;
    }


    public Cursor queryTrain(String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db.query(false, TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
    }

    public int updateTrain(ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int deleteTrain(String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public Cursor rawQuery(String sql, String[] args) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db.rawQuery(sql, args);
    }
}
