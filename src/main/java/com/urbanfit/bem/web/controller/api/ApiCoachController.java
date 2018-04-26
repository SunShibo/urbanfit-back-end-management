package com.urbanfit.bem.web.controller.api;

import com.urbanfit.bem.service.CoachService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/26.
 */
@Controller
@RequestMapping("/apiCoach")
public class ApiCoachController extends BaseCotroller{
    @Resource
    private CoachService coachService;

    @RequestMapping("/list")
    public void queryCoachList(HttpServletResponse response, Integer pageNo, Integer pageSize){
        String result = coachService.queryApiCoachList(getQueryInfo(pageNo, pageSize));
        safeTextPrint(response, result);
    }
}