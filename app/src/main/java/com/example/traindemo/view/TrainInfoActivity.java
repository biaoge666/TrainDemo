package com.example.traindemo.view;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.traindemo.R;
import com.example.traindemo.adapter.TrainInfoAdapter;
import com.example.traindemo.database.TrainDao;

public class TrainInfoActivity  extends BaseActivity {

    private ListView mLvFlights;
    private ListAdapter mAdapter;
    private Cursor mCursor;
    private TrainDao mFlightDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initEvent() {
        mLvFlights.setAdapter(mAdapter);
    }

    @Override
    protected String setLabel() {
        return "车次信息";
    }

    @Override
    protected int setViewLayout() {
        return R.layout.activity_train_info;
    }

    @Override
    protected void initData() {
        mFlightDAO = new TrainDao();
        mCursor = mFlightDAO.rawQuery("select * from train_capacity", null);
        mAdapter = new TrainInfoAdapter(this, mCursor
                , CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    @Override
    protected void initView() {
        mLvFlights = (ListView) findViewById(R.id.id_lv_trains_info);
    }
}
