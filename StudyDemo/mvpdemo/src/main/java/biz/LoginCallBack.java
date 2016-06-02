package biz;

import com.zwb.mvpdemo2.Bean.User;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/23
 ***************************************/
public interface LoginCallBack {
    void loginSuccess(User user);

    void loginFailed();
}
