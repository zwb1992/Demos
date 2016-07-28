package presenter;

import com.zwb.mvpdemo.MainContract;

import com.zwb.mvpdemo2.Bean.User;
import biz.LoginCallBack;
import biz.UserBiz;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/23
 ***************************************/
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private UserBiz userBiz;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.userBiz = new UserBiz();
    }

    @Override
    public void login() {
        view.showPb();
        userBiz.login(view.getName(), view.getPwd(), new LoginCallBack() {
            @Override
            public void loginSuccess(User user) {
                view.showSuccess(user);
                view.disMissPb();
            }

            @Override
            public void loginFailed() {
                view.showFailed();
                view.disMissPb();
            }
        });
    }

    @Override
    public void clear() {
        view.clearName();
        view.clearPwd();
    }

    @Override
    public void start() {
        view.setPresenter(this);
    }
}
