package com.urbanfit.bem.web.controller.api;

import com.urbanfit.bem.service.CourseService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/5/2.
 */
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

    @RequestMapping
    public void queryCourseList(HttpServletResponse response, Integer storeId){
        String result = courseService.queryCourseList(storeId);
        safeTextPrint(response, result);
    }
}
