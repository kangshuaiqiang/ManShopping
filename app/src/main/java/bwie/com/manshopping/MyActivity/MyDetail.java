package bwie.com.manshopping.MyActivity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import bwie.com.manshopping.MyAdapter.MyDetaliListAdapter;
import bwie.com.manshopping.MyBean.Basebean;
import bwie.com.manshopping.MyBean.MyDetailBean;
import bwie.com.manshopping.MyUtils.Api;
import bwie.com.manshopping.MyUtils.OnNetListener;
import bwie.com.manshopping.MyView.MyPop;
import bwie.com.manshopping.R;

public class MyDetail extends BaseActivity_2 {

    private ViewPager pager;
    private TextView goods_name;
    private TextView goods_jingle;
    private TextView goods_price;
    private TextView goods_salenum;
    private ListView listView;
    private LinearLayout linearLayout;
    private MyDetailBean.DatasBean.GoodsInfoBean goods_info;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);
        pager = (ViewPager) findViewById(R.id.viewPager);
        goods_name = (TextView) findViewById(R.id.goods_name);
        goods_jingle = (TextView) findViewById(R.id.goods_jingle);
        goods_price = (TextView) findViewById(R.id.goods_price);
        goods_salenum = (TextView) findViewById(R.id.goods_salenum);
        listView = (ListView) findViewById(R.id.detaListView);
        linearLayout = (LinearLayout) findViewById(R.id.addShoppingCard);
        initDetail();

    }

    private void initDetail() {
        String goods_id = getIntent().getStringExtra("goods_id");
        httpUtil.get(MyDetail.this, Api.DETAIL + goods_id, MyDetailBean.class, new OnNetListener() {
            @Override
            public void onSuccess(Basebean basebean) {
                MyDetailBean myDetailBean = (MyDetailBean) basebean;
                goods_info = myDetailBean.getDatas().getGoods_info();
                goods_name.setText(goods_info.getGoods_name());
                goods_jingle.setText(goods_info.getGoods_jingle());
                goods_price.setText(goods_info.getGoods_price());
                goods_salenum.setText(goods_info.getGoods_salenum());

                List<MyDetailBean.DatasBean.GoodsCommendListBean> goods_commend_list = myDetailBean.getDatas().getGoods_commend_list();

                listView.setAdapter(new MyDetaliListAdapter(goods_commend_list, MyDetail.this));
                initLin(myDetailBean.getDatas().getGoods_info());

            }
        });
    }

    private void initLin(MyDetailBean.DatasBean.GoodsInfoBean goods_info) {
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPopWindow(view);
            }
        });
    }

    private void initPopWindow(View view) {
        MyPop takePhotoPopWin = new MyPop(this, goods_info);
//        设置Popupwindow显示位置（从底部弹出）
        takePhotoPopWin.showAtLocation(findViewById(R.id.addShoppingCard), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }
}
