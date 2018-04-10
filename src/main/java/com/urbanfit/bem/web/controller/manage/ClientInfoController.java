package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.entity.ClientInfo;
import com.urbanfit.bem.service.ClientInfoService;
import com.urbanfit.bem.service.ClientMessageService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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
    public void register(HttpServletResponse response, String mobile, String password, String confirmPassword){
        String result = clientInfoService.register(mobile, password, confirmPassword);
        safeTextPrint(response, result);
    }

    /**
     * 客户登录
     */
    @RequestMapping(value = "/login")
    public void login(HttpServletResponse response, String mobile, String password){
        String result = clientInfoService.login(mobile, password);
        safeTextPrint(response, result);
    }

    /**
     * 重置密码
     */
    @RequestMapping(value = "/password")
    public void updatePassword(HttpServletResponse response, String mobile, String newPassword,
                               String confirmPassword){
        String result = clientInfoService.updatePassword(mobile, newPassword, confirmPassword);
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
    public void updateClientInfo(HttpServletResponse response, String name){
        String result = clientInfoService.updateClientInfo(name, 1);
        safeTextPrint(response, result);
    }
}
