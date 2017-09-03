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

import java.util.List;

import bwie.com.manshopping.MyActivity.BaseActivity;
import bwie.com.manshopping.MyAdapter.MyLeftAdapter;
import bwie.com.manshopping.MyBean.Basebean;
import bwie.com.manshopping.MyBean.LeftBean;
import bwie.com.manshopping.MyUtils.Api;
import bwie.com.manshopping.MyUtils.HttpUtil;
import bwie.com.manshopping.MyUtils.OnNetListener;
import bwie.com.manshopping.R;

/**
 * Created by 黑白 on 2017/8/31.
 */

public class MyClassify extends BaseActivity {
    private ListView listView;
    private ExpandableListView expandableListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.classify, container, false);
        listView = v.findViewById(R.id.classListView);
        expandableListView = v.findViewById(R.id.expandableListView);
        initListView();
        initexpandableListView();

        return v;
    }

    private void initexpandableListView() {

    }

    private void initListView() {
//        http://169.254.85.75/mobile/index.php?act=goods_class
        httpUtil.get(getActivity(), Api.LINK_MOBILE_CLASS, LeftBean.class, new OnNetListener() {

            @Override
            public void onSuccess(Basebean basebean) {
                LeftBean leftBean = (LeftBean) basebean;
                List<LeftBean.DatasBean.ClassListBean> class_list = leftBean.getDatas().getClass_list();
                listView.setAdapter(new MyLeftAdapter(getActivity(), class_list));
            }
        });
    }
}
