package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.entity.ClientInfo;
import com.urbanfit.bem.service.JoinWaitingCourseService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/27.
 */
@Controller
@RequestMapping("/join")
public class JoinWaitingCourseController extends BaseCotroller{
    @Resource
    private JoinWaitingCourseService joinWaitingCourseService;

    @RequestMapping("/add")
    public void addJoinWaitingCourse(HttpServletRequest request, HttpServletResponse response, String clientName,
                                     String clientMobile){
        ClientInfo clientInfo = getLoginClientInfo(request);
        Integer clientId = clientInfo == null ? null : clientInfo.getClientId();
        String result = joinWaitingCourseService.addJoinWaitingCourse(clientId, clientName, clientMobile);
        safeTextPrint(response, result);
    }
}
