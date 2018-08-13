package com.urbanfit.bem.service;

import com.urbanfit.bem.dao.CourseStoreDao;
import com.urbanfit.bem.entity.Course;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/8/5.
 */
@Service("courseStoreService")
@Transactional
public class CourseStoreService {
    @Resource
    private CourseStoreDao courseStoreDao;

    public List<Course> queryCourseByStoreId(Integer storeId){
        return courseStoreDao.queryCourseByStoreId(storeId);
    }
}
