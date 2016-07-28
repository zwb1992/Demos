package com.zwb.paylibrary.alipay;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/***************************************
 * Author zhouweibin
 * Description .支付宝支付
 * Date:2016/6/15
 ***************************************/
public class Alipay {
    // 商户PID
    private static final String PARTNER = "2088021439977446";
    // 商户收款账号
    private static final String SELLER = "thxservice@163.com";
    // 商户私钥，pkcs8格式
    private static final String RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAM/eR5qSl+5p+f6D8PXQj8MNsbgey2eCzWD3Gy3xLKv7lBp0Hi7Hx8Kwr3PDE6hytcAAvecAtkbPsB9oM6ntesKYBxYpgJh2J76CpEgfAGTEvBPZxB8VZxtiRW3Hxt2uRnE/RaQ8K1uEelDHBNjpOKbJCe0jdvLmA8iics9Ny6nDAgMBAAECgYANjy9Wiud0Ot9+1bZ7Im6uR49nNkMIboTdJWFVftzuHwwLKbXqUUfbkFDxmTEIFOt8KdA5aFd7iQKxGcf89qvCNaK7ebN8DpNTGeJ2tqAtKTk4VLZDn7ZcGerHuaCSw0byu9gpG0jB7LvgKFqvBR2DsRyjMGigmbka0jjFV22T8QJBAPeiFWlUWX1iXefYi5RjoO5EkCIQUVT4DIOzdSDx/V4Vx2D1TLYKyo+OdL/j4De/lYyosi5bfDebSDVI0trGKOsCQQDW5D9sGXUK5IkxXiyycPGBK6tdPksHRsSZDZdgkiSmFGXp4Km3ep4pVLifimcwg0JNbT+veWtm/EKq3jNKTkyJAkAgzxW9JdLRfPXqINOlbmnFB8p86OKNumuWC3HKUqV6ELKg0v8940IhMQOg5OUxHa4Hu9YXtEFJ8MXpYWOmXrjPAkBJWmJ34Py9+iMGshHp23nk0OZPDuuHkVWctTH+KgTOmFntit4EHpwMmbnn49OzkKq6SVea64SJBAUx1GWHsNCZAkAKFwnCblYUfSZ4VWiURAMc6+ZmqPXkuRwQ/WdgGV8Eby+0q/4u7TAlU7Vr/2ZnwsUrfNbeGnkMVKnPWrgxa1L5";
    private static final String ALI_NOTIFY_URL = "http://notify.msp.hk/notify.htm";
    private static final String ALI_RETURN_URL = "m.alipay.com";
    public static final int PAY_COMPLE = 1;

    private Context mContext;
    private Handler mHandler;

    public Alipay(Context mContext, Handler handler) {
        super();
        this.mContext = mContext;
        this.mHandler = handler;
    }

    /**
     * call alipay sdk pay. 调用SDK支付
     */
    public void pay(String subject, String body, String price, String orderID) {
        // 订单
        String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01", getOutTradeNo());
//		String orderInfo = getOrderInfo(subject, body, price, orderID);

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！对订单做RSA 签名
         */
        String sign = sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
                + getSignType();

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                if (mContext != null && mHandler != null) {
                    PayTask alipay = new PayTask((Activity) mContext);
                    // 调用支付接口，获取支付结果
                    String result = alipay.pay(payInfo, true);
                    Message msg = new Message();
                    msg.what = PAY_COMPLE;
                    msg.obj = result;
                    mHandler.sendMessage(msg);
                }
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * create the order info. 创建订单信息
     */
    public String getOrderInfo(String subject, String body, String price,
                               String orderID) {
        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderID + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + ALI_NOTIFY_URL + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"" + ALI_RETURN_URL + "\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        // orderInfo += "&extra_common_param=\"" + userid +"\"";
        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    public String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

}
