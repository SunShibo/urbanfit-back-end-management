package com.urbanfit.bem.pay;
import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.tenpay.util.ConstantUtil;
import com.urbanfit.bem.tenpay.util.XMLUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2018/6/25.
 */
public class WechatPayUtil {

    private static final Logger logger = Logger.getLogger(WebWeChatPayUtil.class);

    public static JSONObject submitPrepayToWeChat(HttpServletRequest request, String orderNum, String goodsName,
                                                  int totalPrice, String callbackUrl, String tradeType,
                                                  String openId) {
        JSONObject retJson = new JSONObject();
        try {
            String noncestr = UUID.randomUUID().toString().replace("-", "");
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            packageParams.put("appid", ConstantUtil.APP_ID);
            packageParams.put("body", goodsName);
            packageParams.put("mch_id", ConstantUtil.PARTNER_ID);
            packageParams.put("nonce_str", noncestr);
            packageParams.put("openid", openId);
            packageParams.put("out_trade_no", orderNum);
            packageParams.put("spbill_create_ip", getAddressIp(request));
            packageParams.put("total_fee", totalPrice);
            packageParams.put("trade_type", tradeType);
            packageParams.put("notify_url", callbackUrl);
            String sign = PayCommonUtil.createSign("UTF-8", packageParams, ConstantUtil.PARTNER_KEY);
            packageParams.put("sign", sign);//加密

            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            logger.info("支付请求：" + requestXML);
            long startTime = System.currentTimeMillis();
            String resXml = HttpRequest.postData(ConstantUtil.URL, requestXML);
            long endTime = System.currentTimeMillis();

            Integer execute_time = (int) ((endTime-startTime)/1000);
            logger.info("支付结果：" + resXml);
            System.out.println("支付结果：" + resXml);
            Map map = XMLUtil.doXMLParse(resXml);

            JSONObject reportParams = new JSONObject();
            reportParams.put("url", ConstantUtil.URL);
            reportParams.put("execute_time", execute_time);
            reportParams.put("return_code", map.get("return_code").toString());
            reportParams.put("return_msg", map.get("return_msg").toString());
            reportParams.put("result_code", map.get("result_code").toString());
            if (map.get("err_code") != null) {
                reportParams.put("err_code", map.get("err_code").toString());
                reportParams.put("err_code_des", map.get("err_code_des").toString());
            }
            reportParams.put("out_trade_no", orderNum);
            //交易保障
            if (map.get("return_code").toString().equals("SUCCESS") && map.get("result_code").toString().equals("SUCCESS")) {
                retJson.put("code", Constant.INTERFACE_SUCC);
                retJson.put("message", "下单成功.");

                String prepayId = (String) map.get("prepay_id");
                SortedMap<Object, Object> resultPackageParams = new TreeMap<Object, Object>();
                resultPackageParams.put("appId", ConstantUtil.APP_ID);
                resultPackageParams.put("timeStamp", System.currentTimeMillis());
                resultPackageParams.put("nonceStr", noncestr);
                resultPackageParams.put("signType", "MD5");
                resultPackageParams.put("package", "prepay_id=" + prepayId);
                String paySign = PayCommonUtil.createSign("UTF-8", resultPackageParams, ConstantUtil.PARTNER_KEY);;
                resultPackageParams.put("paySign", paySign);
                retJson.put("data", resultPackageParams);
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

    //获取请求ip地址
    private static String getAddressIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        if(ip.indexOf(",")!=-1){
            String[] ips = ip.split(",");
            ip = ips[0].trim();
        }
        return ip;
    }
}
