package biz;

import android.os.Handler;

import Bean.User;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/23
 ***************************************/
public class UserBiz implements IUserBiz {
    private static Handler handler = new Handler();
    private boolean flag = false;

    @Override
    public void login(final String name, final String pwd, final LoginCallBack callBack) {
        if ("zwb".equals(name) && "123".equals(pwd)) {
            flag = true;
        } else {
            flag = false;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (flag) {
                    callBack.loginSuccess(new User(name, pwd));
                } else {
                    callBack.loginFailed();
                }
            }
        }, 3000);
    }
}
