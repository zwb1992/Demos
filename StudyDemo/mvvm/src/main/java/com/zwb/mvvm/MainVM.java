package com.zwb.mvvm;

import android.widget.Toast;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/3
 ***************************************/
public class MainVM extends AbstractViewMode<MainActivity> {
    public void toast(){
        Toast.makeText(getView(),"MVVM框架",Toast.LENGTH_SHORT).show();
    }
}
