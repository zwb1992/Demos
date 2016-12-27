package com.example.glidedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


/***************************************
 * Author zhouweibin
 * Description . recycleview适配器
 * Date:2016/5/3
 ***************************************/
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context mContext;
    private List<String> mList;
    private OnItemClickLitener mOnItemClickLitener;
    private List<Integer> mHeights;

    public MyAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mList.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("info", "=======onCreateViewHolder=========");
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.e("info","====position======"+position);
        ViewGroup.LayoutParams lp = holder.imageView.getLayoutParams();
        lp.height = mHeights.get(position);
        lp.width = (int)(mContext.getResources().getDisplayMetrics().widthPixels/2.0f);
        Log.e("info","====height====="+lp.height);
        Log.e("info","====width====="+lp.width);
        holder.imageView.setLayoutParams(lp);
        Glide.with(mContext)
                .load(mList.get(position))
                .placeholder(R.mipmap.ic_launcher)
                .override(lp.width,lp.height)
                .centerCrop()
                .into(holder.imageView);
        if (mOnItemClickLitener != null) {
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener) {
        mOnItemClickLitener = onItemClickLitener;
    }

}
