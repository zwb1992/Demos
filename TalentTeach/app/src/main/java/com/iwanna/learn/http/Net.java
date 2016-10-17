package com.iwanna.learn.http;

import android.widget.ImageView;

import com.zwb.zwbframe.http.HttpRequest;
import com.zwb.zwbframe.http.net.NetUtil;
import com.zwb.zwbframe.http.net.OnNetEventListener;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class Net {
    public static class NetInstance {
        public static final String IMG_URL = "http://www.5yxue.cn";
        public static NetUtil netUtil = new NetUtil("http://api.5yxue.cn");
        public static NetApi apiIntance = (NetApi) Proxy.newProxyInstance(
                NetApi.class.getClassLoader(),
                new Class[]{NetApi.class},
                netUtil);

        public static void setDefaultParams() {
            Map<String, String> defaultRequestParams = new HashMap<>();
            netUtil.setDefaultParams(defaultRequestParams);
        }

        public static void setTimeStamp(String stamp) {
            netUtil.addDefaultRequestMaps("timestamp", stamp);
        }

        public static void setToken(String token) {

            netUtil.addDefaultRequestMaps("token", token);
        }

        public static void setUserId(long userId) {
            netUtil.addDefaultRequestMaps("uid", userId + "");
        }

        public static void setMobile(String mobile) {
            netUtil.addDefaultRequestMaps("mobile", mobile);
        }

        public static void clearDefaultRequest() {
            setDefaultParams();
        }
    }


    public static NetApi get(OnNetEventListener listener) {
        return get(true, listener);
    }

    public static NetApi get(boolean shouldCache, OnNetEventListener listener) {
        NetInstance.netUtil.setShouldCache(shouldCache);
        NetInstance.netUtil.setListener(listener);
        return NetInstance.apiIntance;
    }

    public static void imageLoader(final String imageurl, final ImageView view,
                                   final int defaultImageResId, final int errorImageResId, final HttpRequest.ImageShapeType type) {
        HttpRequest.requestImage(imageurl,
                view, defaultImageResId, errorImageResId, type);
    }

    /**
     * 等比缩放到一定的大小
     * @param imageurl
     * @param view
     * @param defaultImageResId
     * @param errorImageResId
     * @param type
     */
    public static void imageLoader(final String imageurl, final ImageView view,int w,int h,
                                   final int defaultImageResId, final int errorImageResId, final HttpRequest.ImageShapeType type) {
        HttpRequest.requestImage(imageurl,
                view,w,h, defaultImageResId, errorImageResId, type);
    }
}
