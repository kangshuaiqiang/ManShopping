package bwie.com.manshopping.Myfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import bwie.com.manshopping.MyActivity.BaseActivity;
import bwie.com.manshopping.MyActivity.BaseActivity_2;
import bwie.com.manshopping.MyAdapter.MyShoopListAdapter;
import bwie.com.manshopping.MyBean.Basebean;
import bwie.com.manshopping.MyBean.MyShoopingcardList;
import bwie.com.manshopping.MyUtils.OnNetListener;
import bwie.com.manshopping.R;

/**
 * Created by 黑白 on 2017/8/31.
 */

public class MyShoppingCart extends BaseActivity implements MyShoopListAdapter.GetCountp, MyShoopListAdapter.GetCoun, MyShoopListAdapter.GetMinus, MyShoopListAdapter.GetAdd {
    private ListView mShoppingCardlistView;
    private TextView count_1;
    private TextView countPrice;
    private int count_count;
    private int count_price;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.shoppingcard, null);
        count_1 = view.findViewById(R.id.count);
        countPrice = view.findViewById(R.id.countPrice);
        count_1.setText(count_count + "");
        countPrice.setText(count_price + "");

        initView(view);
        initShoppingCardList();
        return view;
    }

    private void initShoppingCardList() {
        String url = "http://169.254.85.75/mobile/index.php?act=goods&op=goods_list&page=100&gc_id=587";
        httpUtil.get(getActivity(), url, MyShoopingcardList.class, new OnNetListener() {
            @Override
            public void onSuccess(Basebean basebean) {
                MyShoopingcardList list = (MyShoopingcardList) basebean;

                List<MyShoopingcardList.DatasBean.GoodsListBean> goods_list = list.getDatas().getGoods_list();
                MyShoopListAdapter myShoopListAdapter = new MyShoopListAdapter(goods_list, getActivity());
                mShoppingCardlistView.setAdapter(myShoopListAdapter);
                myShoopListAdapter.initGetCoun(MyShoppingCart.this);
                myShoopListAdapter.initGetCountp(MyShoppingCart.this);

            }
        });
    }

    private void initView(View view) {
        mShoppingCardlistView = (ListView) view.findViewById(R.id.shoppingCardlistView);
    }

    @Override
    public void initCountp(int countp) {
        count_price += countp;
        countPrice.setText(count_price + "");
    }

    @Override
    public void initAdd(int addp) {

    }

    @Override
    public void initMinus(int miunsp) {

    }

    @Override
    public void initCmunt(int count) {
        count_count += count;
        count_1.setText(count_count + "");
    }
}
