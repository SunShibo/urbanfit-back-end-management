package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.service.CoachAuthService;
import com.urbanfit.bem.web.controller.LoginController;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/1.
 */
@Controller
@RequestMapping("/auth")
public class CoachAuthController extends BaseCotroller {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource(name = "coachAuthService")
    private CoachAuthService coachAuthService;

    @RequestMapping("/add")
    public void addCoachAuth(HttpServletResponse response, String coachName, String coachCardNum){
        String result = coachAuthService.addCoachAuth(coachName, coachCardNum);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/delete")
    public void deleteCoachAuth(HttpServletResponse response, Integer authId){
        String result = coachAuthService.deleteCoachAuth(authId);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/update")
    public void updateCoachAuth(HttpServletResponse response, Integer authId, String coachName, String coachCardNum){
        String result = coachAuthService.updateCoachAuth(authId, coachName, coachCardNum);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/authPage")
    public ModelAndView redirectCoachAuthPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/coach_auth");
        return view;
    }

    @RequestMapping("/query")
    public void queryCoachAuth(HttpServletResponse response, String coachName, String coachCardNum){
        String result = coachAuthService.queryCoachAuth(coachName, coachCardNum);
        safeJsonPrint(response, result);
    }
}
