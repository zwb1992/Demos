package com.example.zwb.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/***************************************
 * Author zhouweibin
 * Description . recycleview适配器
 * Date:2016/5/3
 ***************************************/
public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private List<String> mList;
    private OnItemClickLitener mOnItemClickLitener;
    public MyAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("info", "=======onCreateViewHolder=========");
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv.setText(mList.get(position));
        if(mOnItemClickLitener!=null){
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(v,position);
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

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener onItemClickLitener){
        mOnItemClickLitener = onItemClickLitener;
    }

}
