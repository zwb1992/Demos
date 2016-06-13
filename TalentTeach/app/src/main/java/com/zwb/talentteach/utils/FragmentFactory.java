package com.zwb.talentteach.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

import com.zwb.talentteach.R;
import com.zwb.talentteach.view.fragment.ActivityFragment;
import com.zwb.talentteach.view.fragment.FindFragment;
import com.zwb.talentteach.view.fragment.MineFragment;
import com.zwb.talentteach.view.fragment.TalentFragment;
import com.zwb.zwbframe.mvvm.BaseFragment;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class FragmentFactory {
    public static SparseArray<Fragment> fmHashMap = new SparseArray<>();
    private static int mWhichFragment;
    private static int curposition = -1;

    public static void showFragment(int position, FragmentManager fm) {
        FragmentTransaction ft = fm.beginTransaction();
        BaseFragment baseFragment = null;
        //隐藏所有Fragment
        hideFragments(ft);
        //有对象就获取，没对象就创建
        mWhichFragment = position;

//        if (curposition != -1 && curposition != position) {
//            BaseFragment baseFragment1 = (BaseFragment) fmHashMap.get(curposition);
//            baseFragment1.setUserVisibleHint(false);
//        }
        curposition = position;
        if (fmHashMap.get(position) != null) {
            //从缓存中获取对象
            baseFragment = (BaseFragment) fmHashMap.get(position);
            //显示指定Fragment
            ft.show(baseFragment);
        } else {
            switch (position) {
                case 0:  //发现
                    baseFragment = new FindFragment();
                    break;
                case 1://才艺
                    baseFragment = new TalentFragment();
                    break;
                case 2:  //活动
                    baseFragment = new ActivityFragment();
                    break;
                case 3://我的
                    baseFragment = new MineFragment();
                    break;
            }
            //缓存到本地
            fmHashMap.put(position, baseFragment);
            ft.add(R.id.fl_fragment, baseFragment, position + "");
        }
//        baseFragment.setUserVisibleHint(true);
        ft.commitAllowingStateLoss();
    }

    /**
     * 隐藏Fragment
     *
     * @param ft
     */
    public static void hideFragments(FragmentTransaction ft) {
        for (int i = 0;i<4;i++){
            BaseFragment fragment = (BaseFragment) fmHashMap.get(i);
            if (fragment != null) {
                ft.hide(fragment);
            }
        }
    }

    /**
     * 清空当前map集合
     */
    public static void clearFragmentMap() {
        if (null != fmHashMap) {
            fmHashMap.clear();
        }
    }
}
