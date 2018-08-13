package com.urbanfit.bem.web.controller;

import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.entity.Course;
import com.urbanfit.bem.entity.Store;
import com.urbanfit.bem.service.CourseStoreService;
import com.urbanfit.bem.service.StoreService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2018/2/28.
 */
@Controller
@RequestMapping("/store")
public class StoreController extends BaseCotroller{
    @Resource(name = "storeService")
    private StoreService storeService;
    @Resource(name = "courseStoreService")
    private CourseStoreService courseStoreService;

    @RequestMapping(value = "/list")
    public ModelAndView queryStoreList(String provice, String city, String district, Integer pageNo,
                                       Integer pageSize){
        pager = storeService.queryStoreList(provice, city, district, getQueryInfo(pageNo, pageSize));
        ModelAndView view = new ModelAndView();
        view.setViewName("/store_list_new");
        view.addObject("lstStore", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("provice", provice);
        view.addObject("city", city);
        view.addObject("district", district);
        return view;
    }

    @RequestMapping(value = "/detail")
    public ModelAndView queryStoreDetail(Integer storeId){
        // 查询门店信息
        Store store = storeService.queryStoreByStoreId(storeId);
        List<Course> lstCourse = courseStoreService.queryCourseByStoreId(storeId);
        // 查询门店课程信息
        ModelAndView view = new ModelAndView();
        view.setViewName("/store_detail");
        view.addObject("store", store);
        view.addObject("lstCourse", lstCourse);
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        return view;
    }
}
