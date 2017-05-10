package com.zwb.thirdpartydemo.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zwb.thirdpartydemo.R;
import com.zwb.thirdpartydemo.activity.OkHttpActivity;
import com.zwb.thirdpartydemo.adapter.CommonAdapter;
import com.zwb.thirdpartydemo.base.BaseFragment;

/**
 * Created by zwb
 * Description 常用的框架
 * Date 17/4/19.
 */

public class CommonFragment extends BaseFragment {

    private static final String TAG = CommonFragment.class.getName();

    private ListView listView;
    private CommonAdapter adapter;
    @Override
    protected View initView() {
        Log.e(TAG,"==initView==视图被初始化======");
        View view = View.inflate(mContext, R.layout.fragment_common,null);
        listView = (ListView)view.findViewById(R.id.listView);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"==initData==数据被初始化======");
        adapter = new CommonAdapter(mContext,strs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext,strs[i],Toast.LENGTH_SHORT).show();
                if("OkHttp".equals(strs[i])){
                    startActivity(new Intent(mContext, OkHttpActivity.class));
                }
            }
        });
    }

    private static String[] strs = {"OkHttp","Xutils3","Retrofit2","Fresco","Glide","GreenDao","Rxjava","Volley","Json","RecycleView","PullToFresh","Picasso","EventBus"};
}
