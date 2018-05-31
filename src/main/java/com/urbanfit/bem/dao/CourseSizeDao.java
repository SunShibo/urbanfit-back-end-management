package com.urbanfit.bem.dao;

import com.urbanfit.bem.entity.CourseSize;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/31.
 */
public interface CourseSizeDao {
    public List<CourseSize> queryCourseSize(Integer courseId);

    public List<CourseSize> queryCourseSizeInfo(Map<String, Object> map);
}
