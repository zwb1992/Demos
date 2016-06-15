package com.zwb.paylibrary.weixinpay;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zwb.paylibrary.R;

import java.util.Random;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/6/15
 ***************************************/
public class WeiXinPay {
    private IWXAPI msgApi;
    private Context mContext;
    private PayReq req;
    public WeiXinPay(Context mContext, String APP_ID) {
        this.mContext = mContext;
        // APP_ID 替换为你的应用从官方网站申请到的合法appId
        msgApi = WXAPIFactory.createWXAPI(mContext, APP_ID);
        msgApi.registerApp(APP_ID);
        req = new PayReq();
        req.appId = APP_ID;
    }

    public void pay() {
        if (!msgApi.isWXAppInstalled()) {
            Toast.makeText(mContext, mContext.getString(R.string.isWXAppInstalled), Toast.LENGTH_SHORT).show();
            return;
        }
        if (msgApi.getWXAppSupportAPI() < Build.PAY_SUPPORTED_SDK_INT) {
            Toast.makeText(mContext, mContext.getString(R.string.getWXAppSupportAPI), Toast.LENGTH_SHORT).show();
            return;
        }
        req.packageValue = "Sign=WXPay";
        req.nonceStr = genNonceStr();
        req.timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        msgApi.sendReq(req);
    }
    private String genNonceStr() {
        Random random = new Random();
        return Md5Utils.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }
//
//    private String genAppSign(List<NameValuePair> params) {
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < params.size(); i++) {
//            sb.append(params.get(i).getName());
//            sb.append('=');
//            sb.append(params.get(i).getValue());
//            sb.append('&');
//        }
//        sb.append("key=");
//        sb.append("djksi8hUkdH936Rwyi320983762YSH83");
//
//        String appSign = Md5Utils.getMessageDigest(sb.toString().getBytes()).toUpperCase();
//        Log.e("orion", appSign);
//        return appSign;
//    }
}
