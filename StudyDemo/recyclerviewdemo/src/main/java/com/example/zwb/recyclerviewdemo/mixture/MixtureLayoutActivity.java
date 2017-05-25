package com.example.zwb.recyclerviewdemo.mixture;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zwb.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 混合布局，listView与gridView共存
 */
public class MixtureLayoutActivity extends AppCompatActivity {

    @Bind(R.id.recycle)
    RecyclerView recycle;
    List<DataModel> models = new ArrayList<>();
    private MixtureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixture_layout);
        ButterKnife.bind(this);
        initData();
        adapter = new MixtureAdapter(models, this);
        recycle.setAdapter(adapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 6);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = recycle.getAdapter().getItemViewType(position);
                if(type == DataModel.TYPE_TWO){//  6/2,即三列
                    return 2;
                }else if(type == DataModel.TYPE_ONE){
                    return 3;// 6/3两列
                }else {// 6/6---即三列合一，一列
                    return 6;
                }
            }
        });
        recycle.setLayoutManager(layoutManager);
        recycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                int spanSize = layoutParams.getSpanSize();
                int spanIndex = layoutParams.getSpanIndex();
                if(spanSize == 3){
                    if(spanIndex == 0){
                        outRect.right = 10;
                    }else {
                        outRect.left = 10;
                    }
                }else if(spanSize == 2){
                    outRect.top = 10;
                    outRect.bottom = 10;
                    if(spanIndex == 0){
                        outRect.right = 10;
                    }else {
                        outRect.left = 10;
                    }
                }else {
                    outRect.top = 20;
                }
            }
        });
    }


    private void initData() {
        for (int i = 0; i < 30; i++) {
            if (i < 5) {
                models.add(new DataModel("content--one:" + i, "title--one:" + i, R.mipmap.ic_launcher, 1));
            } else if (i < 15) {
                models.add(new DataModel("content--two:" + i, "title--two:" + i, R.mipmap.ic_launcher, 2));
            } else {
                models.add(new DataModel("content--three:" + i, "title--three:" + i, R.mipmap.ic_launcher, 3));
            }
        }

    }
}
