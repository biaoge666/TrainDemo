package com.example.traindemo.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 汤洪 on 2018/3/19.
 */

public class OrdersDao {
    private DBHelper mHelper;
    private static final String TABLE_NAME = "orders";

    public OrdersDao() {
        mHelper = new DBHelper();
    }

    public int insertOrders(ContentValues values) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int row = (int) db.insert(TABLE_NAME, null, values);
        db.close();
        return row;
    }


    public Cursor queryOrders(String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db.query(false, TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
    }

    public int updateOrders(ContentValues values, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int deleteOrders(String whereClause, String[] whereArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public Cursor rawQuery(String sql, String[] args) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db.rawQuery(sql, args);
    }
}
