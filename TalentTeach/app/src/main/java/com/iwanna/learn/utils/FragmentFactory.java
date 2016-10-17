package com.iwanna.learn.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

import com.iwanna.learn.view.base.BaseFragment;
import com.iwanna.learn.view.fragment.ActivityFragment;
import com.iwanna.learn.view.fragment.FindFragment;
import com.iwanna.learn.view.fragment.MineFragment;
import com.iwanna.learn.view.fragment.IndexFragment;
import com.iwanna.learn.R;

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
                case 0:  //首页
                    baseFragment = new IndexFragment();
                    break;
                case 1://发现
                    baseFragment = new FindFragment();
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
            Fragment fragment = fmHashMap.get(i);
            if (fragment != null) {
                ft.hide(fragment);
            }
//            BaseFragment fragment = (BaseFragment) fmHashMap.get(i);
//            if (fragment != null) {
//                ft.hide(fragment);
//            }
        }
    }

    public static <T extends Fragment> T getFragment(int i){
        if(fmHashMap.get(i) == null){
            return null;
        }
        T fragment = (T) fmHashMap.get(i);
        return fragment;
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
