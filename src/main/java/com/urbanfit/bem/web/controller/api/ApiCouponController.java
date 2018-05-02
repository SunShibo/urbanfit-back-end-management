package com.urbanfit.bem.web.controller.api;

import com.urbanfit.bem.service.CouponService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/5/2.
 */
@Controller
@RequestMapping("/apiCoupon")
public class ApiCouponController extends BaseCotroller{

    @Resource(name = "couponService")
    private CouponService couponService;

    @RequestMapping("/detail")
    public void queryCouponDetail(HttpServletResponse response, String couponNum){
        String result = couponService.queryCouponDetail(couponNum);
        safeTextPrint(response, result);
    }
}
