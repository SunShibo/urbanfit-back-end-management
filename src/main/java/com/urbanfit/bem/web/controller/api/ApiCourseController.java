package com.urbanfit.bem.web.controller.api;

import com.urbanfit.bem.service.CourseService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/apiCourse")
public class ApiCourseController extends BaseCotroller{
    @Resource(name = "courseService")
    private CourseService courseService;

    @RequestMapping("/detail")
    public void queryCourseById(HttpServletResponse response, Integer courseId){
        String result = courseService.queryCourseDetail(courseId);
        safeTextPrint(response, result);
    }

    @RequestMapping("/storeList")
    public void queryStoreCourseList(HttpServletResponse response, Integer storeId, Integer pageNo,
                                     Integer pageSize){
        String result = courseService.queryStoreCourseList(storeId, getQueryInfo(pageNo, pageSize));
        safeTextPrint(response, result);
    }

    @RequestMapping("/list")
    public void queryCourseList(HttpServletResponse response, Integer courseType, String provice, String city,
                                String district, Integer pageNo, Integer pageSize){
        String result = courseService.queryCourseList(courseType, provice, city, district, getQueryInfo(
                pageNo, pageSize));
        safeTextPrint(response, result);
    }
}
