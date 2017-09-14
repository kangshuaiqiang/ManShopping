package bwie.com.manshopping.MyAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

import bwie.com.manshopping.MyBean.MyShoopingcardList;
import bwie.com.manshopping.R;


/**
 * Created by 黑白 on 2017/9/12.
 */

public class MyShoopListAdapter extends BaseAdapter {

    List<MyShoopingcardList.DatasBean.GoodsListBean> goods_list;
    Context context;

    private int count = 1;
    private int countprice = 0;

    public MyShoopListAdapter(List<MyShoopingcardList.DatasBean.GoodsListBean> goods_list, Context context) {
        this.goods_list = goods_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return goods_list.size();
    }

    @Override
    public MyShoopingcardList.DatasBean.GoodsListBean getItem(int i) {
        return goods_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final MyViewholder myViewholder;
        if (view == null) {
            view = View.inflate(context, R.layout.shoopingcarsitem, null);
            myViewholder = new MyViewholder();
            myViewholder.shoopingcardCount = view.findViewById(R.id.shoopingcardCount);
            myViewholder.shoopingcardCount2 = view.findViewById(R.id.shoopingcardCount2);
            myViewholder.shoopingcardCountPrice = view.findViewById(R.id.shoopingcardCountPrice);
            myViewholder.shoopingcardDelete = view.findViewById(R.id.shoopingcardDelete);
            myViewholder.shoopingcardImage = view.findViewById(R.id.shoopingcardImage);
            myViewholder.shoopingcardPrice = view.findViewById(R.id.shoopingcardPrice);
            myViewholder.shoopingcardTitle = view.findViewById(R.id.shoopingcardTitle);
            myViewholder.shoppingcardAdd = view.findViewById(R.id.shoppingcardAdd);
            myViewholder.shoppingcardMinus = view.findViewById(R.id.shoppingcardMinus);
            myViewholder.shoopingcardBox = view.findViewById(R.id.shoopingcardBox);
            view.setTag(myViewholder);
        } else {
            myViewholder = (MyViewholder) view.getTag();
        }
        final MyShoopingcardList.DatasBean.GoodsListBean goodsListBean = goods_list.get(i);
        Glide.with(context).load(goodsListBean.getGoods_image_url()).into(myViewholder.shoopingcardImage);
        myViewholder.shoopingcardTitle.setText(goodsListBean.getGoods_name());
        myViewholder.shoopingcardPrice.setText(goodsListBean.getGoods_price());
        countprice = (int) Double.parseDouble(goodsListBean.getGoods_price());
        myViewholder.shoopingcardCountPrice.setText("共" + count + "件商品，合计" + countprice + "元");
        myViewholder.shoopingcardCount2.setText(count + "");


        myViewholder.shoopingcardBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    goods_list.get(i).setIscheck(b);
                    //返回总价钱
                    getCountp.initCountp(countprice);
                    //获得总价钱
                    getCoun.initCmunt(count);
                } else {
                    goods_list.get(i).setIscheck(b);
                    //返回总价钱
                    getCountp.initCountp(-countprice);
                    //获得总价钱
                    getCoun.initCmunt(-count);
                }
            }
        });
        myViewholder.shoopingcardBox.setChecked(goods_list.get(i).ischeck());

        myViewholder.shoopingcardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods_list.remove(i);
                notifyDataSetChanged();

            }
        });

        myViewholder.shoppingcardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                myViewholder.shoopingcardCount2.setText(count + "");
                countprice = (int) Double.parseDouble(goodsListBean.getGoods_price()) * count;
                myViewholder.shoopingcardCountPrice.setText("共" + count + "件商品，合计" + countprice + "元");
            }
        });

        myViewholder.shoppingcardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                if (count <= 1) {
                    count = 1;
                }
                myViewholder.shoopingcardCount2.setText(count + "");
                countprice = (int) Double.parseDouble(goodsListBean.getGoods_price()) * count;
                myViewholder.shoopingcardCountPrice.setText("共" + count + "件商品，合计" + countprice + "元");

            }
        });

        return view;
    }

    class MyViewholder {
        ImageView shoopingcardImage;
        TextView shoopingcardTitle;
        TextView shoopingcardPrice;
        TextView shoopingcardCount;
        TextView shoopingcardCountPrice;
        Button shoopingcardDelete;
        TextView shoppingcardMinus;
        TextView shoopingcardCount2;
        TextView shoppingcardAdd;
        CheckBox shoopingcardBox;

    }


    //总价钱
    private GetCountp getCountp;
    //获得加的钱数
    private GetAdd getAdd;
    //获得减的钱数
    private GetMinus getMinus;
    //获得数量
    private GetCoun getCoun;

    //获得总价钱
    public void initGetCountp(GetCountp getCountp) {
        this.getCountp = getCountp;
    }


    public void initGetCoun(GetCoun getCoun) {
        this.getCoun = getCoun;
    }

    public interface GetCountp {
        void initCountp(int countp);
    }

    public interface GetAdd {
        void initAdd(int addp);
    }

    public interface GetMinus {
        void initMinus(int miunsp);
    }

    public interface GetCoun {
        void initCmunt(int count);
    }
}
