package com.zwb.thirdpartydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.zwb.thirdpartydemo.R;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by zwb
 * Description
 * Date 17/5/4.
 */

public class MyRecylerViewGlideAdapter extends RecyclerView.Adapter<MyRecylerViewHolder> {

    private Context context;
    private List<String> datas;

    public MyRecylerViewGlideAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onBindViewHolder(MyRecylerViewHolder holder, int position) {
        holder.tvContent.setText(datas.get(position));
        Glide.with(context)
                .load(datas.get(position))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .bitmapTransform(new CropCircleTransformation(context))
//                .fitCenter()
                .into(holder.imgContent);
    }

    @Override
    public MyRecylerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyRecylerViewHolder holder = new MyRecylerViewHolder(View.inflate(context, R.layout.item_recylerview,null));
        return holder;
    }

    public void addItem(int position , String content){
        Log.e("info",position+"=="+content);
        datas.add(position,content);
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        datas.remove(position);
        notifyItemRemoved(position);
    }
}
