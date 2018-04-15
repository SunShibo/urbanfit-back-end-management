package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.dao.CouponDao;
import com.urbanfit.bem.entity.Coupon;
import com.urbanfit.bem.util.DateUtils;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * Created by wangyubo on 2018/4/15.
 */
@Service("couponService")
@Transactional
public class CouponService {
    @Resource
    private CouponDao couponDao;

    public String queryCouponDetail(String couponNum){
        if(StringUtils.isEmpty(couponNum)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Coupon coupon = couponDao.queryCouponByCouponNum(couponNum);
        if(coupon == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "不存在", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", JsonUtils.
                getJsonString4JavaPOJO(coupon, DateUtils.LONG_DATE_PATTERN)).toString();
    }
}
