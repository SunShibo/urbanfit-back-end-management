package com.urbanfit.bem.web.controller.api;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.entity.ClientInfo;
import com.urbanfit.bem.service.ClientInfoService;
import com.urbanfit.bem.service.ClientMessageService;
import com.urbanfit.bem.util.DateUtils;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.MD5Util;
import com.urbanfit.bem.util.StringUtils;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Administrator on 2018/4/25.
 */
@Controller
@RequestMapping("/apiClient")
public class ApiClientInfoController extends BaseCotroller{

    @Resource(name = "clientInfoService")
    private ClientInfoService clientInfoService;
    @Resource(name = "clientMessageService")
    private ClientMessageService clientMessageService;

    /**
     * 客户登录
     */
    @RequestMapping(value = "/login")
    public void login(HttpServletResponse response, String mobile, String password){
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            safeTextPrint(response, JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").
                    toString());
            return ;
        }
        ClientInfo clientInfo = clientInfoService.queryClientInfoByMobile(mobile);
        if(clientInfo == null){
            safeTextPrint(response, JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "用户不存在", "").toString());
            return ;
        }
        // 判断密码是否正确
        if(!MD5Util.digest(password).equals(clientInfo.getPassword())){
            safeTextPrint(response, JsonUtils.encapsulationJSON(2, "密码输入不正确", "").toString());
            return ;
        }
        safeTextPrint(response, JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "登录成功", JsonUtils.
                getJsonString4JavaPOJO(clientInfo, DateUtils.LONG_DATE_PATTERN)).toString()); ;
    }

    /**
     * 客户注册
     */
    @RequestMapping( value = "/register" )
    public void register(HttpServletResponse response, String mobile, String password, String confirmPassword){
        String result = clientInfoService.register(mobile, password, confirmPassword);
        safeTextPrint(response, result);
    }

    /**
     * 修改客户姓名
     */
    @RequestMapping("/update")
    public void updateClientInfo(HttpServletResponse response, ClientInfo clientInfo){
        String result = clientInfoService.updateClientInfo(clientInfo);
        safeTextPrint(response, result);
    }

    /**
     * 发送验证码
     */
    @RequestMapping("/codeSignIn")
    public void getCodeFoSignIn(HttpServletResponse response, String mobile, Integer type){
        String result = clientMessageService.sendCodeForSignIn(mobile, type);
        safeTextPrint(response, result);
    }

    /**
     * 重置密码
     */
    @RequestMapping(value = "/password")
    public void updatePassword(HttpServletResponse response, Integer type, String mobile, String newPassword,
                               String confirmPassword){
        String result = clientInfoService.updatePassword(type, mobile, newPassword, confirmPassword);
        safeTextPrint(response, result);
    }

    @RequestMapping("/queryByClientId")
    public void queryClientInfoById(Integer clientId, HttpServletResponse response){
        String result = "";
        if(clientId == null){
            result =  JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }else{
            ClientInfo clientInfo = clientInfoService.queryClientInfoByClientId(clientId);
            if(clientInfo == null){
                result = JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "用户不存在", "").toString();
            }else{
                result = JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", JsonUtils.
                        getJsonString4JavaPOJO(clientInfo, DateUtils.LONG_DATE_PATTERN)).toString();
            }
        }
        safeTextPrint(response, result);
    }

    @RequestMapping("/queryByOpenId")
    public void queryClientInfoByOpenId(String code, HttpServletResponse response){
        String result = clientInfoService.queryClientByOpenId(code);
        safeTextPrint(response, result);
    }

    @RequestMapping("/bangdingWechat")
    public void wechatClientBangding(String openId, Integer clientId, HttpServletResponse response){
        String result = clientInfoService.wechatClientBangding(openId, clientId);
        safeTextPrint(response, result);
    }
}
