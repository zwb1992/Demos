package com.example.refreshdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.refreshdemo.refreshlayout.YDNormalRefreshViewHolder;
import com.example.refreshdemo.refreshlayout.YDRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements YDRefreshLayout.RefreshLayoutDelegate{

    private YDRefreshLayout refreshLayout;
    private ListView lv_travel_list;
    private List<String> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (YDRefreshLayout) findViewById(R.id.rl_listview_refresh);
        YDNormalRefreshViewHolder normalRefreshViewHolder = new YDNormalRefreshViewHolder(this, true);
        refreshLayout.setRefreshViewHolder(normalRefreshViewHolder);
//        refreshLayout.setRefreshViewHolder(RefreshHolderUtil.getHolder(this));
        refreshLayout.setDelegate(this);
        refreshLayout.setIsShowLoadingMoreView(true);
        lv_travel_list = (ListView) findViewById(R.id.rv_commonroute);
        adapter = new MyAdapter();
        initData();
        lv_travel_list.setAdapter(adapter);
    }
    private void initData() {
        for (int i = 0; i < 20; i++) {
            list.add(0, "test====" + i);
        }
        adapter.notifyDataSetChanged();
        refreshLayout.endRefreshing();
        refreshLayout.endLoadingMore();
    }

    /**
     * 下拉刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onRefreshLayoutBeginRefreshing(YDRefreshLayout refreshLayout) {
        getData();
    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(YDRefreshLayout refreshLayout) {
        getData();
        return true;
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(MainActivity.this);
            textView.setText(list.get(i));
            return textView;
        }
    }

    private android.os.Handler handler = new android.os.Handler();

    private void getData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }, 1000);
    }
    }
