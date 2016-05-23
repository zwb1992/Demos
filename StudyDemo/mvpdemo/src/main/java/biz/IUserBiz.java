package biz;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/23
 ***************************************/
public interface IUserBiz {
    void login(String name,String pwd,LoginCallBack callBack);
}
