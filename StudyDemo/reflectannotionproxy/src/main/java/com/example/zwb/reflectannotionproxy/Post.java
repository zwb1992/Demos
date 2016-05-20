package com.example.zwb.reflectannotionproxy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***************************************
 * Author zhouweibin
 * Description .模拟post请求
 * Date:2016/5/20
 ***************************************/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Post {
    String value() default "";
}
