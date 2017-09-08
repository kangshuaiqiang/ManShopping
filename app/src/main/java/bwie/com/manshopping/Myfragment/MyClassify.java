package bwie.com.manshopping.Myfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bwie.com.manshopping.MyActivity.BaseActivity;
import bwie.com.manshopping.MyActivity.ShoppingList;
import bwie.com.manshopping.MyAdapter.MyLeftAdapter;
import bwie.com.manshopping.MyAdapter.MyRightAdapter;
import bwie.com.manshopping.MyBean.Basebean;
import bwie.com.manshopping.MyBean.LeftBean;
import bwie.com.manshopping.MyBean.Two_1;
import bwie.com.manshopping.MyBean.Two_2;
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


        return v;
    }


    private void initListView() {
//        http://169.254.85.75/mobile/index.php?act=goods_class
        httpUtil.get(getActivity(), Api.LINK_MOBILE_CLASS, LeftBean.class, new OnNetListener() {

            @Override
            public void onSuccess(Basebean basebean) {
                LeftBean leftBean = (LeftBean) basebean;
                final List<LeftBean.DatasBean.ClassListBean> class_list = leftBean.getDatas().getClass_list();
                initexpandableListView(class_list.get(0).getGc_id());
                listView.setAdapter(new MyLeftAdapter(getActivity(), class_list));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        initexpandableListView(class_list.get(i).getGc_id());
                    }
                });

            }
        });
    }

    private void initexpandableListView(final String gc_id) {
        httpUtil.get(getActivity(), Api.TWO + gc_id, Two_1.class, new OnNetListener() {
            @Override
            public void onSuccess(Basebean basebean) {
                Log.d("zzz", Api.TWO + gc_id + "");
                Two_1 two_1 = (Two_1) basebean;
                final List<Two_1.DatasBean.ClassListBean> class_list = two_1.getDatas().getClass_list();
                final ArrayList<List<Two_2.DatasBean.ClassListBean>> class_list_2 = new ArrayList<>();
                class_list_2.clear();
                for (int i = 0; i < class_list.size(); i++) {
                    final int index = i;
                    httpUtil.get(getActivity(), Api.TWO_2 + class_list.get(i).getGc_id(), Two_2.class, new OnNetListener() {
                        @Override
                        public void onSuccess(Basebean basebean) {
//                            Log.d("zzz", Api.TWO_2 + class_list.get(i).getGc_id() + "");
                            Two_2 two_2 = (Two_2) basebean;
                            List<Two_2.DatasBean.ClassListBean> class_list1 = two_2.getDatas().getClass_list();
                            class_list_2.add(class_list1);

                            if (index == class_list.size() - 1) {
                                MyRightAdapter myRightAdapter = new MyRightAdapter(class_list, class_list_2, getActivity());
                                expandableListView.setAdapter(myRightAdapter);

                                int groupCount = expandableListView.getCount();

                                for (int j = 0; j < groupCount; j++) {

                                    expandableListView.expandGroup(j);

                                }
                                ;
                            }
                        }
                    });


                }

                //
                expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                        Intent intent = new Intent(getActivity(), ShoppingList.class);
                        Two_1.DatasBean.ClassListBean classListBean = class_list.get(i);
                        String gc_id1 = classListBean.getGc_id();
                        intent.putExtra("gc_id",gc_id1);
                        startActivity(intent);
                        return true;
                    }
                });

            }
        });
    }

}
