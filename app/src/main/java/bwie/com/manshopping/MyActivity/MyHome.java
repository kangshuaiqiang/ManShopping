package bwie.com.manshopping.MyActivity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.ArrayList;

import bwie.com.manshopping.Myfragment.MyClassify;
import bwie.com.manshopping.Myfragment.MyHomePager;
import bwie.com.manshopping.Myfragment.MyPerson;
import bwie.com.manshopping.Myfragment.MyShoppingCart;
import bwie.com.manshopping.R;

public class MyHome extends AppCompatActivity {

    private ArrayList<Fragment> frag = new ArrayList<>();
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        initFrag();
    }

    private void initFrag() {
        frag.add(new MyHomePager());
        frag.add(new MyClassify());
        frag.add(new MyShoppingCart());
        frag.add(new MyPerson());
        getSupportFragmentManager().beginTransaction().replace(R.id.frageLayout, frag.get(0)).commit();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.shouye:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frageLayout, frag.get(0)).commit();
                        break;
                    case R.id.fenlei:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frageLayout, frag.get(1)).commit();
                        break;
                    case R.id.gouwuche:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frageLayout, frag.get(2)).commit();
                        break;
                    case R.id.geren:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frageLayout, frag.get(3)).commit();
                        break;
                }
            }
        });

    }
}
