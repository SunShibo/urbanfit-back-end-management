package com.urbanfit.bem.entity;

import com.urbanfit.bem.common.base.BaseModel;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/9.
 */
public class Coupon extends BaseModel{

    public static final int TYPE_PERCENT = 0;
    public static final int TYPE_MINUS_MONEY = 1;

    public static final int STATUS_EXPIRED = 1;

    private Integer couponId;
    private String couponName;
    private String sourceName;
    private Double percent;
    private Date createTime;
    private Date beginTime;
    private Date endTime;
    private String couponNum;
    private Integer userId;
    private int status;

    private int type;
    private Double minusMoney;
    private Integer useAmount;
    private Integer remainAmount;
    private Integer courseType;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCouponNum() {
        return couponNum;
    }

    public void setCouponNum(String couponNum) {
        this.couponNum = couponNum;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Double getMinusMoney() {
        return minusMoney;
    }

    public void setMinusMoney(Double minusMoney) {
        this.minusMoney = minusMoney;
    }

    public Integer getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(Integer useAmount) {
        this.useAmount = useAmount;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Integer getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(Integer remainAmount) {
        this.remainAmount = remainAmount;
    }
}
