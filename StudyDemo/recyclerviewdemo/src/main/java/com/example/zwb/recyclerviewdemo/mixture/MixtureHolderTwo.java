package com.example.zwb.recyclerviewdemo.mixture;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zwb.recyclerviewdemo.R;

/**
 * Created by zwb
 * Description
 * Date 17/5/19.
 */

public class MixtureHolderTwo extends BaseMixtureHolder {
    private ImageView img;

    public MixtureHolderTwo(View itemView) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.img);
    }

    @Override
    public void bindModel(DataModel dataModel) {
        img.setImageResource(dataModel.getImage());
    }
}
