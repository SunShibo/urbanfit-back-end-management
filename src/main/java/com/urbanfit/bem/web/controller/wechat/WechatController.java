package com.urbanfit.bem.web.controller.wechat;

import com.urbanfit.bem.util.HttpClientUtil;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/4/22.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController extends BaseCotroller{

    @RequestMapping("/accessToken")
    public void getAccessToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxfceafb8ea3eae188&secret=39f122b6b85706b0b0e43682e3a69841";
        System.out.println(HttpClientUtil.httpPostRequest(url));
    }
}
