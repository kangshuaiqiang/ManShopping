package bwie.com.manshopping.MyActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import bwie.com.manshopping.MyBean.Basebean;
import bwie.com.manshopping.MyBean.MyRegisterBean;
import bwie.com.manshopping.MyUtils.Api;
import bwie.com.manshopping.MyUtils.OnNetListener;
import bwie.com.manshopping.R;

public class MyRegister extends BaseActivity_2 implements View.OnClickListener {

    /**
     * 请输入用户名
     */
    private EditText mRegisterName;
    /**
     * 请输入密码
     */
    private EditText mRegisterPassword;
    /**
     * 请输入密码
     */
    private EditText mRegisterPassword2;
    /**
     * 请输入邮箱
     */
    private EditText mRegisterEmail;
    /**
     * 注册
     */
    private Button mRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_register);
        initView();


    }

    private void initView() {
        mRegisterName = (EditText) findViewById(R.id.register_name);
        mRegisterPassword = (EditText) findViewById(R.id.register_password);
        mRegisterPassword2 = (EditText) findViewById(R.id.register_password_2);
        mRegisterEmail = (EditText) findViewById(R.id.register_email);
        mRegister = (Button) findViewById(R.id.register);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                initregister();
                break;
        }
    }

    private void initregister() {

        //获得输入内容
        String name = mRegisterName.getText().toString();
        String pwd = mRegisterPassword.getText().toString();
        String pwd2 = mRegisterPassword2.getText().toString();
        String email = mRegisterEmail.getText().toString();

        //判断输入框不为空
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(pwd2) && !TextUtils.isEmpty(email)) {
            if (!pwd.equals(pwd2)) {
                return;
            }

            Map<String, String> map = new HashMap<>();
//            map.put("act", "login");
//            map.put("op", "register");
            map.put("username", name);
            map.put("password", pwd);
            map.put("password_confirm", pwd2);
            map.put("email", email);
            map.put("client", "android");

            httpUtil.post(MyRegister.this, Api.REGISTER, map, MyRegisterBean.class, new OnNetListener() {
                @Override
                public void onSuccess(Basebean basebean) {
                    MyRegisterBean myRegisterBean = (MyRegisterBean) basebean;
                    if (myRegisterBean.getCode().equals("200")) {
                        Toast.makeText(MyRegister.this, "成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(MyRegister.this, myRegisterBean.getDatas().getError(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(MyRegister.this, "输入框不能为空~~~~~~", Toast.LENGTH_SHORT).show();
        }
    }

}
