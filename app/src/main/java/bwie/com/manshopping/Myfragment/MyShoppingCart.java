package bwie.com.manshopping.Myfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bwie.com.manshopping.GreenDao.DBManager;
import bwie.com.manshopping.GreenDao.GreenDao_Count.CountManager;
import bwie.com.manshopping.GreenDao.GreenDao_Count.Count_Price;
import bwie.com.manshopping.GreenDao.Shooping;
import bwie.com.manshopping.MyActivity.BaseActivity;
import bwie.com.manshopping.MyAdapter.MyShoopingCard_Adapter;
import bwie.com.manshopping.R;

/**
 * Created by 黑白 on 2017/8/31.
 */

public class MyShoppingCart extends BaseActivity implements MyShoopingCard_Adapter.GetPriceFalse, MyShoopingCard_Adapter.GetPriceTrue, MyShoopingCard_Adapter.GetAdd, MyShoopingCard_Adapter.GetMinus, MyShoopingCard_Adapter.SetIsCheck {


    private View view;
    private ListView mShoppingCardlistView;
    /**
     * 全选
     */
    private CheckBox mQuanxuan;
    /**
     * 0
     */
    private TextView mCount;
    /**
     * 00.00
     */
    private TextView mCountPrice;
    private float price;
    private int count;
    private DBManager dbManager;
    private MyShoopingCard_Adapter myShoopingCard_adapter;
    private List<Shooping> shoopings = new ArrayList<>();
    private CountManager countManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.shoppingcard, null);
        dbManager = myApp.getDbManager();
        countManager = myApp.getCountManager();
        shoopings = dbManager.queryUserList();
        initView(view);

        return view;
    }


    private void initView(View view) {
        mShoppingCardlistView = (ListView) view.findViewById(R.id.shoppingCardlistView);
        mQuanxuan = (CheckBox) view.findViewById(R.id.quanxuan);

        mCount = (TextView) view.findViewById(R.id.count);
        mCountPrice = (TextView) view.findViewById(R.id.countPrice);
//        shoopings.clear();

        myShoopingCard_adapter = new MyShoopingCard_Adapter(shoopings, getActivity(), dbManager);
        mShoppingCardlistView.setAdapter(myShoopingCard_adapter);
        myShoopingCard_adapter.initPriceFalse(MyShoppingCart.this);
        myShoopingCard_adapter.initPriceTrue(MyShoppingCart.this);
        myShoopingCard_adapter.initAdd(MyShoppingCart.this);
        myShoopingCard_adapter.initMinus(MyShoppingCart.this);
        myShoopingCard_adapter.initCheck(MyShoppingCart.this);

        mQuanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initShoopingAdapter();
            }
        });

    }

    private void initShoopingAdapter() {
        if (mQuanxuan.isChecked()) {
            count = 0;
            price = 0;
            for (int i = 0; i < shoopings.size(); i++) {
                String price1 = shoopings.get(i).getPrice();
                count += shoopings.get(i).getCount();
                price += shoopings.get(i).getCount() * Float.parseFloat(price1);
                shoopings.get(i).setIsNet(mQuanxuan.isChecked());
            }
            initcount_price();

        } else {
            count = 0;
            price = 0;
            for (int i = 0; i < shoopings.size(); i++) {
                shoopings.get(i).setIsNet(mQuanxuan.isChecked());
            }
            initcount_price();

        }

        myShoopingCard_adapter.notifyDataSetChanged();

    }

    @Override
    public void getpriceTrue(float price, int count) {
        Toast.makeText(getActivity(), "添加" + price + "===" + count, Toast.LENGTH_SHORT).show();
        Log.d("zzz", "添加" + price + "===" + count);
        this.count += count;
        this.price += price;
        initcount_price();
    }

    @Override
    public void getpriceFalse(float price, int count) {
        Log.d("zzz", "减去" + price + "===" + count);


        Toast.makeText(getActivity(), "减去" + price + "===" + count, Toast.LENGTH_SHORT).show();
        this.count -= count;
        this.price -= price;
        initcount_price();
    }

    @Override
    public void getadd(float add) {
        this.count += 1;
        this.price += add;
        initcount_price();
        Log.d("zzz", add + "");
    }

    private void initcount_price() {
        mCount.setText(this.count + "");
        mCountPrice.setText(this.price + "");
    }

    @Override
    public void getminus(float minus) {
        this.count -= 1;
        this.price -= minus;
        initcount_price();
        Log.d("zzz", minus + "");
    }

    @Override
    public void setCheck(boolean isCheck) {
        mQuanxuan.setChecked(isCheck);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCheckBox_start();
    }

    @Override
    public void onStart() {
        super.onStart();
        initCount_Price_start();

    }

    private void initCheckBox_start() {

        if (shoopings.size() > 0) {
            for (int i = 0; i < shoopings.size(); i++) {
                Shooping shooping = shoopings.get(i);
                if (!shooping.getIsNet()) {
                    mQuanxuan.setChecked(false);
                    return;
                }
            }
            mQuanxuan.setChecked(true);
        }

    }

    private void initCount_Price_start() {
        List<Count_Price> count_prices = countManager.queryUserList();
        if (count_prices.size() == 0) {
            return;
        }
        Count_Price count_price = count_prices.get(0);
        count = count_price.getCount();
        price = count_price.getPrice();
        initcount_price();
    }

    @Override
    public void onPause() {
        super.onPause();
        List<Count_Price> count_prices = countManager.queryUserList();
        if (count_prices.size() == 0) {
            Count_Price count_price = new Count_Price();
            count_price.setPrice(price);
            count_price.setCount(count);
            countManager.insertUser(count_price);
        } else {
            Count_Price count_price = count_prices.get(0);
            count_price.setPrice(price);
            count_price.setCount(count);
            countManager.updateUser(count_price);
        }

    }
}
