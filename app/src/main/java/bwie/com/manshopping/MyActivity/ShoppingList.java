package bwie.com.manshopping.MyActivity;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.List;

import bwie.com.manshopping.MyAdapter.MyShoppingList_Recycle;
import bwie.com.manshopping.MyBean.Basebean;
import bwie.com.manshopping.MyBean.MyShoppingList;
import bwie.com.manshopping.MyUtils.Api;
import bwie.com.manshopping.MyUtils.HttpUtil;
import bwie.com.manshopping.MyUtils.OnNetListener;
import bwie.com.manshopping.R;

public class ShoppingList extends BaseActivity_2 {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initShoppingListView();
    }


    private void initShoppingListView() {
        String gc_id = getIntent().getStringExtra("gc_id");
        httpUtil.get(this, Api.SHOPPINGLIST + gc_id, MyShoppingList.class, new OnNetListener() {
            @Override
            public void onSuccess(Basebean basebean) {
                MyShoppingList myShoppingList = (MyShoppingList) basebean;
                final List<MyShoppingList.DatasBean.GoodsListBean> goods_list = myShoppingList.getDatas().getGoods_list();
                MyShoppingList_Recycle myShoppingList_recycle = new MyShoppingList_Recycle(goods_list, ShoppingList.this);
                recyclerView.setAdapter(myShoppingList_recycle);
                myShoppingList_recycle.setOnItemClickLitener(new MyShoppingList_Recycle.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(ShoppingList.this,MyDetail.class);
                        MyShoppingList.DatasBean.GoodsListBean goodsListBean = goods_list.get(position);
                        String goods_id = goodsListBean.getGoods_id();
                        intent.putExtra("goods_id",goods_id);
                        startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
            }
        });

    }
}
