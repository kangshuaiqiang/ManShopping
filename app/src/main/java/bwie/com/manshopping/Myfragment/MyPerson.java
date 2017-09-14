package bwie.com.manshopping.Myfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import bwie.com.manshopping.MyActivity.BaseActivity;
import bwie.com.manshopping.MyActivity.MyLogin;
import bwie.com.manshopping.R;

/**
 * Created by 黑白 on 2017/8/31.
 */

public class MyPerson extends BaseActivity implements MyLogin.GetLoginName{

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myperson, container, false);

        MyLogin.setLoginName(this);

        textView = view.findViewById(R.id.LoginButton);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (myApp.getShared(textView.getText().toString())) {
                    Toast.makeText(getActivity(), "已经登录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), MyLogin.class);
                    startActivity(intent);
                }

            }
        });
        return view;
    }

    @Override
    public void getLogin(String name) {
        textView.setText(name);
    }
/*
    public void initTextView(String name) {
            textView.setText(name);

    }*/

}
