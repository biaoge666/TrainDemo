package com.example.traindemo.database;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.widget.CursorAdapter;

/**
 * Created by 汤洪 on 2018/3/19.
 */

public class TrainLoader implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private CursorAdapter mAdapter;

    public TrainLoader(Context context, CursorAdapter adapter) {
        this.mAdapter = adapter;
        this.mContext = context;
    }
    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse(TrainProvider.TRAIN_URI);
        return new CursorLoader(mContext, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> Loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
