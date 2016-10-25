package com.zwb.frameutils.http;

import com.zwb.volley.help.GET;
import com.zwb.volley.net.NetRequest;

/**
 * Created by zwb
 * Description
 * Date 16/10/24.
 */
public interface NetApi {
    @GET("baike.baidu.com/link?url=tXjFeTKBE2G9mGpxK1SHs2J6nFvBwnv7NIr4Oo29YYWThN7tp-HJO_AZzBWlrQHB18T9sIJ5Yq8Od_jDgebJUa")
    NetRequest get();
}
