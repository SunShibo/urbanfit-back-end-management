package com.urbanfit.bem.web.controller.manage;

import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.service.CourseService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.bouncycastle.math.raw.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/10.
 */
@Controller
@RequestMapping("/course")
public class CourseController extends BaseCotroller{
    @Resource
    private CourseService courseService;

    @RequestMapping("/detail")
    public ModelAndView queryCourseDetail(Integer courseId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/course_info");
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        view.addObject("course", courseService.queryUpCourseByCourseId(courseId));
        view.addObject("courseId", courseId);
        return view;
    }

    @RequestMapping("/toJoin")
    public ModelAndView redirectJoinCourse(Integer courseId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/course_order");
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        view.addObject("course", courseService.queryUpCourseByCourseId(courseId));
        return view;
    }

    @RequestMapping("/list")
    public ModelAndView queryCourseList(Integer courseType, String provice, String city, String district,
                                        Integer pageNo, Integer pageSize){
        ModelAndView view = new ModelAndView();
        pager = courseService.queryWebCourseList(courseType, provice, city, district, getQueryInfo(pageNo, pageSize));
        view.setViewName("/course_list");
        view.addObject("lstCourse", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("courseType", courseType);
        view.addObject("provice", provice);
        view.addObject("city", city);
        view.addObject("district", district);
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        return view;
    }
}
