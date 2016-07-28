package com.example.zwb.databindingdemo;

import android.databinding.ObservableField;
import android.view.View;

/***************************************
 * Author zhouweibin
 * Description .用这种方式不用继承BaseObservable
 * Date:2016/5/31
 ***************************************/
public class ViewModel {
    public final ObservableField<User> user = new ObservableField<>();

    private int i = 1;

    public View.OnClickListener bindData() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                user.get().setName("数据第" + i + "次绑定");//这种方式无效
                user.set(new User("数据第" + i + "次绑定",i));
                i++;

            }
        };
    }
}
