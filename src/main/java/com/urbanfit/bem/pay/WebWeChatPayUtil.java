package com.urbanfit.bem.pay;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.tenpay.util.ConstantUtil;
import com.urbanfit.bem.tenpay.util.XMLUtil;
import com.urbanfit.bem.util.QRUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Administrator on 2018/4/21.
 */
public class WebWeChatPayUtil {

    private static final Logger logger = Logger.getLogger(WeChatPayUtil.class);

    public static JSONObject submitPrepayToWeChat(HttpServletRequest request, HttpServletResponse response,
                                                  String orderNum, String goodsName, int totalPrice, String callbackUrl,
                                                  String tradeType, String product_id) {;
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
            packageParams.put("product_id", product_id);

            String sign = PayCommonUtil.createSign("UTF-8", packageParams, ConstantUtil.PARTNER_KEY);
            packageParams.put("sign", sign);//加密

            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            logger.info("支付请求：" + requestXML);
            long startTime=System.currentTimeMillis();
            String resXml = HttpRequest.postData(ConstantUtil.URL, requestXML);
            long endTime=System.currentTimeMillis();

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
                String urlCode = (String) map.get("code_url");//微信二维码短链接
                retJson.put("code", Constant.INTERFACE_SUCC);
                retJson.put("message", "下单成功.");
                JSONObject jo = new JSONObject();
                String wechatPayQr = QRUtil.generatedQRCode(urlCode, "wechat_pay_qr_url", "wechat_pay_qr_visit_url");
                jo.put("orderNum", orderNum);
                jo.put("wechatPayQr", wechatPayQr);
                retJson.put("data", jo.toString());
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
