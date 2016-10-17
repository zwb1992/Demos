package com.iwanna.learn.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.iwanna.learn.DataCache;
import com.iwanna.learn.R;
import com.iwanna.learn.http.Net;
import com.iwanna.learn.utils.DensityUtils;
import com.iwanna.learn.view.base.BaseActivity;
import com.iwanna.learn.view.customview.WheelView;
import com.iwanna.learn.viewmodel.EntryVM;
import com.zwb.zwbframe.http.HttpRequest;
import com.zwb.zwbframe.http.RequestInstance;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 报名
 */
public class EntryActivity extends BaseActivity<EntryActivity, EntryVM> implements View.OnClickListener{

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_face)
    ImageView ivFace;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.ll_stu_info)
    LinearLayout llStuInfo;

    private String phone;
    private String name;
    private String content;
    private String img;
    private int CourseId;

    private WheelView wl_sex;
    private PopupWindow mPopupWindow;
    private List<String> sexList = new ArrayList<>();

    private TextView tempSex = null;
    @Override
    public Class<EntryVM> getVMClass() {
        return null;
    }

    @Override
    public int tellMeLayout() {
        return R.layout.activity_entry;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        tvTitle.setText(R.string.entry);
        View view = getLayoutInflater().inflate(R.layout.item_baoming,null,false);
        TextView tvSex = (TextView) view.findViewById(R.id.tv_sex);
        tvSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSexPopupWindow();
                tempSex = (TextView)view;
            }
        });

        llStuInfo.addView(view);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        content = intent.getStringExtra("content");
        img = intent.getStringExtra("img");
        CourseId = intent.getIntExtra("CourseId", -1);

        if (!TextUtils.isEmpty(phone)) {
            tvPhone.setText(phone);
        }
        if (!TextUtils.isEmpty(name)) {
            tvName.setText(name);
        }
        if (!TextUtils.isEmpty(content)) {
            tvContent.setText(content);
        }

        ivFace.setTag(Net.NetInstance.IMG_URL + "/" + img);
        Net.imageLoader(Net.NetInstance.IMG_URL + "/" + img, ivFace, R.mipmap.default_face,
                DensityUtils.dip2px(EntryActivity.this, 68), DensityUtils.dip2px(EntryActivity.this, 68),
                R.mipmap.default_face, HttpRequest.ImageShapeType.ROUND);

    }


    @OnClick({R.id.img_back,  R.id.ll_add, R.id.bt_entry})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.ll_add:
                View addView = getLayoutInflater().inflate(R.layout.item_baoming,null,false);
                TextView tvSex1 = (TextView) addView.findViewById(R.id.tv_sex);
                tvSex1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showSexPopupWindow();
                        tempSex = (TextView)view;
                    }
                });
                llStuInfo.addView(addView);
                break;
            case R.id.bt_entry:
                List<HashMap<String, Object>> list = new ArrayList<>();
                for (int i = 0;i<llStuInfo.getChildCount();i++){
                    View view1 = llStuInfo.getChildAt(i);
                    EditText edName = (EditText)view1.findViewById(R.id.ed_name);
                    EditText edAge = (EditText)view1.findViewById(R.id.ed_age);
                    TextView tvSex = (TextView) view1.findViewById(R.id.tv_sex);
                    String name = edName.getText().toString();
                    String age = edAge.getText().toString();
                    String sex = tvSex.getText().toString();
                    if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(sex)){
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("CourseId", CourseId);
                        map.put("UserID", DataCache.getInstance().getUserID());
                        map.put("StuName", name);
                        map.put("StuSex", sex);
                        map.put("StuAge", age);
                        map.put("LinkPhone", phone);
                        list.add(map);
                    }
                }
                if(list.size() == 0){
                    Toast.makeText(this,R.string.input_stu_info,Toast.LENGTH_SHORT).show();
                    return;
                }
                showDialog();
                String url = "http://api.5yxue.cn/ApplyCourse.aspx";
                try {
                    final JSONArray object = new JSONArray(list);
                    Log.i("info", object.toString());
                    JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST,
                            url, object, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            dissMissDialog();
                            if(response  != null){
                                try {
                                    JSONObject object1 =  response.getJSONObject(0) ;
                                    if(object1.getInt("Code") == 200){
                                        Toast.makeText(EntryActivity.this, R.string.entry_successed, Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(EntryActivity.this, R.string.entry_failed, Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                    Toast.makeText(EntryActivity.this, R.string.entry_failed, Toast.LENGTH_SHORT).show();
                                }
                            }
                            Log.i("info", "-------" + response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dissMissDialog();
                            Log.e("info", error.toString());
                            Toast.makeText(EntryActivity.this, R.string.entry_failed, Toast.LENGTH_SHORT).show();
                        }
                    });
                    RequestInstance.getInstance().addToRequestQueue(request);
                } catch (Exception e) {
                    e.printStackTrace();
                    dissMissDialog();
                }
                break;
            case R.id.btn_cancel:
                mPopupWindow.dismiss();
                break;
            case R.id.btn_sure:
                if(tempSex != null) {
                    tempSex.setText(wl_sex.getSeletedItem());
                }
                mPopupWindow.dismiss();
                break;
        }
    }

    /**
     * 显示人数
     */
    private void showSexPopupWindow() {
        if (mPopupWindow != null) {
            mPopupWindow.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
        } else {
            sexList.add("男");
            sexList.add("女");
            View window = getLayoutInflater().inflate(R.layout.popup_setsex, null);
            RelativeLayout main = (RelativeLayout) window.findViewById(R.id.main);
            RelativeLayout content = (RelativeLayout) window.findViewById(R.id.rl_content);
            wl_sex = (WheelView) window.findViewById(R.id.wl_sex);
            TextView btn_cancel = (TextView) window.findViewById(R.id.btn_cancel);
            TextView btn_sure = (TextView) window.findViewById(R.id.btn_sure);
            mPopupWindow = new PopupWindow(window,
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            wl_sex.setOffset(1);
            wl_sex.setSelectColor(getResources().getColor(R.color.black));
            wl_sex.setItems(sexList);
            wl_sex.setSeletion(0);
            btn_cancel.setOnClickListener(this);
            btn_sure.setOnClickListener(this);
            main.setOnClickListener(this);
            content.setOnClickListener(this);
            mPopupWindow.setFocusable(true);
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
        }
    }
}
