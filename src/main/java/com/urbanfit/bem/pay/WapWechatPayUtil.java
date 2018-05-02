package com.urbanfit.bem.pay;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.tenpay.util.ConstantUtil;
import com.urbanfit.bem.tenpay.util.XMLUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Created by Administrator on 2018/4/27.
 */
public class WapWechatPayUtil {

    private static final Logger logger = Logger.getLogger(WapWechatPayUtil.class);

    public static JSONObject submitPrepayToWeChat(HttpServletRequest request, String orderNum, String goodsName,
                                                  int totalPrice, String callbackUrl, String tradeType,
                                                  String returnUrl) {
        JSONObject retJson = new JSONObject();
        try {
            String noncestr = UUID.randomUUID().toString().replace("-", "");
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            packageParams.put("appid", ConstantUtil.APP_ID);
            packageParams.put("mch_id", ConstantUtil.PARTNER_ID);
            packageParams.put("nonce_str", noncestr);
            packageParams.put("body", goodsName);
            packageParams.put("out_trade_no", orderNum);
            packageParams.put("total_fee", totalPrice);
            System.out.println("ipAddress：" + getIpAddr(request));
            packageParams.put("spbill_create_ip", getIpAddr(request));
            packageParams.put("notify_url", callbackUrl);
            packageParams.put("trade_type", tradeType);

            String sign = PayCommonUtil.createSign("UTF-8", packageParams, ConstantUtil.PARTNER_KEY);
            packageParams.put("sign", sign);//加密

            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            logger.info("支付请求：" + requestXML);
            System.out.println("支付请求：" + requestXML);
            String resXml = HttpRequest.postData(ConstantUtil.URL, requestXML);

            logger.info("支付结果：" + resXml);
            System.out.println("支付结果：" + resXml);
            Map map = XMLUtil.doXMLParse(resXml);
            //交易保障
            if (map.get("return_code").toString().equals("SUCCESS") && map.get("result_code").toString().equals("SUCCESS")) {
                JSONObject reportParams = new JSONObject();
                String wechatPayUrl = map.get("mweb_url").toString() + "&redirect_url=" + returnUrl;
                reportParams.put("wechatPayUrl", wechatPayUrl);
                retJson.put("code", Constant.INTERFACE_SUCC);
                retJson.put("message", "调用微信支付成功");
                retJson.put("data", reportParams.toString());
            } else {
                retJson.put("code", Constant.INTERFACE_FAIL);
                retJson.put("message", map.get("err_code_des").toString());
                retJson.put("data", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            retJson.put("code", Constant.INTERFACE_PARAM_ERROR);
            retJson.put("message", "参数有误");
            retJson.put("data", "");
        }
        System.out.println(retJson.toString());
        return retJson;
    }

    /**
     * 获取ip地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
