package com.example.traindemo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.traindemo.R;

/**
 * Created by 汤洪 on 2018/3/21.
 */

public class TrainsAdapter extends CursorAdapter {
    private Context mContext;

    public TrainsAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        this.mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_train, null, false);
        ViewHolder holder = new ViewHolder();
        holder.mTvTrainNo = (TextView) root.findViewById(R.id.id_tv_item_no);
        holder.mTvStarting = (TextView) root.findViewById(R.id.id_tv_item_starting);
        holder.mTvEnding = (TextView) root.findViewById(R.id.id_tv_item_ending);
        holder.mTvPrice = (TextView) root.findViewById(R.id.id_tv_item_price);
        holder.mTvTime = (TextView) root.findViewById(R.id.id_tv_item_time);
        root.setTag(holder);
        return root;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.mTvTrainNo.setText("车次: " + cursor.getString(cursor.getColumnIndex("train_no")));
        holder.mTvStarting.setText("始发地: " + cursor.getString(cursor.getColumnIndex("train_starting")));
        holder.mTvEnding.setText("目的地: " + cursor.getString(cursor.getColumnIndex("train_ending")));
        holder.mTvPrice.setText("价格: " + cursor.getString(cursor.getColumnIndex("train_price")));
        holder.mTvTime.setText(cursor.getString(cursor.getColumnIndex("train_time")));

    }

    @Override
    protected void onContentChanged() {
        super.onContentChanged();
    }

    class ViewHolder {
        TextView mTvTrainNo;
        TextView mTvStarting;
        TextView mTvEnding;
        TextView mTvPrice;
        TextView mTvTime;
    }
}
