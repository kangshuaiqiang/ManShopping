package bwie.com.manshopping.MyAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.manshopping.MyBean.LeftBean;
import bwie.com.manshopping.R;

/**
 * Created by 黑白 on 2017/9/2.
 */

public class MyLeftAdapter extends BaseAdapter {
    private Context context;
    private List<LeftBean.DatasBean.ClassListBean> class_list;

    public MyLeftAdapter(Context context, List<LeftBean.DatasBean.ClassListBean> class_list) {
        this.context = context;
        this.class_list = class_list;
    }

    @Override
    public int getCount() {
        return class_list.size();
    }

    @Override
    public LeftBean.DatasBean.ClassListBean getItem(int i) {
        return class_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.left, null);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.leftImage);
            holder.textView = view.findViewById(R.id.leftText);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.textView.setText(class_list.get(i).getGc_name());
        Glide.with(context).load(class_list.get(i).getImage()).into(holder.imageView);
        return view;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
