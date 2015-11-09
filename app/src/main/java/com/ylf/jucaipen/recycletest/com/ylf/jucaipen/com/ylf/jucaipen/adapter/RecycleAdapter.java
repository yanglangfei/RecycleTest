package com.ylf.jucaipen.recycletest.com.ylf.jucaipen.com.ylf.jucaipen.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylf.jucaipen.recycletest.R;

/**
 * Created by Administrator on 2015/11/9.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  String str[]={"张三","李四","王五","赵六","王八","大脸","畜生","小明","小红","张亮"};
    private TextView tv;

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return  2;
        }else{
            return 5;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==5){
            TextView tv=new TextView(parent.getContext());
            ItemHolder itemHolder=new ItemHolder(tv);
            return  itemHolder;
        }else {
            ImageView iv=new ImageView(parent.getContext());
            HeadHolder headHolder=new HeadHolder(iv);
            return  headHolder;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  ItemHolder){
            ((ItemHolder) holder).tv.setText(str[position]);
        }else if(holder instanceof  HeadHolder){
            ((HeadHolder) holder).iv.setImageResource(R.mipmap.ic_launcher);
        }
    }


    @Override
    public int getItemCount() {
        return str.length;
    }

    class  ItemHolder extends  RecyclerView.ViewHolder{
        TextView tv;

        public ItemHolder(TextView itemView) {
            super(itemView);
            this.tv=itemView;
        }
    }

    class  HeadHolder extends  RecyclerView.ViewHolder{
        ImageView iv;

        public HeadHolder(ImageView itemView) {
            super(itemView);
            this.iv=itemView;
        }
    }
}
