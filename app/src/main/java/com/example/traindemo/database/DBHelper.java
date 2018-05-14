package com.example.traindemo.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 汤洪 on 2018/3/17.
 */

public class DBHelper {
    private static final String DB_PATH = "/data/data/" +
            "com.example.traindemo/databases/traindemo.db";
    /**
     * 获取可读写的数据库
     */
    public SQLiteDatabase getWritableDatabase() {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }


    /**
     * 获取仅可读的数据库
     */
    public SQLiteDatabase getReadableDatabase() {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
    }

}
