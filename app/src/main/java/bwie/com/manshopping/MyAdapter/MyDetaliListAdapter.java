package bwie.com.manshopping.MyAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.manshopping.MyBean.MyDetailBean;
import bwie.com.manshopping.R;

/**
 * Created by 黑白 on 2017/9/8.
 */

public class MyDetaliListAdapter extends BaseAdapter {
    private List<MyDetailBean.DatasBean.GoodsCommendListBean> goods_commend_list;
    private Context context;

    public MyDetaliListAdapter(List<MyDetailBean.DatasBean.GoodsCommendListBean> goods_commend_list, Context context) {
        this.goods_commend_list = goods_commend_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return goods_commend_list.size();
    }

    @Override
    public MyDetailBean.DatasBean.GoodsCommendListBean getItem(int i) {
        return goods_commend_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.detalist, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.detaImage);
            viewHolder.title = view.findViewById(R.id.detatitle);
            viewHolder.price = view.findViewById(R.id.detaprice);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.price.setText(goods_commend_list.get(i).getGoods_promotion_price());
        viewHolder.title.setText(goods_commend_list.get(i).getGoods_name());
        Glide.with(context).load(goods_commend_list.get(i).getGoods_image_url()).into(viewHolder.imageView);
        return view;
    }

    class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView price;
    }
}
