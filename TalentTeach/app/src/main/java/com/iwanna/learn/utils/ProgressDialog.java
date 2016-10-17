package com.iwanna.learn.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iwanna.learn.R;

/**
 * Created by zwb
 * Description
 * Date 16/8/19.
 */
public class ProgressDialog {

    private Context mContext;
    private Dialog dialog;
    private ImageView imageView;
    private Animation animation;

    public ProgressDialog(Context context){
        mContext = context;
        init();
    }

    private void init(){
        dialog = new Dialog(mContext, R.style.translucentDialogStyle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_progress,null,false);
        imageView = (ImageView)view.findViewById(R.id.img_pb);

        int w = (int)(DensityUtils.getW(mContext)/1.8);
        dialog.setContentView(view,new LinearLayout.LayoutParams(w, ViewGroup.LayoutParams.WRAP_CONTENT));
        dialog.setCanceledOnTouchOutside(false);
    }

    public void show(){
        dialog.show();
        setIvAnimation();
    }

    public void dismiss(){
        dialog.dismiss();
        cancleAnimation();
    }

    public void setIvAnimation(){
        if(imageView!=null){
            animation = getAnimation();
            imageView.startAnimation(animation);
        }
    }
    public void cancleAnimation(){
        if(animation!=null){
            animation.cancel();
        }
    }

    private Animation getAnimation() {
        Animation animation=new RotateAnimation(0,359,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(1000);
        animation.setInterpolator(new LinearInterpolator());//不停顿
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(-1);
        return animation;
    }
}
