package com.urbanfit.bem.web.controller;

import com.bskcare.ch.util.http.HttpClientUtils;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.StringUtils;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/25.
 */
@Controller
public class GetJssdkSignature extends BaseCotroller{
    private static final long serialVersionUID = 1L;

    public static String getAccessToken() {
        String access_token = "";
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"
                    + "&appid=wxfceafb8ea3eae188&secret=39f122b6b85706b0b0e43682e3a69841";

            String inputLine = HttpClientUtils.getContentByGet(url, "utf-8");

            if (!StringUtils.isEmpty(inputLine)) {
                Map<String, Object> mapInfo = new HashMap<String, Object>();
                mapInfo = JsonUtils.getMap4Json(inputLine);
                if (mapInfo.containsKey("access_token")) {
                    access_token = mapInfo.get("access_token").toString();
                }
            }
        } catch (Exception e) {
            System.out.println("获取失败");
            e.printStackTrace();
            access_token = "fail";
        }
        return access_token;
    }

    public static String getJsapiTicket(String access_token) {
        String jsapi_ticket = "";
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                    + access_token + "&type=jsapi";

            String inputLine = HttpClientUtils.getContentByGet(url, "utf-8");

            if (!StringUtils.isEmpty(inputLine)) {
                Map<String, Object> mapInfo = new HashMap<String, Object>();
                mapInfo = JsonUtils.getMap4Json(inputLine);
                if (mapInfo.containsKey("ticket")) {
                    jsapi_ticket = mapInfo.get("ticket").toString();
                }
            }
        } catch (Exception e) {
            System.out.println("获取失败");
            e.printStackTrace();
            jsapi_ticket = "fail";
        }
        return jsapi_ticket;
    }

    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
