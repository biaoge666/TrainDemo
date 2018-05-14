package com.example.traindemo.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.traindemo.R;
import com.example.traindemo.database.TrainDao;

/**
 * Created by 汤洪 on 2018/3/21.
 */

public class AddTrainFragment extends Fragment implements View.OnClickListener {

    private View mRoot;
    private TextInputLayout mTilNo;
    private TextInputLayout mTilStarting;
    private TextInputLayout mTilEnding;
    private TextInputLayout mTilTime;
    private TextInputLayout mTilPrice;
    private Button mBtnSubmit;
    private TrainDao mTrainDAO;

    public AddTrainFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();
        mTrainDAO = new TrainDao();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_add_train, container, false);
        initView();
        return mRoot;
    }

    private void initView() {
        mTilNo = (TextInputLayout) mRoot.findViewById(R.id.id_til_add_no);
        mTilStarting = (TextInputLayout) mRoot.findViewById(R.id.id_til_add_starting);
        mTilEnding = (TextInputLayout) mRoot.findViewById(R.id.id_til_add_ending);
        mTilTime = (TextInputLayout) mRoot.findViewById(R.id.id_til_add_time);
        mTilPrice = (TextInputLayout) mRoot.findViewById(R.id.id_til_add_price);
        mBtnSubmit = (Button) mRoot.findViewById(R.id.id_btn_flight_submit);
        mBtnSubmit.setOnClickListener(this);
        mTilNo.setHint("车次");
        mTilStarting.setHint("起点");
        mTilEnding.setHint("终点");
        mTilTime.setHint("时间");
        mTilPrice.setHint("价格");
    }


    /**
     * 向数据库插入数据
     */
    @Override
    public void onClick(View v) {
        // 五个字段均非空
        String no = mTilNo.getEditText().getText().toString();
        String starting = mTilStarting.getEditText().getText().toString();
        String ending = mTilEnding.getEditText().getText().toString();
        String price = mTilPrice.getEditText().getText().toString();
        String trainTime = mTilTime.getEditText().getText().toString();
        if (!TextUtils.isEmpty(no)
                && !TextUtils.isEmpty(starting)
                && !TextUtils.isEmpty(ending)
                && !TextUtils.isEmpty(price)
                && !TextUtils.isEmpty(trainTime)) {
            ContentValues values = new ContentValues();
            values.put("train_no", no);
            values.put("train_starting", starting);
            values.put("train_ending", ending);
            values.put("train_price", price);
            values.put("train_time", trainTime);
            int row = mTrainDAO.insertTrain(values);
            if (row > 0) {
                Snackbar.make(mRoot, "添加成功", Snackbar.LENGTH_SHORT).show();
                getLoaderManager().restartLoader(AddTicketFragment.LOADER_ID, null, AddTicketFragment.mLoader);
            }
        } else {
            Snackbar.make(mRoot, "输入错误，请重新输入", Snackbar.LENGTH_SHORT).show();
        }
    }
}

