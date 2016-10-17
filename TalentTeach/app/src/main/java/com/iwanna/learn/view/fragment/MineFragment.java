package com.iwanna.learn.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iwanna.learn.DataCache;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.model.UserInfo;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.activity.AccountSafeActivity;
import com.iwanna.learn.view.activity.MyCollectionListActivity;
import com.iwanna.learn.view.activity.MyEntryListActivity;
import com.iwanna.learn.view.activity.MyJoinedListActivity;
import com.iwanna.learn.view.activity.MyOpenCourseActivity;
import com.iwanna.learn.view.activity.PersonInfoActivity;
import com.iwanna.learn.view.base.BaseFragment;
import com.iwanna.learn.viewmodel.MineFragmentVM;
import com.zwb.zwbframe.http.HttpRequest;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***************************************
 * Author zhouweibin
 * Description .我的
 * Date:2016/6/13
 ***************************************/
public class MineFragment extends BaseFragment<MineFragment, MineFragmentVM> {

    @Bind(R.id.iv_face)
    ImageView ivFace;
    @Bind(R.id.tv_tel)
    TextView tvTel;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_service_tel)
    TextView tvServiceTel;
    @Bind(R.id.tv_start_teach)
    TextView tvStartTeach;

    @Override
    public int tellMeLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public Class<MineFragmentVM> getVMClass() {
        return MineFragmentVM.class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        showTeach();

        ivFace.setTag(Net.NetInstance.IMG_URL + "/" + DataCache.getInstance().getmUserInfo().getUserImg());
        Net.imageLoader(Net.NetInstance.IMG_URL + "/" + DataCache.getInstance().getmUserInfo().getUserImg(), ivFace,
                DensityUtils.dip2px(getActivity(),68),DensityUtils.dip2px(getActivity(),68),
                R.mipmap.default_face_50, R.mipmap.default_face_50, HttpRequest.ImageShapeType.ROUND);
    }

    @OnClick({R.id.ll_info, R.id.tv_collection, R.id.tv_entry, R.id.tv_join, R.id.tv_account, R.id.tv_service_tel, R.id.tv_start_teach})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start_teach:
                readyGo(MyOpenCourseActivity.class);
                break;
            case R.id.ll_info:
                readyGoForResult(PersonInfoActivity.class,211);
                break;
            case R.id.tv_collection:
                readyGo(MyCollectionListActivity.class);
                break;
            case R.id.tv_entry:
                readyGo(MyEntryListActivity.class);
                break;
            case R.id.tv_join:
                readyGo(MyJoinedListActivity.class);
                break;
            case R.id.tv_account:
                readyGo(AccountSafeActivity.class);
                break;
            case R.id.tv_service_tel:
                Uri uri = Uri.parse("tel:" + "4006655668");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(callIntent);
                break;
        }
    }

    public void showTeach() {
        if(tvStartTeach == null){
            return;
        }
        if (2 == DataCache.getInstance().getUserType()) {
            tvStartTeach.setVisibility(View.GONE);
        } else {
            tvStartTeach.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 211){
            UserInfo mUserInfo = DataCache.getInstance().getmUserInfo();
            if(mUserInfo != null){
                tvTel.setText(mUserInfo.getLoginPhone());
                tvName.setText(mUserInfo.getNickName());
                ivFace.setTag(Net.NetInstance.IMG_URL + "/" + DataCache.getInstance().getmUserInfo().getUserImg());
                Net.imageLoader(Net.NetInstance.IMG_URL + "/" + DataCache.getInstance().getmUserInfo().getUserImg(), ivFace,
                        DensityUtils.dip2px(getActivity(),68),DensityUtils.dip2px(getActivity(),68),
                        R.mipmap.default_face_50, R.mipmap.default_face_50, HttpRequest.ImageShapeType.ROUND);
            }
        }
    }
}
