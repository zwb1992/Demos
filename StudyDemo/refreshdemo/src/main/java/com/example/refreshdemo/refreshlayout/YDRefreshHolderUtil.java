/*
 *
 *   Copyright 2016 YunDi
 *
 *   The code is part of Yunnan, Shenzhen Branch of the internal architecture of YunDi source group
 *
 *   DO NOT DIVULGE
 *
 */

package com.example.refreshdemo.refreshlayout;

import android.content.Context;

import com.example.refreshdemo.R;

/**
 * @author YaoWeihui on 2016/4/26.
 */
public class YDRefreshHolderUtil {
    public static YDRefreshViewHolder getHolder(Context ctx) {
        YDMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new YDMeiTuanRefreshViewHolder(ctx, true);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        return meiTuanRefreshViewHolder;
    }

    public static YDRefreshViewHolder getHolder(Context ctx, boolean loadMoreEnable) {
        YDMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new YDMeiTuanRefreshViewHolder(ctx, loadMoreEnable);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        return meiTuanRefreshViewHolder;
    }
}
