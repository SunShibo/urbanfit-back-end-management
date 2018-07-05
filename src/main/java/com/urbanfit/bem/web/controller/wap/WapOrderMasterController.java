package com.urbanfit.bem.web.controller.wap;

import com.urbanfit.bem.pay.WapWechatPayUtil;
import com.urbanfit.bem.service.OrderMasterService;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/27.
 */
@Controller
@RequestMapping("/wapOrder")
public class WapOrderMasterController extends BaseCotroller{
    @Resource
    private OrderMasterService orderMasterService;

    @RequestMapping("/list")
    public void queryOrderMasterList(HttpServletResponse response, Integer pageNo, Integer pageSize,
                                     Integer clientId, Integer wechatType){
        String result = orderMasterService.queryOrderMasterList(clientId, wechatType, getQueryInfo(pageNo, pageSize));
        safeTextPrint(response, result);
    }

    @RequestMapping("/detail")
    public void queryClientOrderMasterDetail(HttpServletResponse response, String orderNum){
        String result = orderMasterService.queryClientOrderMasterDetail(orderNum);
        safeTextPrint(response, result);
    }

    @RequestMapping("/addOrder")
    public void addWapOrderMaster(HttpServletRequest request, HttpServletResponse response, String params,
                               Integer clientId){
        String result = orderMasterService.wapAddOrderMaster(request, response, params, clientId);
        safeTextPrint(response, result);
    }

    @RequestMapping("/payOrderAgain")
    public void payWapOrderMasterAgain(HttpServletRequest request, HttpServletResponse response, String params){

        String result = orderMasterService.payWapOrderMasterAgain(params, request, response);
        safeHtmlPrint(response, result);
    }

    @RequestMapping("/wechatPayAgain")
    public void wechatPayWapOrderMasterAgain(HttpServletRequest request){
        System.out.println(request.getParameter("code"));
    }
}
