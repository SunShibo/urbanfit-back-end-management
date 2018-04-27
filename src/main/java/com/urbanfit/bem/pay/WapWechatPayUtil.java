package com.urbanfit.bem.pay;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.tenpay.util.ConstantUtil;
import com.urbanfit.bem.tenpay.util.XMLUtil;
import com.urbanfit.bem.util.QRUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                                                  int totalPrice, String callbackUrl, String tradeType) {
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
            packageParams.put("spbill_create_ip", request.getLocalAddr());
            packageParams.put("notify_url", callbackUrl);
            packageParams.put("trade_type", tradeType);

            String sign = PayCommonUtil.createSign("UTF-8", packageParams, ConstantUtil.PARTNER_KEY);
            packageParams.put("sign", sign);//加密

            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            logger.info("支付请求：" + requestXML);
            long startTime=System.currentTimeMillis();
            String resXml = HttpRequest.postData(ConstantUtil.URL, requestXML);
            long endTime=System.currentTimeMillis();

            logger.info("支付结果：" + resXml);
            System.out.println("支付结果：" + resXml);
            Map map = XMLUtil.doXMLParse(resXml);
            //交易保障
            if (map.get("return_code").toString().equals("SUCCESS") && map.get("result_code").toString().equals("SUCCESS")) {
                JSONObject reportParams = new JSONObject();
                reportParams.put("appid", map.get("appid"));
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
}
