package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.common.constants.SysConstants;
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
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Administrator on 2018/4/8.
 */
@Controller
@RequestMapping("/client")
public class ClientInfoController extends BaseCotroller{

    @Resource(name = "clientInfoService")
    private ClientInfoService clientInfoService;
    @Resource(name = "clientMessageService")
    private ClientMessageService clientMessageService;

    @RequestMapping("/toLogin")
    public ModelAndView redirectLoginPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/login");
        return view;
    }

    @RequestMapping("/toRegister")
    public ModelAndView redirectRegisternPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/register");
        return view;
    }

    @RequestMapping("/registerSuccess")
    public ModelAndView redirectRegisternSuccess(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/register_success");
        return view;
    }

    @RequestMapping("/toPassword")
    public ModelAndView redirectUpdatePassword(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/update_password");
        return view;
    }


    /**
     * 客户注册
     */
    @RequestMapping( value = "/register" )
    public void register(HttpServletResponse response, String mobile, String password, String confirmPassword,
                         String authCode){
        String result = clientInfoService.register(mobile, password, confirmPassword, authCode);
        safeTextPrint(response, result);
    }

    /**
     * 客户登录
     */
    @RequestMapping(value = "/login")
    public void login(HttpServletResponse response, String mobile, String password){
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            safeTextPrint(response, JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString());
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
        // 登陆客户信息放入Redis缓存
        super.setLoginClientInfo(clientInfo);
        String uuid = UUID.randomUUID().toString();
        super.putLoginClientInfo(uuid, clientInfo);
        super.setCookie(response, SysConstants.CURRENT_LOGIN_CLIENT_ID, uuid, SysConstants.SEVEN_DAY_TIME);
        safeTextPrint(response, JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "登录成功", JsonUtils.
                getJsonString4JavaPOJO(clientInfo, DateUtils.LONG_DATE_PATTERN)).toString()); ;
    }

    /**
     * 重置密码
     */
    @RequestMapping(value = "/password")
    public void updatePassword(HttpServletResponse response, Integer type, String mobile, String newPassword,
                               String confirmPassword, String authCode){
        String result = clientInfoService.updatePassword(type, mobile, newPassword, confirmPassword, authCode);
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

    @RequestMapping("/loginSuccess")
    public ModelAndView redirectLoginSuccessPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/login_success");
        return view;
    }

    @RequestMapping("/detail")
    public ModelAndView redirectClientInfoPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/client_info");
        return view;
    }

    @RequestMapping("/update")
    public void updateClientInfo(HttpServletRequest request, HttpServletResponse response, String name){
        ClientInfo clientInfo = getLoginClientInfo(request);
        if(clientInfo == null){
            safeTextPrint(response, JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "客户没有登陆", "").toString());
            return;
        }
        String result = clientInfoService.updateClientInfo(name, clientInfo.getClientId());
        safeTextPrint(response, result);
    }

    @RequestMapping("/toReset")
    public ModelAndView redirectResetPassword(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/reset_password");
        return view;
    }

    @RequestMapping("/resetSuccess")
    public ModelAndView redirectResetPasswordSuccess(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/reset_success");
        return view;
    }

    @RequestMapping("/signOut")
    public ModelAndView clientSignOut(HttpServletRequest request){
        super.removeSession(request, SysConstants.CURRENT_LOGIN_CLIENT);
        ModelAndView view = new ModelAndView();
        view.setViewName("/home");
        return view;
    }
}
