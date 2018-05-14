package com.example.traindemo.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.traindemo.R;
import com.example.traindemo.database.GuestDao;


public class LoginActivity extends AppCompatActivity{

    private GuestDao mGuestDao;
    private Handler mHandler;

    private EditText mEtUser;
    private EditText mEtPwd;
    private View mProgressView;
    private View mLoginFormView;
    private Button mBtnLogin;
    private Toolbar mToolbar;

    @Override
    protected void onStart() {
        super.onStart();
        mGuestDao = new GuestDao();
        mHandler = new Handler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mToolbar = (Toolbar) findViewById(R.id.id_toolbar_login);
        mToolbar.setTitle("用户登陆");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        initView();
        mEtPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }
    private void initView() {
        mEtUser = (EditText) findViewById(R.id.id_et_guest_name);
        mEtPwd = (EditText) findViewById(R.id.id_et_guest_pwd);
        mBtnLogin = (Button) findViewById(R.id.id_btn_guest_login);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEtUser.setError(null);
        mEtPwd.setError(null);

        // Store values at the time of the login attempt.
        final String userName = mEtUser.getText().toString();
        String password = mEtPwd.getText().toString();
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mEtPwd.setError("密码不合法");
            focusView = mEtPwd;
            cancel = true;
        }

        // Check for a valid userName address.
        if (TextUtils.isEmpty(userName)) {
            mEtUser.setError("用户名不能为空");
            focusView = mEtUser;
            cancel = true;
        } else if (!isUserValid(userName)) {
            mEtUser.setError("用户名必须大于5位");
            focusView = mEtUser;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // 登陆操作
            Cursor c = mGuestDao.queryGuest("guest_name=? and guest_pwd=?"
                    , new String[]{userName, password});
            // 没有查询结果时,表示登陆失败
            if (!c.moveToNext()) {
                Snackbar.make(mLoginFormView, "用户名或密码错误", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(mLoginFormView, "登陆成功", Snackbar.LENGTH_SHORT).show();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this, GuestMenuActivity.class);
                        intent.putExtra(RegisterActivity.SEND_USER_NAME, userName);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }
        }
    }

    private boolean isUserValid(String user) {
        return user.length() >= 5;
    }

    private boolean isPasswordValid(String password) {

        return password.length() >= 5;
    }


    public void tv_signUp(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}

