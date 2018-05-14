package com.example.traindemo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.traindemo.R;

/**
 * Created by 汤洪 on 2018/3/20.
 */

public class OrdersAdapter extends CursorAdapter {

    private LayoutInflater mInflater;
    private View mRoot;
    private boolean mIsPaying;

    public OrdersAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        mRoot = mInflater.inflate(R.layout.item_orders, null, false);
        mIsPaying = Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("is_paying")));
        ViewHolder holder = new ViewHolder();
        holder.mTvStarting = mRoot.findViewById(R.id.id_tv_orders_starting);
        holder.mTvEnding = mRoot.findViewById(R.id.id_tv_orders_ending);
        holder.mTvSeatID = mRoot.findViewById(R.id.id_tv_orders_seat_id);
        holder.mTvSeatInfo = mRoot.findViewById(R.id.id_tv_orders_seat_info);
        holder.mTvTime = mRoot.findViewById(R.id.id_tv_orders_train_time);
        holder.mIvPaying = mRoot.findViewById(R.id.id_iv_is_paying);
        mRoot.setTag(holder);
        return mRoot;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.mTvStarting.setText(cursor.getString(cursor.getColumnIndex("train_starting")));
        holder.mTvEnding.setText(cursor.getString(cursor.getColumnIndex("train_ending")));
        holder.mTvSeatID.setText(cursor.getString(cursor.getColumnIndex("seat_id")));
        holder.mTvSeatInfo.setText(cursor.getString(cursor.getColumnIndex("seat_info")));
        holder.mTvTime.setText(cursor.getString(cursor.getColumnIndex("train_time")));
        if (mIsPaying) {
            holder.mIvPaying.setImageResource(R.drawable.item_is_paying);
        }
    }

    /**
     * 判断当前行是否可以单击
     */
    @Override
    public boolean isEnabled(int position) {

        return super.isEnabled(position);
    }

    public static class ViewHolder {
        public TextView mTvStarting;
        public TextView mTvEnding;
        public TextView mTvSeatID;
        public TextView mTvSeatInfo;
        public TextView mTvTime;
        public ImageView mIvPaying;
    }
}
