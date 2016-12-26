package com.example.glidedemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by zwb
 * Description
 * Date 2016/12/26.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public MyViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imgView);
    }
}
