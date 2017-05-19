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

public class MixtureHolderThree extends BaseMixtureHolder {
    private TextView tv_title, tv_content;
    private ImageView img;

    public MixtureHolderThree(View itemView) {
        super(itemView);
        tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        img = (ImageView) itemView.findViewById(R.id.img);
    }

    @Override
    public void bindModel(DataModel dataModel) {
        tv_title.setText(dataModel.getTitle());
        tv_content.setText(dataModel.getContent());
        img.setImageResource(dataModel.getImage());
    }
}
