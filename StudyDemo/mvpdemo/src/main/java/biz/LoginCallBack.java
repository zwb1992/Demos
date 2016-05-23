package biz;

import Bean.User;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/23
 ***************************************/
public interface LoginCallBack {
    void loginSuccess(User user);

    void loginFailed();
}
