package bwie.com.manshopping.MyAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import bwie.com.manshopping.MyBean.MyShoppingList;
import bwie.com.manshopping.R;

/**
 * Created by 黑白 on 2017/9/7.
 */

public class MyShoppingList_Recycle extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MyShoppingList.DatasBean.GoodsListBean> goods_list;
    private Context context;

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public MyShoppingList_Recycle(List<MyShoppingList.DatasBean.GoodsListBean> goods_list, Context context) {
        this.goods_list = goods_list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.shoppinglist, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MyViewHolder) {
            final MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.amout.setText("已售：" + goods_list.get(position).getEvaluation_count() + "件");
            viewHolder.title.setText(goods_list.get(position).getGoods_name());
            Glide.with(context).load(goods_list.get(position).getGoods_image_url()).into(viewHolder.imageView);

            if(mOnItemClickLitener!=null){
                viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickLitener.onItemClick(viewHolder.layout,position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return goods_list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        TextView price;
        TextView amout;
        LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ShoppingList_Image);
            title = itemView.findViewById(R.id.ShoppintList_title);
            price = itemView.findViewById(R.id.ShoppingList_price);
            amout = itemView.findViewById(R.id.ShoppingList_Amout);
            layout = itemView.findViewById(R.id.linear);
        }
    }
}
