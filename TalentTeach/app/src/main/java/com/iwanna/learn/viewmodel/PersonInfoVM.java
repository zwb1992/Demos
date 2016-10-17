package com.iwanna.learn.viewmodel;

import android.content.Intent;
import android.util.Log;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.view.activity.ChangeInfoActivity;
import com.iwanna.learn.view.activity.PersonInfoActivity;
import com.zwb.zwbframe.event.NetEvent;
import com.zwb.zwbframe.http.net.NetRequest;
import com.zwb.zwbframe.mvvm.AbstractViewMode;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/14
 ***************************************/
public class PersonInfoVM extends AbstractViewMode<PersonInfoActivity> {
    private NetRequest sexNR = null;
    private NetRequest ageNR = null;
    private NetRequest likeNR = null;
    public void goChange(int type){
        Intent intent = new Intent(getView(), ChangeInfoActivity.class);
        intent.putExtra("type",type);
        getView().startActivity(intent);
    }

    private String mSex;
    public void editSex(String sex){
        mSex = sex;
        sexNR = Net.get(this).editSex(DataCache.getInstance().getLoginPhone(),mSex);
    }
    private String mAge;
    public void editAge(String age){
        mAge = age;
        ageNR = Net.get(this).editAge(DataCache.getInstance().getLoginPhone(),mAge);
    }
    private String mLike;
    public void editLike(String like){
        mLike = like;
        likeNR = Net.get(this).editLike(DataCache.getInstance().getLoginPhone(),mLike);
    }

    @Override
    public void netSuccess(NetEvent netEvent) {
        super.netSuccess(netEvent);
        if(getView() != null){
            getView().dissMissDialog();
        }
        if(netEvent.whatEqual(sexNR)){
            DataCache.getInstance().getmUserInfo().setUserSex(mSex);
        }else if(netEvent.whatEqual(ageNR)){
            DataCache.getInstance().getmUserInfo().setUserAge(mAge);
        }else if(netEvent.whatEqual(likeNR)){
            DataCache.getInstance().getmUserInfo().setUserLike(mLike);
            Log.d("info","--mLike--"+mLike);
        }
        DataCache.getInstance().saveUserToLocal();
        DataCache.getInstance().getUserFromLocal();
    }

    @Override
    public boolean netfailed(NetEvent netEvent) {
        if(getView() != null){
            getView().dissMissDialog();

            if(netEvent.whatEqual(sexNR)){
                getView().editSexFailed();
            }else if(netEvent.whatEqual(ageNR)){
                getView().editAgeFailed();
            }
        }
        return super.netfailed(netEvent);
    }
}
