package com.example.zwb.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * recyclerview 的Listview用法
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.recycle)
    RecyclerView recycle;
    private List<String> list;
    private MyAdapter adapter;
    private LinearLayoutManager linearLayoutManager;//类似listview的显示管理器
    private GridLayoutManager gridLayoutManager;//类似grideview的显示管理器
    private StaggeredHomeAdapter staggeredHomeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        adapter = new MyAdapter(this,list);
        adapter.setOnItemClickLitener(new MyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "====" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
//        recycle.setAdapter(adapter);
        staggeredHomeAdapter = new StaggeredHomeAdapter(this,list);
        staggeredHomeAdapter.setOnItemClickLitener(new StaggeredHomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                staggeredHomeAdapter.removeData(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        recycle.setAdapter(staggeredHomeAdapter);
        recycle.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//垂直显示
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//水平显示
//        recycle.setLayoutManager(linearLayoutManager);
//        recycle.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        gridLayoutManager = new GridLayoutManager(this,4);
//        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//        recycle.setLayoutManager(gridLayoutManager);
        recycle.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
//        recycle.addItemDecoration(new DividerGridItemDecoration(this));//设置分割线

    }
    private void initData(){
        list = new ArrayList<>();
        for (int i =0;i<97;i++){
            list.add("test======"+i);
        }
    }

}
