package com.urbanfit.bem.dao;

import com.urbanfit.bem.entity.CourseSizeDetail;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/31.
 */
public interface CourseSizeDetailDao {
    public List<CourseSizeDetail> queryCourseSizeDetail(Integer courseId);

    public CourseSizeDetail queryCourseSizeDetailByMap(Map<String, Object> map);

    public CourseSizeDetail queryCourseSizeDetailById(Integer detailId);
}
