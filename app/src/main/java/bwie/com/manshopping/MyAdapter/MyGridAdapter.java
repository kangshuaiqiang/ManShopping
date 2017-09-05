package bwie.com.manshopping.MyAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import bwie.com.manshopping.MyBean.Two_2;
import bwie.com.manshopping.R;

/**
 * Created by 黑白 on 2017/9/5.
 */

public class MyGridAdapter extends BaseAdapter {
    private Context context;
    private List<Two_2.DatasBean.ClassListBean> classListBeen;

    public MyGridAdapter(Context context, List<Two_2.DatasBean.ClassListBean> classListBeen) {
        this.context = context;
        this.classListBeen = classListBeen;
    }

    @Override
    public int getCount() {
        return classListBeen.size();
    }

    @Override
    public Two_2.DatasBean.ClassListBean getItem(int i) {
        return classListBeen.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, R.layout.gridview, null);
        }
        TextView textView = view.findViewById(R.id.gridTextView);
        textView.setText(classListBeen.get(i).getGc_name());
        return view;
    }
}
