package com.zwb.thirdpartydemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwb.thirdpartydemo.R;

/**
 * Created by zwb
 * Description
 * Date 17/5/4.
 */

public class MyRecylerViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgContent;
    public TextView tvContent;
    public MyRecylerViewHolder(View itemView) {
        super(itemView);
        imgContent = (ImageView)itemView.findViewById(R.id.img_content);
        tvContent = (TextView)itemView.findViewById(R.id.tv_content);
    }
}
