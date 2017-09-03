package bwie.com.manshopping.MyUtils;

import android.app.Application;


/**
 * Created by peng on 2017/9/1.
 */

public class MyApp extends Application {

    private HttpUtil httpUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        httpUtil = HttpUtil.getHttpUtil(this.getApplicationContext());
    }

    public HttpUtil getHttpUtil() {
        return httpUtil;
    }
}
