package com.zwb.pulltorefreshlibrary.refreshlayout;

import android.content.Context;

import com.zwb.pulltorefreshlibrary.R;


/**
 *
 */
public class RefreshHolderUtil {
    public static RefreshViewHolder getHolder(Context ctx) {
        MeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new MeiTuanRefreshViewHolder(ctx, true);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        return meiTuanRefreshViewHolder;
    }

    public static RefreshViewHolder getHolder(Context ctx, boolean loadMoreEnable) {
        MeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new MeiTuanRefreshViewHolder(ctx, loadMoreEnable);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);
        return meiTuanRefreshViewHolder;
    }
}
