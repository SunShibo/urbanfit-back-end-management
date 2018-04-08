package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.service.CoachService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/8.
 */
@Controller
@RequestMapping("/coach")
public class CoachController extends BaseCotroller{
    @Resource
    private CoachService coachService;

    @RequestMapping("/list")
    public ModelAndView queryCoachList(Integer pageNo, Integer pageSize){
        ModelAndView view = new ModelAndView();
        pager = coachService.queryCoachList(getQueryInfo(pageNo, pageSize));
        view.setViewName("/coach_list");
        view.addObject("lstCoach", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        return view;
    }
}
