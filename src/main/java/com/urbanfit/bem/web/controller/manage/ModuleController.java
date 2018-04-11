package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.entity.Module;
import com.urbanfit.bem.service.ActivityMessageService;
import com.urbanfit.bem.service.BannerService;
import com.urbanfit.bem.service.ModuleService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/9.
 */
@Controller
@RequestMapping("/module")
public class ModuleController extends BaseCotroller{
    @Resource
    private ModuleService moduleService;
    @Resource
    private BannerService bannerService;
    @Resource
    private ActivityMessageService activityMessageService;

    @RequestMapping("/clist")
    public ModelAndView queryModuleList(Integer type, Integer pageNo, Integer pageSize){
        ModelAndView view = new ModelAndView();
        if(type == Module.TYPE_HOME_PAGE){    // 首页
            view.setViewName("/home");
        }else if(type == Module.TYPE_MESSAGE_PAGE){    // 活动资讯
            view.setViewName("/message");
            pager = activityMessageService.queryActivityMessageList(getQueryInfo(pageNo, pageSize));
            view.addObject("lstMessage", pager.getDatas());
            view.addObject("pager", pager);
            view.addObject("pageNo", pageNo);
        }
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        view.addObject("lstBanner", bannerService.queryBannerList(type));
        view.addObject("module", moduleService.queryModuleList(type));
        return view;
    }

    @RequestMapping("/list")
    public void queryModuleList(HttpServletResponse response, Integer type){
        String result = moduleService.queryClientModuleList(type);
        safeTextPrint(response, result);
    }

    @RequestMapping("/toHome")
    public ModelAndView redirectHomePage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/home");
        return view;
    }

    @RequestMapping("/toMessage")
    public ModelAndView redirectMessagePage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/message");
        return view;
    }
}
