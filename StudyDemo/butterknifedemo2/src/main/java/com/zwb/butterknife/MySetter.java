package com.zwb.butterknife;

import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/4/29
 ***************************************/
public class MySetter implements ButterKnife.Setter<TextView,List<String>> {

    @Override
    public void set(TextView view, List<String> value, int index) {
        view.setText(value.get(index));
    }
}
