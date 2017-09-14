package bwie.com.manshopping.MyView;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import bwie.com.manshopping.MyBean.MyDetailBean;
import bwie.com.manshopping.R;

/**
 * Created by 黑白 on 2017/9/11.
 */

public class MyPop extends PopupWindow {

    private Context mContext;

    private View view;
    private int count = 1;

    private MyDetailBean.DatasBean.GoodsInfoBean goods_info;

//    private Button btn_take_photo, btn_pick_photo, btn_cancel;


    public MyPop(final Context mContext, MyDetailBean.DatasBean.GoodsInfoBean goods_info) {
        this.goods_info = goods_info;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.addshoppingpop_window, null);

        final TextView addShoppingCoun = view.findViewById(R.id.addShoppingCoun);
        TextView addShoppingadd = view.findViewById(R.id.addShoppingadd);
        ImageView imageView = view.findViewById(R.id.addShoppingImage);
        TextView addShoppingminus = view.findViewById(R.id.addShoppingminus);
        TextView addShoppingPrice = view.findViewById(R.id.addShoppingPrice);
        TextView addShoppingTitle = view.findViewById(R.id.addShoppingTitle);
//        Glide.with(mContext).load(goods_info.get)
        addShoppingPrice.setText(goods_info.getGoods_price());
        addShoppingTitle.setText(goods_info.getGoods_name());
        addShoppingCoun.setText(count + "");
        addShoppingadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                addShoppingCoun.setText(count + "");
            }
        });
        addShoppingminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count <=1) {
                    count = 1;
                    Toast.makeText(mContext, "不能再瘦了", Toast.LENGTH_SHORT).show();
                    return;
                }
                count--;
                addShoppingCoun.setText(count + "");
            }
        });



/*
        // 设置按钮监听
        btn_pick_photo.setOnClickListener(itemsOnClick);
        btn_take_photo.setOnClickListener(itemsOnClick);*/

        // 设置外部可点击
        this.setOutsideTouchable(true);
        initContext();
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
/*        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });*/


    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);

    }

    private void initContext() {


    }


}
