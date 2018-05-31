package com.urbanfit.bem.dao;

import com.urbanfit.bem.entity.CourseStore;
import com.urbanfit.bem.entity.Store;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/31.
 */
public interface CourseStoreDao {

    public List<Store> queryCourseStore(Integer courseId);

    public CourseStore queryCourseStoreByMap(Map<String, Object> map);
}
