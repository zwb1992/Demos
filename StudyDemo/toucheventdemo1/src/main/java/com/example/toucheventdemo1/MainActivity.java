package com.example.toucheventdemo1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * ScrollerView 与listView 的滑动冲突
 */
public class MainActivity extends Activity {


    @InjectView(R.id.listView)
    ListView listView;
    @InjectView(R.id.tv)
    TextView tv;

    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        for (int i = 0; i < 100; i++) {
            list.add("test----" + i);
        }
        MyAdapter adapter = new MyAdapter(list);
        listView.setAdapter(adapter);
//        tv = new TextView(this);
//        tv.setText("sadfssssssssssssssss");
//        listView.addHeaderView(tv);
        listView.setEmptyView(tv);
//        setListViewHeightBasedOnChildren(listView);
    }

    public class MyAdapter extends BaseAdapter {
        List<String> list;

        MyAdapter(List<String> list) {
            this.list = list;
            Log.i("info", list.size() + "");
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView;
            if (view == null) {
                Log.i("info", "getview---=====" + i);
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item, viewGroup, false);
                textView = (TextView) view.findViewById(R.id.text);
            } else {
                textView = (TextView) view.findViewById(R.id.text);
            }
            textView.setText(list.get(i));
            return view;
        }
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {

            return;

        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {

            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0, 0);

            totalHeight += listItem.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight

                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        // params.height += 5;// if without this statement,the listview will be

        // a

        // little short

        // listView.getDividerHeight()获取子项间分隔符占用的高度

        // params.height最后得到整个ListView完整显示需要的高度

        listView.setLayoutParams(params);

    }
}
