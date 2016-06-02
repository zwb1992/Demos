package com.zwb.mvpdemo2.presenter;

import android.widget.Toast;

import com.zwb.mvpdemo2.base.AbstractBasePresenter;
import com.zwb.mvpdemo2.view.activity.MainActivity;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/2
 ***************************************/
public class MainPresenter extends AbstractBasePresenter<MainActivity> {
    public void show() {
        Toast.makeText(getView(), "hahahhaa", Toast.LENGTH_SHORT).show();
    }
}
