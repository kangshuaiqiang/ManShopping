package bwie.com.manshopping.MyActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bwie.com.manshopping.R;

public class MyDetail extends BaseActivity_2 {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);
        String goods_id = getIntent().getStringExtra("goods_id");
    }
}
