package com.example.zwb.dynamicproxydemo;

/***************************************
 * Author zhouweibin
 * Description .动态代理接口
 * Date:2016/5/17
 ***************************************/
public interface Subject {
    void prepare();
    Test sayHello(String str);
}
