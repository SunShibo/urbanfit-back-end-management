package com.urbanfit.bem.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.urbanfit.bem.cfg.pop.SystemConfig;

/**
 * Created by Administrator on 2018/4/27.
 */
public class WapAlipayUtil {
    public static final String APP_ID = SystemConfig.getString("alipay_appid");
    public static final String APP_PRIVATE_KEY = SystemConfig.getString("alipay_app_private_key");
    public static final String CHARSET = SystemConfig.getString("alipay_charset");
    public static final String ALIPAY_PUBLIC_KEY = SystemConfig.getString("alipay_public_key");

    public static String submitClientAlipay(String subject, String orderNum, Double price, String callbackUrl,
                                     String returnUrl){
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        //创建API对应的request
        alipayRequest.setReturnUrl(returnUrl);
        //在公共参数中设置回跳和通知地址
        alipayRequest.setNotifyUrl(callbackUrl);
        //填充业务参数
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":\"" + orderNum + "\"," +
                " \"total_amount\":\"" + price + "\"," +
                " \"subject\":\"" + subject + "\"," +
                " \"product_code\":\"QUICK_WAP_PAY\"" +
                " }");
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return form;
    }
}
