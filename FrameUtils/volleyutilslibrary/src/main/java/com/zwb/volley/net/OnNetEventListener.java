package com.zwb.volley.net;

/**
 * Created by zwb
 * Description 对结果进行处理
 * Date 16/10/22.
 */
public interface OnNetEventListener {
    /**
     * 成功回调
     *
     * @param netEvent
     */
    void netSuccess(NetEvent netEvent);

    /**
     * 失败回调
     *
     * @param netEvent
     * @return false 由底层去处理，true自己处理事件
     */
    boolean netfailed(NetEvent netEvent);


    /**
     * 登录过期
     */
    void tokenVerifyFailed();
}
