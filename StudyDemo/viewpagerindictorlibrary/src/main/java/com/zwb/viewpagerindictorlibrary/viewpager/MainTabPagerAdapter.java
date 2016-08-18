package com.zwb.viewpagerindictorlibrary.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;


import java.util.List;

public class MainTabPagerAdapter extends SlidingTabPagerAdapter {

	@Override
	public int getCacheCount() {
		return mFragments.size();
	}

	private List<Fragment> mFragments;
	private List<String> mTitles;

	public MainTabPagerAdapter(FragmentManager fm, Context context, ViewPager pager
			,List<Fragment> fragments,List<String> titles) {
		super(fm, fragments, context.getApplicationContext(), pager);
        mTitles = titles;
        mFragments = fragments;
	}

	@Override
	public int getCount() {
		return mFragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTitles.get(position);
	}

}