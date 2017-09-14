package bwie.com.manshopping.MyActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import bwie.com.manshopping.MyUtils.HttpUtil;
import bwie.com.manshopping.MyUtils.MyApp;


/**
 * Created by peng on 2017/9/1.
 */

public class BaseActivity_2 extends AppCompatActivity {

    protected MyApp myApp;
    protected HttpUtil httpUtil;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApp = (MyApp) getApplication();
        httpUtil = myApp.getHttpUtil();


    }


}
