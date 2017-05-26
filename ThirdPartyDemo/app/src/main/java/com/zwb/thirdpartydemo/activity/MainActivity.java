package com.zwb.thirdpartydemo.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.zwb.thirdpartydemo.R;
import com.zwb.thirdpartydemo.base.BaseFragment;
import com.zwb.thirdpartydemo.fragment.CommonFragment;
import com.zwb.thirdpartydemo.fragment.CustomeFragment;
import com.zwb.thirdpartydemo.fragment.OtherFragment;
import com.zwb.thirdpartydemo.fragment.ThirdPartyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RadioButton rb_common,rb_thirdParty,rb_custom,rb_orther;
    private List<BaseFragment> baseFragments ;
    private int current = 0;
    private BaseFragment currentFragmnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rb_common:
                current = 0;
                break;
            case R.id.rb_thirdParty:
                current = 1;
                break;
            case R.id.rb_custom:
                current = 2;
                break;
            case R.id.rb_orther:
                current = 3;
                break;
        }
        showFragment(baseFragments.get(current));
    }

    private void initView(){
        rb_common = (RadioButton)findViewById(R.id.rb_common);
        rb_common.setOnClickListener(this);
        rb_thirdParty = (RadioButton)findViewById(R.id.rb_thirdParty);
        rb_thirdParty.setOnClickListener(this);
        rb_custom = (RadioButton)findViewById(R.id.rb_custom);
        rb_custom.setOnClickListener(this);
        rb_orther = (RadioButton)findViewById(R.id.rb_orther);
        rb_orther.setOnClickListener(this);
    }

    private void initFragment(){
        baseFragments = new ArrayList<>();
        baseFragments.add(new CommonFragment());
//        baseFragments.add(new ThirdPartyFragment());
//        baseFragments.add(new CustomeFragment());
//        baseFragments.add(new OtherFragment());
        current = 0;
        currentFragmnt = baseFragments.get(current);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_fragment,currentFragmnt).commit();
    }

    private void showFragment(BaseFragment to){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(!to.isAdded()){
            transaction.hide(currentFragmnt);
            transaction.add(R.id.fl_fragment,to);
            transaction.commit();
        }else {
            transaction.hide(currentFragmnt);
            transaction.show(to);
            transaction.commit();
        }
        currentFragmnt = to;
    }


}
