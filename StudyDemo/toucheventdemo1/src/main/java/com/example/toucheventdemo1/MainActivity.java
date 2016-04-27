package com.example.toucheventdemo1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        for (int i =0;i<100;i++){
            list.add("test----"+i);
        }
        MyAdapter adapter =  new MyAdapter(list);
        listView.setAdapter(adapter);
    }

    public class MyAdapter extends BaseAdapter{
        List<String> list;
        MyAdapter(List<String> list){
            this.list = list;
            Log.i("info",list.size()+"");
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
            if(view == null){
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item,viewGroup,false);
                textView = (TextView)view.findViewById(R.id.text);
            }else{
                textView = (TextView)view.findViewById(R.id.text);
            }
            textView.setText(list.get(i));
            return view;
        }
    }
}
