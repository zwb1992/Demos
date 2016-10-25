package com.zwb.frameutils.http;

import com.zwb.volley.net.NetUtil;
import com.zwb.volley.net.OnNetEventListener;

import java.lang.reflect.Proxy;

/**
 * Created by zwb
 * Description
 * Date 16/10/24.
 */
public class Net {
    public static class NetInstance{
        public static NetUtil netUtil = new NetUtil("http://");
        public static NetApi apiIntance = (NetApi) Proxy.newProxyInstance(
                NetApi.class.getClassLoader(),
                new Class[]{NetApi.class},
                netUtil);
    }
    public static NetApi get(OnNetEventListener listener) {
        return get(true, listener);
    }

    public static NetApi get(boolean shouldCache, OnNetEventListener listener) {
        NetInstance.netUtil.setOnNetEventListener(listener);
        return NetInstance.apiIntance;
    }

//    public static void imageLoader(final String imageurl, final ImageView view,
//                                   final int defaultImageResId, final int errorImageResId, final HttpRequest.ImageShapeType type) {
//        HttpRequest.requestImage(imageurl,
//                view, defaultImageResId, errorImageResId, type);
//    }

}
