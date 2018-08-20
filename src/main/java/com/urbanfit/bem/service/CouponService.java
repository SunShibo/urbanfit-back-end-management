package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.dao.CouponDao;
import com.urbanfit.bem.dao.CourseDao;
import com.urbanfit.bem.entity.Coupon;
import com.urbanfit.bem.entity.Course;
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
    @Resource
    private CourseDao courseDao;

    public String queryCouponDetail(String couponNum, Integer courseId){
        if(StringUtils.isEmpty(couponNum) || courseId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Coupon coupon = couponDao.queryCouponByCouponNum(couponNum);
        if(coupon == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "优惠码不存在", "").toString();
        }
        Course course = courseDao.queryCourseByCourseId(courseId);
        if(course == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程不存在", "").toString();
        }
        if(coupon.getCourseType() != null && !coupon.getCourseType().equals(course.getCourseType())){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "优惠码不适用该课程类型", "").toString();
        }
        if(coupon.getRemainAmount() != null && coupon.getRemainAmount() <= 0){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "优惠码已用完", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", JsonUtils.
                getJsonString4JavaPOJO(coupon, DateUtils.LONG_DATE_PATTERN)).toString();
    }
}
