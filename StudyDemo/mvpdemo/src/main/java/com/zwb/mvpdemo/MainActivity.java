package com.zwb.mvpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import Bean.User;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import presenter.MainPresenter;

/**
 * MVP模式
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.pwd)
    EditText pwd;
    @Bind(R.id.bt_login)
    Button btLogin;
    @Bind(R.id.bt_clear)
    Button btClear;
    @Bind(R.id.pb)
    ProgressBar pb;

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this);
        presenter.start();
    }

    @OnClick({R.id.bt_login, R.id.bt_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                presenter.login();
                break;
            case R.id.bt_clear:
                presenter.clear();
                break;
        }
    }

    @Override
    public String getName() {
        return name.getText().toString();
    }

    @Override
    public String getPwd() {
        return pwd.getText().toString();
    }

    @Override
    public void showPb() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void disMissPb() {
        pb.setVisibility(View.GONE);
    }

    @Override
    public void clearName() {
        name.setText("");
    }

    @Override
    public void clearPwd() {
        pwd.setText("");
    }

    @Override
    public void showSuccess(User user) {
        Toast.makeText(this, "登录成功:" + user.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailed() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MainContract.Presenter o) {
        presenter = o;
    }
}
