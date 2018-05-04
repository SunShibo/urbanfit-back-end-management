package com.urbanfit.bem.service;

import com.alibaba.fastjson.JSONException;
import com.google.gson.Gson;
import com.urbanfit.bem.util.HttpClientUtil;
import com.urbanfit.bem.util.wechat.HttpRequestUtil;
import com.urbanfit.bem.util.wechat.WeiXinQRCode;
import com.urbanfit.bem.util.wechat.WeiXinResult;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/5/4.
 */
@Service("wechatService")
@Transactional
public class WechatService {
    private static final Logger log = Logger.getLogger(WechatService.class);
    // 微信公众号appId
    private static String APPID = "wxfceafb8ea3eae188";
    private static String APPSECRET = "39f122b6b85706b0b0e43682e3a69841";
    // 永久二维码
    private final static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
    // 永久二维码(字符串)
    private final static String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";
    // 创建二维码
    private static String create_ticket_path = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
    // 通过ticket换取二维码
    private static String showqrcode_path = "https://mp.weixin.qq.com/cgi-bin/showqrcode";


    public static WeiXinResult showQrcode(String savePath) throws Exception{
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("ticket", HttpRequestUtil.urlEncode(createForeverTicket(), HttpRequestUtil.DEFAULT_CHARSET));
        WeiXinResult result = HttpRequestUtil.downMeaterMetod(params,HttpRequestUtil.GET_METHOD, showqrcode_path, savePath);
        return result;
    }

    /**
     * 创建永久二维码(数字)
     */
    public static String createForeverTicket() {
        TreeMap<String,String> params = new TreeMap<String,String>();
        /*params.put("access_token", getAccessToken());*/
        params.put("access_token", "9_EiOSsgzmUJwUVGlLbFbe_OKdrlrj7xGfjK6pIwIc-ONfRbhnfIzWhMn2NFmJDQKyScOpODms0mxPhWdSdv4lrhpnb4icUWCOG5T25TU1TMygXp2iZ8zr6InTv4tGTPNPpK75hbIG8tfmiN_2LVPaAGAWGA");
        Map<String,Integer> intMap = new HashMap<String,Integer>();
        intMap.put("scene_id", 123456);
        Map<String,Map<String,Integer>> mapMap = new HashMap<String,Map<String,Integer>>();
        mapMap.put("scene", intMap);
        Map<String,Object> paramsMap = new HashMap<String,Object>();
        paramsMap.put("action_name", QR_LIMIT_SCENE);
        paramsMap.put("action_info", mapMap);
        String data = new Gson().toJson(paramsMap);
        data =  HttpRequestUtil.HttpsDefaultExecute(HttpRequestUtil.POST_METHOD, create_ticket_path, params, data);
        WeiXinQRCode wxQRCode = null;
        try {
            wxQRCode = new Gson().fromJson(data, WeiXinQRCode.class);
        } catch (Exception e) {
            wxQRCode = null;
            e.printStackTrace();
        }
        return wxQRCode==null?null:wxQRCode.getTicket();
    }

    public static String getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + APPID + "&secret=" + APPSECRET;
        String result = HttpClientUtil.httpPostRequest(url);
        JSONObject jsonObject = JSONObject.fromObject(result);
        if (null != jsonObject) {
            try {
                result = jsonObject.getString("access_token");
            } catch (JSONException e) {
                log.info("获取token失败 errcode:"+jsonObject.getInt("errcode") +",errmsg:"
                        + jsonObject.getString("errmsg"));
            }
        }
        return result;
    }

    public static void main(String args[]) throws Exception {
        System.out.println(showQrcode("D:\\images\\test.jpg"));
    }
}
