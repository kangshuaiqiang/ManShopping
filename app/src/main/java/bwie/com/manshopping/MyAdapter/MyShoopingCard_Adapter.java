package bwie.com.manshopping.MyAdapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bwie.com.manshopping.GreenDao.DBManager;
import bwie.com.manshopping.GreenDao.Shooping;
import bwie.com.manshopping.Myfragment.MyShoppingCart;
import bwie.com.manshopping.R;

/**
 * Created by 黑白 on 2017/9/14.
 */

public class MyShoopingCard_Adapter extends BaseAdapter {

    private List<Shooping> shoopings;
    private Context context;
    private DBManager dbManager;

    public MyShoopingCard_Adapter(List<Shooping> shoopings, Context context, DBManager dbManager) {
        this.shoopings = shoopings;
        this.context = context;
        this.dbManager = dbManager;
    }

    @Override
    public int getCount() {
        return shoopings.size();
    }

    @Override
    public Shooping getItem(int i) {
        return shoopings.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final MyViewHolder myViewHolder;
        if (view == null) {
            view = View.inflate(context, R.layout.shoopingcarsitem, null);
            myViewHolder = new MyViewHolder();
            myViewHolder.shoopingcardBox = view.findViewById(R.id.shoopingcardBox);
            myViewHolder.shoopingcardCount2 = view.findViewById(R.id.shoopingcardCount2);
            myViewHolder.shoopingcardCountPrice = view.findViewById(R.id.shoopingcardCountPrice);
            myViewHolder.shoopingcardPrice = view.findViewById(R.id.shoopingcardPrice);
            myViewHolder.shoopingcardTitle = view.findViewById(R.id.shoopingcardTitle);
            myViewHolder.shoppingcardMinus = view.findViewById(R.id.shoppingcardMinus);
            myViewHolder.shoppingcardAdd = view.findViewById(R.id.shoppingcardAdd);
            myViewHolder.shoopingcardDelete = view.findViewById(R.id.shoopingcardDelete);
            view.setTag(myViewHolder);

        } else {
            myViewHolder = (MyViewHolder) view.getTag();
        }

        myViewHolder.shoopingcardTitle.setText(shoopings.get(i).getName());
        myViewHolder.shoopingcardPrice.setText(shoopings.get(i).getPrice());
        myViewHolder.shoopingcardCount2.setText(shoopings.get(i).getCount() + "");
        myViewHolder.shoppingcardMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = shoopings.get(i).getCount();
                count--;
                if (count < 1) {
                    Toast.makeText(context, "不能再瘦了", Toast.LENGTH_SHORT).show();
                    return;
                }

                Shooping shooping = shoopings.get(i);
                shooping.setCount(count);
                dbManager.updateUser(shooping);
				
				
                notifyDataSetChanged();
                myViewHolder.shoopingcardCount2.setText(shoopings.get(i).getCount() + "");
                if (shoopings.get(i).getIsNet()) {

                    getMinus.getminus(Float.parseFloat(shoopings.get(i).getPrice()));
                }
            }
        });


        myViewHolder.shoppingcardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = shoopings.get(i).getCount();
                Long id = shoopings.get(i).getId();
                count++;
                Shooping shooping = shoopings.get(i);
                shooping.setCount(count);
                dbManager.updateUser(shooping);

                myViewHolder.shoopingcardCount2.setText(shoopings.get(i).getCount() + "");
//                List<Shooping> shoopings = dbManager.queryUserList(id);
//                Log.d("zzz", shoopings.toString());
                if (shoopings.get(i).getIsNet()) {
                    getAdd.getadd(Float.parseFloat(shoopings.get(i).getPrice()));
                }
                notifyDataSetChanged();
            }
        });

        myViewHolder.shoopingcardBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isNet = shoopings.get(i).getIsNet();
                myViewHolder.shoopingcardBox.setChecked(!shoopings.get(i).getIsNet());
                Shooping shooping = shoopings.get(i);
                shooping.setIsNet(!isNet);
                dbManager.updateUser(shooping);
                initCountPrice(i, isNet);
            }
        });

        myViewHolder.shoopingcardBox.setChecked(shoopings.get(i).getIsNet());

        myViewHolder.shoopingcardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCountPrice(i, shoopings.get(i).getIsNet());
                Shooping shooping = shoopings.get(i);
                dbManager.deleteUser(shooping);
                shoopings.remove(i);
                setIsCheck.setCheck(setInitCheck());
                notifyDataSetChanged();
            }
        });
        return view;
    }

    private void initCountPrice(int i, boolean isNet) {
        Shooping shooping = shoopings.get(i);
        int count = shooping.getCount();
        float price = Float.parseFloat(shooping.getPrice());
        if (isNet) {
            getPriceFalse.getpriceFalse(count * price, count);
            setIsCheck.setCheck(false);
        } else {
            getPriceTrue.getpriceTrue(count * price, count);
            setIsCheck.setCheck(setInitCheck());
        }
    }

    private boolean setInitCheck() {
        for (int i = 0; i < shoopings.size(); i++) {
            Shooping shooping = shoopings.get(i);
            if (!shooping.getIsNet()) {
                return false;
            }
        }
        return true;
    }

    class MyViewHolder {
        CheckBox shoopingcardBox;
        TextView shoopingcardTitle;
        TextView shoopingcardPrice;
        TextView shoopingcardCount2;
        TextView shoopingcardCountPrice;
        TextView shoppingcardMinus;
        TextView shoppingcardAdd;
        Button shoopingcardDelete;
    }


    private GetPriceTrue getPriceTrue;
    private GetAdd getAdd;
    private GetMinus getMinus;
    private GetPriceFalse getPriceFalse;
    private SetIsCheck setIsCheck;

    public void initCheck(SetIsCheck setIsCheck) {
        this.setIsCheck = setIsCheck;
    }

    public void initPriceTrue(GetPriceTrue getPriceTrue) {
        this.getPriceTrue = getPriceTrue;
    }

    public void initPriceFalse(GetPriceFalse getPrivateFalse) {
        this.getPriceFalse = getPrivateFalse;
    }


    public void initAdd(GetAdd getAdd) {
        this.getAdd = getAdd;
    }

    public void initMinus(GetMinus getMinus) {
        this.getMinus = getMinus;
    }

    public interface GetPriceTrue {
        void getpriceTrue(float price, int count);
    }

    public interface GetPriceFalse {
        void getpriceFalse(float price, int count);
    }

    public interface GetAdd {
        void getadd(float add);
    }

    public interface GetMinus {
        void getminus(float minus);
    }

    public interface SetIsCheck {
        void setCheck(boolean isCheck);
    }
}
