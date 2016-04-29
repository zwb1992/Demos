package com.zwb.butterknife;

import android.graphics.Color;
import android.widget.TextView;

import butterknife.ButterKnife;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/4/29
 ***************************************/
public class MyAction implements ButterKnife.Action<TextView> {
    @Override
    public void apply(TextView view, int index) {
        if (index == 0) {
            view.setTextColor(Color.BLUE);
        } else {
            view.setTextColor(Color.RED);
        }
    }
}
