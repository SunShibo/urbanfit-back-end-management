package com.urbanfit.bem.web.controller.wechat;

import com.alibaba.fastjson.JSONObject;
import com.urbanfit.bem.entity.bo.WechatBo;
import com.urbanfit.bem.util.HttpClientUtil;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.StringUtils;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/22.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController extends BaseCotroller{

    public static final String APPID = "wxfceafb8ea3eae188";
    public static final String SECRET = "39f122b6b85706b0b0e43682e3a69841";

    @RequestMapping("/accessToken")
    public void getAccessToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxfceafb8ea3eae188&secret=39f122b6b85706b0b0e43682e3a69841";
        System.out.println(HttpClientUtil.httpPostRequest(url));
    }

    @RequestMapping("/pay")
    public String payOrderMaster(HttpServletRequest request, String code, String params){
        try {
            //页面获取openId接口
            String getopenid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
                    APPID + "&secret="+ SECRET +"&code=" + code + "&grant_type=authorization_code";
            //向微信服务器发送get请求获取openIdStr
            String openIdStr = HttpClientUtil.httpPostRequest(getopenid_url);
            JSONObject json = JSONObject.parseObject(openIdStr);//转成Json格式
            String openId = json.getString("openid");//获取openId
            System.out.println("code：" + code + "，params：" + "，openId：" + openId);
            //拼接统一下单地址参数
            Map<String, String> paraMap = new HashMap<String, String>();
            //获取请求ip地址
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
            if(ip.indexOf(",") != -1){
                String[] ips = ip.split(",");
                ip = ips[0].trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/login")
    public void wechatLogin(HttpServletRequest request){
        String code = request.getParameter("code");
        System.out.println("code：" + code);
        // 获取access_token、openId信息
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID
                +"&secret=" + SECRET + "&code=" + code +"&grant_type=authorization_code";
        String result = HttpClientUtil.httpGetRequest(url);
        System.out.println("result：" + result);
        // 获取登录用户信息
        WechatBo wechatBo = (WechatBo)JsonUtils.getObject4JsonString(result, WechatBo.class);
        String wechatInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
                +  wechatBo.getAccess_token() + "&openid=" + wechatBo.getOpenid() + "&lang=zh_CN";
        String wechatInfoResult = HttpClientUtil.httpGetRequest(wechatInfoUrl);
        System.out.println("wechatInfoResult：" + wechatInfoResult);
    }
}
