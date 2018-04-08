package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2018/4/8.
 */
@Controller
@RequestMapping("/about")
public class AboutController extends BaseCotroller{

    @RequestMapping("/company")
    public ModelAndView redirectAboutCompany(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/about_company");
        return view;
    }

    @RequestMapping("/contact")
    public ModelAndView redirectContactUs(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/contact_us");
        return view;
    }

    @RequestMapping("/join")
    public ModelAndView redirectJoinPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/about_join");
        return view;
    }
}
