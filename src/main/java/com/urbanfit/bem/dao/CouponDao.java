package com.urbanfit.bem.dao;


import com.urbanfit.bem.entity.Coupon;

/**
 * Created by Administrator on 2018/4/9.
 */
public interface CouponDao {

    public Coupon queryCouponById(Integer couponId);

    public void updateCouponStatus(Integer couponId);

    public Coupon queryCouponByCouponNum(String couponNum);

    public void updateCouponRemainAmount(String couponNum);
}
