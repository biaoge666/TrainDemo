package com.example.traindemo;

import android.app.Application;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
       super.onCreate();
        initDB();
    }

    private void initDB() {
        String DB_DIR_PATH = "/data/data/" + this.getPackageName() + "/databases";
        // 初始化数据库
        if (!new File(DB_DIR_PATH + "/traindemo.db").exists()) {
            try {
                new File(DB_DIR_PATH).mkdir();
                FileOutputStream fos = new FileOutputStream(DB_DIR_PATH + "/traindemo.db");
                //由于数据库是在assets文件夹下，所以这里用getAssets方法
                InputStream is = getAssets().open("traindemo.db");
                byte[] buf = new byte[1024];
                int len;
                while ((len = is.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                /*
                * 一定不要忘了关闭InputStream和FileOutStream
                * */
                is.close();
                fos.close();
            } catch (IOException e) {
                Log.v("LOG", e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

