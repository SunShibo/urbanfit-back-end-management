package com.urbanfit.bem.service;

import com.alibaba.fastjson.JSONException;
import com.google.gson.Gson;
import com.urbanfit.bem.util.HttpClientUtil;
import com.urbanfit.bem.util.wechat.HttpRequestUtil;
import com.urbanfit.bem.util.wechat.WeiXinQRCode;
import com.urbanfit.bem.util.wechat.WeiXinResult;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service("wechatService")
@Transactional
public class WechatService {
    private static final Logger log = Logger.getLogger(WechatService.class);
    // 微信公众号appId
    private static String APPID = "wxfceafb8ea3eae188";
    private static String APPSECRET = "39f122b6b85706b0b0e43682e3a69841";
    // 临时二维码
    private final static String QR_SCENE = "QR_SCENE";
    // 永久二维码
    private final static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";
    // 永久二维码(字符串)
    private final static String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";
    // 创建二维码
    private String create_ticket_path = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
    // 通过ticket换取二维码
    private String showqrcode_path = "https://mp.weixin.qq.com/cgi-bin/showqrcode";


    /*public static WeiXinResult showQrcode(String savePath) throws Exception{
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("ticket", HttpRequestUtil.urlEncode(createForeverTicket(), HttpRequestUtil.DEFAULT_CHARSET));
        WeiXinResult result = HttpRequestUtil.downMeaterMetod(params,HttpRequestUtil.GET_METHOD, showqrcode_path, savePath);
        return result;
    }*/

    public static Map createForeverStrQr(String sceneStr, String accessToken) {
        RestTemplate rest = new RestTemplate();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken ;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("action_name", "QR_LIMIT_STR_SCENE");
        Map<String, Object> action = new HashMap<String, Object>();
        Map<String, Object> scene = new HashMap<String, Object>();
        scene.put("scene_str", sceneStr);
        action.put("scene", scene);
        param.put("action_info", action);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        HttpEntity requestEntity = new HttpEntity(param, headers);
        Map result = null;
        try {
            ResponseEntity<Map> entity = rest.exchange(url, HttpMethod.POST, requestEntity,Map.class, new Object[0]);
            log.info("调用生成微信永久二维码URL接口返回结果:" + entity.getBody());
            result = (Map) entity.getBody();
        } catch (Exception e) {
            log.error("调用生成微信永久二维码URL接口异常", e);
        }
        System.out.println(result);
        return result;
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
        createForeverStrQr("shishaonan", getAccessToken());
    }
}
