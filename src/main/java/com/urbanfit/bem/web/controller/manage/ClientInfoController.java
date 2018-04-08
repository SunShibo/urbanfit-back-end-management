package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/8.
 */
@Controller
@RequestMapping("/client")
public class ClientInfoController extends BaseCotroller{

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
}
