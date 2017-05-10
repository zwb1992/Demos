package com.zwb.thirdpartydemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zwb.thirdpartydemo.R;
import com.zwb.thirdpartydemo.adapter.DividerGridItemDecoration;
import com.zwb.thirdpartydemo.adapter.DividerItemDecoration;
import com.zwb.thirdpartydemo.adapter.MyRecylerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btAdd,btDelete,btList,btGrid,btflow;
    private TextView tvTitle;
    private ImageView back;
    private RecyclerView recyclerView;
    private List<String> datas;
    private MyRecylerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();
        initData();

        //设置adapter
        adapter = new MyRecylerViewAdapter(this,datas);
        recyclerView.setAdapter(adapter);
        //设置LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

    }
    private void initView(){
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("RecyclerView");
        back = (ImageView)findViewById(R.id.img_back);
        back.setOnClickListener(this);
        btAdd = (Button)findViewById(R.id.bt_add);
        btAdd.setOnClickListener(this);
        btDelete = (Button)findViewById(R.id.bt_delete);
        btDelete.setOnClickListener(this);
        btList = (Button)findViewById(R.id.bt_list);
        btList.setOnClickListener(this);
        btGrid = (Button)findViewById(R.id.bt_grid);
        btGrid.setOnClickListener(this);
        btflow = (Button)findViewById(R.id.bt_flow);
        btflow.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void initData(){
        datas = new ArrayList<>();
        for (int i = 0;i<100;i++){
            datas.add("content_"+i);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.bt_add:
                adapter.addItem(0,"New Content");
                break;
            case R.id.bt_delete:
                adapter.removeItem(0);
                break;
            case R.id.bt_list:
                recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
                break;
            case R.id.bt_grid:
                recyclerView.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));
                recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
                break;
            case R.id.bt_flow:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
        }
    }
}
