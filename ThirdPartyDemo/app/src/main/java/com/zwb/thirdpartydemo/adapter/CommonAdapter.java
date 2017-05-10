package com.zwb.thirdpartydemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by zwb
 * Description
 * Date 17/4/20.
 */

public class CommonAdapter extends BaseAdapter {

    private String[] strs;
    private Context mContext;

    public CommonAdapter(Context context,String[] strs) {
        this.mContext = context;
        this.strs = strs;
    }

    @Override
    public Object getItem(int i) {
        return strs[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(mContext);
        textView.setPadding(10,10,10,10);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18);
        textView.setText(strs[i]);
        return textView;
    }

    @Override
    public int getCount() {
        return strs.length;
    }
}
