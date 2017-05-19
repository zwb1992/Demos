package com.example.zwb.recyclerviewdemo.mixture;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.zwb.recyclerviewdemo.R;

import java.util.List;

/**
 * Created by zwb
 * Description
 * Date 17/5/19.
 */

public class MixtureAdapter extends RecyclerView.Adapter<BaseMixtureHolder> {
    private List<DataModel> dataModels;
    private Context context;
    private LayoutInflater inflater;

    public MixtureAdapter(List<DataModel> dataModels, Context context) {
        this.dataModels = dataModels;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public BaseMixtureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == DataModel.TYPE_ONE) {
            return new MixtureHolderOne(inflater.inflate(R.layout.item_one, parent, false));
        } else if (viewType == DataModel.TYPE_TWO) {
            return new MixtureHolderTwo(inflater.inflate(R.layout.item_two, parent, false));
        } else {
            return new MixtureHolderThree(inflater.inflate(R.layout.item_three, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(BaseMixtureHolder holder, int position) {
        holder.bindModel(dataModels.get(position));
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataModels.get(position).getType();
    }
}
