package com.urbanfit.bem.entity;

import com.urbanfit.bem.common.base.BaseModel;

/**
 * Created by Administrator on 2018/5/31.
 */
public class CourseStore extends BaseModel{
    private Integer csId;
    private Integer courseId;
    private Integer storeId;

    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public CourseStore() {}

    public CourseStore(Integer courseId, Integer storeId) {
        this.courseId = courseId;
        this.storeId = storeId;
    }
}
