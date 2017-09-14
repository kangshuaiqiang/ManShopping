package bwie.com.manshopping.MyUtils;

import android.app.Application;
import android.content.SharedPreferences;


/**
 * Created by peng on 2017/9/1.
 */

public class MyApp extends Application {

    private HttpUtil httpUtil;
    private SharedPreferences logging_status;
    private SharedPreferences.Editor edit;

    @Override
    public void onCreate() {
        super.onCreate();
        httpUtil = HttpUtil.getHttpUtil(this.getApplicationContext());
        initSharedPreferences();
    }

    public HttpUtil getHttpUtil() {
        return httpUtil;
    }


    private void initSharedPreferences() {
        logging_status = getSharedPreferences("logging_status", MODE_PRIVATE);
        edit = logging_status.edit();
    }

    public void setShared(String name) {
        edit.putBoolean(name, true);
        edit.commit();
    }


    public boolean getShared(String name){
        boolean aBoolean = logging_status.getBoolean(name, false);
        return aBoolean;
    }
}
