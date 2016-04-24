package com.example.zwb.studydemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import adapter.CommonAdapter;
import adapter.ViewHolder;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 万能适配器
 */
public class MainActivity extends Activity {

    @Bind(R.id.list_item)
    ListView listItem;

    private List<String> list;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        adapter = new MyAdapter(this,list,R.layout.item_view);
        listItem.setAdapter(adapter);
    }
    private void initData(){
        list = new ArrayList<>();
        for (int i = 0;i <20;i++){
            list.add("test====="+i);
        }
    }

    public static class MyAdapter extends CommonAdapter<String>{
        public MyAdapter(Context context, List<String> ts, int layoutId){
            super(context,ts,layoutId);
        }
        @Override
        public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.text,str);
            holder.setImageResource(R.id.image,R.mipmap.ic_launcher);
        }
    }
}
