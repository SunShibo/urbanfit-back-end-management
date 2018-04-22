package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.service.ActivityMessageService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/11.
 */
@Controller
@RequestMapping("/message")
public class ActivityMessageController extends BaseCotroller{

    @Resource
    private ActivityMessageService activityMessageService;

    @RequestMapping("/list")
    public void queryActivityMessage(HttpServletResponse response, Integer pageNo, Integer pageSize){
        String result = activityMessageService.queryClientActivityMessageList(getQueryInfo(pageNo, pageSize));
        safeTextPrint(response, result);
    }

    @RequestMapping("/detail")
    public void queryActivityMessageDetail(HttpServletResponse response, Integer messageId){
        String result = activityMessageService.queryClientActivityMessageDetail(messageId);
        safeTextPrint(response, result);
    }

    @RequestMapping("/cdetail")
    public ModelAndView queryActivityMessageDetail(Integer messageId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/message_detail");
        view.addObject("message", activityMessageService.queryActivityMessageDetail(messageId));
        return view;
    }

    @RequestMapping("/toDetail")
    public ModelAndView redirectActivityMessageDetail(Integer messageId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/message_detail");
        view.addObject("messageId", messageId);
        return view;
    }
}
