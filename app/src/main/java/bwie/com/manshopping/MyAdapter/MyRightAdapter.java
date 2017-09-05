package bwie.com.manshopping.MyAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bwie.com.manshopping.MyBean.Two_1;
import bwie.com.manshopping.MyBean.Two_2;
import bwie.com.manshopping.R;

/**
 * Created by 黑白 on 2017/9/5.
 */

public class MyRightAdapter extends BaseExpandableListAdapter {
    private List<Two_1.DatasBean.ClassListBean> group;
    private ArrayList<List<Two_2.DatasBean.ClassListBean>> child;
    private Context context;

    public MyRightAdapter(List<Two_1.DatasBean.ClassListBean> group, ArrayList<List<Two_2.DatasBean.ClassListBean>> child, Context context) {
        this.group = group;
        this.child = child;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Two_1.DatasBean.ClassListBean getGroup(int i) {
        return group.get(i);
    }

    @Override
    public Two_2.DatasBean.ClassListBean getChild(int i, int i1) {
        return child.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.group, null);
        TextView textView = v.findViewById(R.id.groupTextView);
        textView.setText(group.get(i).getGc_name());
        return v;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.child, null);
        GridView gridView = v.findViewById(R.id.child_gridView);
        List<Two_2.DatasBean.ClassListBean> classListBeen = child.get(i);

//        child.get(i).get(i1);

        gridView.setAdapter(new MyGridAdapter(context, classListBeen));
        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


}
