package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.entity.ClientInfo;
import com.urbanfit.bem.entity.OrderMaster;
import com.urbanfit.bem.pay.AlipayUtil;
import com.urbanfit.bem.pay.WebAlipayUtil;
import com.urbanfit.bem.service.ClientInfoService;
import com.urbanfit.bem.service.OrderMasterService;
import com.urbanfit.bem.tenpay.handler.PrepayIdRequestHandler;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.web.controller.GetJssdkSignature;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2018/4/9.
 */
@Controller
@RequestMapping("/order")
public class OrderMasterController extends BaseCotroller{
    @Resource
    private OrderMasterService orderMasterService;
    @Resource
    private ClientInfoService clientInfoService;

    @RequestMapping("/list")
    public ModelAndView queryClientOrderMaster(HttpServletRequest request, String orderNum, Integer status,
                                               Integer pageNo, Integer pageSize){
        ModelAndView view = new ModelAndView();
        view.setViewName("/order_master_list");
        ClientInfo clientInfo = getLoginClientInfo(request);
        if(clientInfo == null){
            return view;
        }
        pager = orderMasterService.queryClientOrderMaster(clientInfo.getClientId(), orderNum, status,
                getQueryInfo(pageNo, pageSize));

        view.addObject("lstOrder", pager.getDatas());
        view.addObject("pageNo", pageNo);
        view.addObject("pager", pager);
        view.addObject("orderNum", orderNum);
        view.addObject("status", status);
        clientInfo = clientInfoService.queryClientInfoByClientId(getLoginClientInfo(request).getClientId());
        view.addObject("clientInfo", clientInfo);
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        return view;
    }

    @RequestMapping("/detail")
    public void queryClientOrderMasterDetail(HttpServletRequest request, HttpServletResponse response,
                                             String orderNum){
        ClientInfo clientInfo = getLoginClientInfo(request);
        String result = orderMasterService.queryClientOrderMasterDetail(clientInfo.getClientId(), orderNum);
        safeTextPrint(response, result);
    }

    @RequestMapping("/add")
    public void addClientOrderMaster(HttpServletRequest request, HttpServletResponse response, String params){
        ClientInfo clientInfo = getLoginClientInfo(request);
        /*  未来这句话要去掉   这个是用session取的登录信息*/
        ClientInfo sessionLoginUser = sgetLoginUser();
        if (sessionLoginUser == null ){
            String result = JsonUtils.encapsulationJSON(Constant.INTERFACE_CLIENT_NO_LOGIN, "没有登录账号", "").toString();
            safeTextPrint(response, result);
            return;
        }
        String result = orderMasterService.addClientOrderMaster(params, clientInfo, request, response);
        safeTextPrint(response, result);
    }

    @RequestMapping("/payAgain")
    public void payOrderMasterAgain(HttpServletRequest request, HttpServletResponse response, String params){
        ClientInfo clientInfo = getLoginClientInfo(request);
        String result = orderMasterService.payOrderMasterAgain(request, response, params, clientInfo);
        safeHtmlPrint(response, result);
    }

    @RequestMapping("/orderAlipayCallback")
    public void payOrderMasterSuccessAlipayCallback(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        System.out.println("================调用支付宝支付回调函数==============");
        String result = "fail";
        System.out.println("checkAlipayCallback：" + WebAlipayUtil.checkAlipayCallback(request));
        // 支付宝回调函数参数验证通过
        /*if(WebAlipayUtil.checkAlipayCallback(request)){*/
            Map<String, String> params = AlipayUtil.getAlipayCallbackParams(request);
            System.out.println(params.get("trade_status"));
            if (params.get("trade_status").equals(AlipayUtil.TRADE_SUCCESS)) {
                System.out.println("============即将进入service==========");
                System.out.println("out_trade_no" + params.get("out_trade_no"));
                // 同步支付信息
                orderMasterService.payOrderMasterSuccess(params.get("out_trade_no"), OrderMaster.PAYMENT_ALIPAY);
                result = "success";
            } else if (params.get("trade_status").equals(AlipayUtil.TRADE_FINISHED)) {
                result = "success";
            } else {
                log.debug("支付宝回调支付结果未成功：状态trade_status=" + params.get("trade_status"));
            }
       /* }else {
            log.debug("---------------支付宝回调签名验证失败-------------");
        }*/
        safeTextPrint(response, result);
    }

    @RequestMapping("/orderWeChatPayCallback")
    public void payOrderMasterSuccessWeChatPayCallback(HttpServletResponse response) throws Exception{
        System.out.println("调用微信回调函数");
        PrintWriter out = null;
        StringBuffer xmlStr = new StringBuffer();
        try {
            BufferedReader reader = super.getRequest().getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                xmlStr.append(line);
            }
            log.debug(">>>>>微信支付通知信息>>>>>>>" + xmlStr.toString());
            Map<String,String> map = PrepayIdRequestHandler.parse2(xmlStr.toString());
            StringBuffer result = new StringBuffer();
            result.append("<xml>");
            if(map != null) {
                log.debug(">>>>>微信支付订单信息>>>>>>>" + map.get("out_trade_no"));
                if ("SUCCESS".equals(map.get("return_code")) && "SUCCESS".equals(map.get("result_code"))) {
                    orderMasterService.payOrderMasterSuccess(map.get("out_trade_no"), OrderMaster.PAYMENT_WECHAT);
                    result.append("<return_code>SUCCESS</return_code>");
                    result.append("<return_msg>OK</return_code>");
                }else{
                    result.append("<return_code>FAIL</return_code>");
                    result.append("<return_msg>支付失败</return_code>");
                }
            }else{
                result.append("<return_code>FAIL</return_code>");
                result.append("<return_msg>签名验证失败</return_code>");
            }
            result.append("</xml>");
            safeTextPrint(response, result.toString());
        }catch (Exception e) {
            Logger.getLogger(getClass()).error("qlydweixinotify.do", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @RequestMapping("/paySuccess")
    public ModelAndView redirectPaySuccessPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/course_pay_success");
        return view;
    }

    @RequestMapping("/wechatPay")
    public ModelAndView redirectWechatPayPage(String orderNum, String wechatPayQr){
        ModelAndView view = new ModelAndView();
        view.setViewName("/course_wechat_pay");
        view.addObject("orderNum", orderNum);
        view.addObject("order", orderMasterService.queryOderMaterDetail(orderNum));
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        view.addObject("wechatPayQr", wechatPayQr);
        return view;
    }

    @RequestMapping("/orderDetail")
    public void queryPayOrderMasterDetail(HttpServletResponse response, String orderNum){
        String result = orderMasterService.queryPayOrderMasterDetail(orderNum);
        safeTextPrint(response, result);
    }
}
