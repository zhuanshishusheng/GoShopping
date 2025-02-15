package app.m15.cn.goshopping.fragment.sort;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.m15.cn.goshopping.R;
import app.m15.cn.goshopping.bean.GoodBean;
import app.m15.cn.goshopping.net.RequestUtil;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.OneViewHolder> implements View.OnClickListener {
    private List<GoodBean.DataBean> list;
    private Context context;

    private OnItemClickListener onItemClickListener=null;
    public MyRecyclerViewAdapter(Context context,List list){
        this.list=list;
        this.context=context;
    }

    @Override
    public OneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false);
        OneViewHolder oneViewHolder=new OneViewHolder(view);
        view.setOnClickListener(this);
        return oneViewHolder;
    }
    @Override
    public void onBindViewHolder(OneViewHolder holder,int position) {
        holder.itemView.setTag(position);
        //网络加载图片
        Glide.with(context).load(RequestUtil.REQUEST_HEAD+list.get(position).getImageUrl1())
                .placeholder(R.mipmap.sort_goods_error)
                .into(holder.imageView);

        holder.mPrice.setText("￥"+list.get(position).getMarketPrice()+".00");
        holder.mlove.setText(list.get(position).getLove()+"");

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if(onItemClickListener!=null){
            onItemClickListener.onItemClick(view,(int) view.getTag());
        }
    }

    class OneViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView mPrice;
        private TextView mlove;
        public OneViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.ivImage);
            mPrice=(TextView)itemView.findViewById(R.id.recyclerview_price);
            mlove=(TextView)itemView.findViewById(R.id.recyclerview_love);
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener=listener;
    }
    public static interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
}
