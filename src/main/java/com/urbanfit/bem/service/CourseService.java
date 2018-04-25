package com.urbanfit.bem.service;

import com.alibaba.fastjson.JSONObject;
import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.dao.CourseDao;
import com.urbanfit.bem.entity.Course;
import com.urbanfit.bem.entity.dto.ResultDTOBuilder;
import com.urbanfit.bem.util.DateUtils;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/3/2.
 */
@Service("courseService")
@Transactional
public class CourseService {
    @Resource
    private CourseDao courseDao;

    /**
     * 添加课程数据
     */
    public String addCourse(Integer courseType, String introduce){
        if(courseType == null || StringUtils.isEmpty(introduce)){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数有误")) ;
        }
        // 查询类型是否添加过
        Course course = courseDao.queryCourseByType(courseType);
        if(course != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "课程类型已经存在")) ;
        }
        Course courseseInfo = new Course();
        courseseInfo.setCourseType(courseType);
        courseseInfo.setIntroduce(introduce);
        courseDao.addCourse(courseseInfo);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "添加课程成功")) ;
    }

    /**
     * 修改课程信息
     */
    public String updateCourse(Integer courseId, String introduce){
        if(courseId == null || StringUtils.isEmpty(introduce)){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数有误")) ;
        }
        // 查询课程是否存在
        Course course = courseDao.queryCourseByCourseId(courseId);
        if(course == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "课程不存在")) ;
        }
        course.setIntroduce(introduce);
        courseDao.updateCourse(course);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "修改课程成功")) ;
    }

    public Course queryUpCourseByCourseId(Integer courseId){
        return courseDao.queryUpCourseByCourseId(courseId);
    }

    public String queryCourseDetail(Integer courseId){
        if(courseId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Course course = courseDao.queryUpCourseByCourseId(courseId);
        JSONObject jo = new JSONObject();
        if(course == null){
            jo.put("isHave", 0);
            jo.put("baseUrl", SystemConfig.getString("image_base_url"));
            jo.put("course", "");
        }else{
            jo.put("isHave", 1);
            jo.put("baseUrl", SystemConfig.getString("image_base_url"));
            jo.put("course", JsonUtils.getJsonString4JavaPOJO(course, DateUtils.LONG_DATE_PATTERN));
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }
}
