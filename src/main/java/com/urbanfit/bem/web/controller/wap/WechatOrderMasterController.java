package com.urbanfit.bem.web.controller.wap;

import com.alibaba.fastjson.JSONObject;
import com.urbanfit.bem.service.OrderMasterService;
import com.urbanfit.bem.util.HttpClientUtil;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/wechatOrder")
public class WechatOrderMasterController extends BaseCotroller {
    @Resource
    private OrderMasterService orderMasterService;

    public static final String APPID = "wxfceafb8ea3eae188";
    public static final String SECRET = "39f122b6b85706b0b0e43682e3a69841";

    @RequestMapping("/addOrder")
    public void addOrderMaster(HttpServletRequest request, HttpServletResponse response, String params,
                                  Integer clientId, String code){
        try {
            //页面获取openId接口
            String getopenid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
                    APPID + "&secret=" + SECRET + "&code=" + code + "&grant_type=authorization_code";
            //向微信服务器发送get请求获取openIdStr
            String openIdStr = HttpClientUtil.httpPostRequest(getopenid_url);
            JSONObject json = JSONObject.parseObject(openIdStr);        //转成Json格式
            String openId = json.getString("openid");                  //获取openId
            System.out.println("code：" + code + "，params：" + params + "，openId：" + openId);
            // 添加订单
            String result = orderMasterService.wechatAddOrderMaster(request, response, openId, params, clientId);
            safeTextPrint(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/payOrderAgain")
    public void payOrderMasterAgain(HttpServletRequest request, HttpServletResponse response, String params,
                                    String code){
        try {
            //页面获取openId接口
            String getopenid_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" +
                    APPID + "&secret=" + SECRET + "&code=" + code + "&grant_type=authorization_code";
            //向微信服务器发送get请求获取openIdStr
            String openIdStr = HttpClientUtil.httpPostRequest(getopenid_url);
            JSONObject json = JSONObject.parseObject(openIdStr);        //转成Json格式
            String openId = json.getString("openid");                  //获取openId
            System.out.println("code：" + code + "，openId：" + openId);
            String result = orderMasterService.payWechatOrderMasterAgain(params, openId, request);
            safeHtmlPrint(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
