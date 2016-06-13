package com.zwb.talentteach.http;

import android.widget.ImageView;

import com.zwb.zwbframe.http.HttpListener;
import com.zwb.zwbframe.http.HttpRequest;
import com.zwb.zwbframe.http.net.NetUtil;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/13
 ***************************************/
public class Net {
    NetApi api = null;

    public static class NetInstance {
        public static NetUtil netUtil = new NetUtil(NetApi.HTTP_FORTEST);
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

    public static NetApi get(HttpListener listener) {
        return get(false, listener);
    }

    public static NetApi get(boolean shouldCache, HttpListener listener) {
        NetInstance.netUtil.setShouldCache(shouldCache);
        NetInstance.netUtil.setListener(listener);
        return NetInstance.apiIntance;
    }

    public void setHttpListener(HttpListener listener) {
        NetInstance.netUtil.setListener(listener);
    }

    public static void imageLoader(final String imageurl, final ImageView view,
                                   final int defaultImageResId, final int errorImageResId, final HttpRequest.ImageShapeType type) {
        HttpRequest.requestImage(imageurl,
                view, defaultImageResId, errorImageResId, type);
    }
}
