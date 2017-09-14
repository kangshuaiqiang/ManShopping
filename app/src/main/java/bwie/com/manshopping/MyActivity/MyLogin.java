package bwie.com.manshopping.MyActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import bwie.com.manshopping.MyBean.Basebean;
import bwie.com.manshopping.MyBean.MyLoginBean;
import bwie.com.manshopping.MyUtils.Api;
import bwie.com.manshopping.MyUtils.OnNetListener;
import bwie.com.manshopping.Myfragment.MyPerson;
import bwie.com.manshopping.R;

public class MyLogin extends BaseActivity_2 implements View.OnClickListener {

    private ImageView mLoginBackImage;
    /**
     * 请输入用户名
     */
    private EditText mLogindName;
    /**
     * 请输入密码
     */
    private EditText mLoginPassword;
    /**
     * 登录
     */
    private Button mLogind;
    /**
     * 注册账号
     */
    private TextView mRegister;
    /**
     * 找回密码
     */
    private TextView mFind;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        initView();


    }

    private void initView() {
        mLoginBackImage = (ImageView) findViewById(R.id.login_backImage);
        mLoginBackImage.setOnClickListener(this);
        mLogindName = (EditText) findViewById(R.id.logind_name);
        mLoginPassword = (EditText) findViewById(R.id.login_password);
        mLogind = (Button) findViewById(R.id.logind);
        mLogind.setOnClickListener(this);
        mRegister = (TextView) findViewById(R.id.login_register);
        mRegister.setOnClickListener(this);
        mFind = (TextView) findViewById(R.id.find);
        mFind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_backImage:
                finish();
                break;
            case R.id.logind:
                initLogind();
                break;
            case R.id.login_register:
                startActivity(new Intent(MyLogin.this, MyRegister.class));
                break;
            case R.id.find:
                break;
        }
    }

    private static GetLoginName getLoginName;

    public static void setLoginName(GetLoginName etLoginName) {
        getLoginName = etLoginName;
    }

    public interface GetLoginName {
        void getLogin(String name);
    }

    private void initLogind() {
//        String loginname = getIntent().getStringExtra("loginname");
        //获得输入数据
        final String name = mLogindName.getText().toString();
        String pwd = mLoginPassword.getText().toString();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {

            Map<String, String> map = new HashMap<>();
            map.put("username", name);
            map.put("password", pwd);
            map.put("client", "android");
            httpUtil.post(MyLogin.this, Api.LOGIN, map, MyLoginBean.class, new OnNetListener() {
                @Override
                public void onSuccess(Basebean basebean) {
                    MyLoginBean loginBean = (MyLoginBean) basebean;
                    if (loginBean.getCode().equals("200")) {
                        Toast.makeText(MyLogin.this, "登录成功", Toast.LENGTH_SHORT).show();
//                        initLogindName(name);
//                        MyPerson myPerson = new MyPerson();
//                        myPerson.initTextView(name);
                        getLoginName.getLogin(name);
                        myApp.setShared(name);
                        finish();
                    } else {
                        Toast.makeText(MyLogin.this, "" + loginBean.getDatas().getError(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(MyLogin.this, "输入框不能为空", Toast.LENGTH_SHORT).show();
        }

    }

    private void initLogindName(String name) {
        getLoginName.getLogin(name);
    }




}
