package com.example.zwb.recyclerviewdemo.mixture;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zwb
 * Description 第一个
 * Date 17/5/19.
 */

public abstract class BaseMixtureHolder extends RecyclerView.ViewHolder {

    public BaseMixtureHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindModel(DataModel dataModel);
}
