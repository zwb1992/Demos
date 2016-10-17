package com.iwanna.learn.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.iwanna.learn.R;
import com.iwanna.learn.model.Likes;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.viewmodel.LikesVM;
import com.zwb.zwbframe.adapter.CommonAdapter;
import com.zwb.zwbframe.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LikesActivity extends BaseActivity<LikesActivity, LikesVM> {

    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.no_data)
    LinearLayout noData;
    private List<Likes> likesList = new ArrayList<>();
    private CommonAdapter<Likes> adapter = null;

    @Override
    public Class<LikesVM> getVMClass() {
        return LikesVM.class;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_likes;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.likes);
        initAdater();
        showDialog();
        getViewModel().getLikes();
    }

    private void initAdater() {
        listView.setEmptyView(noData);
        adapter = new CommonAdapter<Likes>(this,R.layout.item_likes,getLayoutInflater(),likesList) {
            @Override
            public void convert(ViewHolder holder, Likes likes) {
                holder.setText(R.id.tv_name,likes.getTypeName());
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("like",likesList.get(i).getTypeName());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    public void setData(List<Likes> likes) {
        if (likes == null) {
            return;
        }
        likesList.clear();
        likesList.addAll(likes);
        adapter.notifyDataSetChanged();
    }


    @OnClick(R.id.img_back)
    public void onClick() {
        onBackPressed();
    }
}
