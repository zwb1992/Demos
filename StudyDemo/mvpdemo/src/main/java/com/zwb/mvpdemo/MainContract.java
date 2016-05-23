package com.zwb.mvpdemo;

import Bean.User;
import presenter.BasePresenter;
import view.BaseView;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/23
 ***************************************/
public interface MainContract {
    interface View extends BaseView<Presenter> {
        String getName();

        String getPwd();

        void showPb();

        void disMissPb();

        void clearName();

        void clearPwd();

        void showSuccess(User user);

        void showFailed();
    }

    interface Presenter extends BasePresenter {
        void login();

        void clear();
    }

}
