package com.zwb.viewpagerindictorlibrary.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

public abstract class SlidingTabPagerAdapter extends FragmentPagerAdapter implements
		PagerSlidingTabStrip.OnTabClickListener, PagerSlidingTabStrip.OnTabDoubleTapListener {

//	protected final Fragment[] fragments;
	protected final List<Fragment> mFragments;

	protected final Context mContext;

	private final ViewPager mPager;

	public abstract int getCacheCount();

	private int lastPostion = 0;

	public SlidingTabPagerAdapter(FragmentManager fm, List<Fragment> fragments, Context context, ViewPager pager) {
		super(fm);
        mFragments = fragments;
        mContext = context;
        mPager = pager;
		lastPostion = 0;
	}

	@Override
	public Fragment getItem(int pos) {
		return mFragments.get(pos);
	}

	@Override
	public abstract int getCount();

	@Override
	public abstract CharSequence getPageTitle(int position);


	public void onPageSelected(int position) {
		Fragment fragment = getFragmentByPosition(position);

		// INSTANCE
		if (fragment == null) {
			return;
		}

		onLeave(position);
	}

	private void onLeave(int position) {
		Fragment fragment = getFragmentByPosition(lastPostion);
		lastPostion = position;
		// INSTANCE
		if (fragment == null) {
			return;
		}
	}

	public void onPageScrolled(int position) {
		Fragment fragment = getFragmentByPosition(position);

		// INSTANCE
		if (fragment == null) {
			return;
		}
		onLeave(position);
	}

	private Fragment getFragmentByPosition(int position) {
		// IDX
		if (position < 0 || position >= mFragments.size()) {
			return null;
		}
		return mFragments.get(position);
	}

	@Override
	public void onCurrentTabClicked(int position) {

		Fragment fragment = getFragmentByPosition(position);

		// INSTANCE
		if (fragment == null) {
			return;
		}

	}

	@Override
	public void onCurrentTabDoubleTap(int position) {
		Fragment fragment = getFragmentByPosition(position);

		// INSTANCE
		if (fragment == null) {
			return;
		}

	}
}