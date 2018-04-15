package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.service.CouponService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangyubo on 2018/4/15.
 */
@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseCotroller {
    @Resource
    private CouponService couponService;

    @RequestMapping("/detail")
    public void queryCouponDetail(HttpServletResponse response, String couponNum){
        String result = couponService.queryCouponDetail(couponNum);
        safeTextPrint(response, result);
    }
}

