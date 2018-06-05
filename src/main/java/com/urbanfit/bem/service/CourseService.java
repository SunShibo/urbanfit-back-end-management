package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.dao.*;
import com.urbanfit.bem.entity.Course;
import com.urbanfit.bem.entity.CourseSize;
import com.urbanfit.bem.entity.CourseSizeDetail;
import com.urbanfit.bem.entity.Store;
import com.urbanfit.bem.entity.dto.ResultDTOBuilder;
import com.urbanfit.bem.query.PageObject;
import com.urbanfit.bem.query.PageObjectUtil;
import com.urbanfit.bem.query.QueryInfo;
import com.urbanfit.bem.util.ArrayUtils;
import com.urbanfit.bem.util.DateUtils;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.StringUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("courseService")
@Transactional
public class CourseService {
    @Resource
    private CourseDao courseDao;
    @Resource
    private CourseStoreDao courseStoreDao;
    @Resource
    private CourseSizeDao courseSizeDao;
    @Resource
    private CourseSizeDetailDao courseSizeDetailDao;
    @Resource
    private StoreDao storeDao;

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

        if(course == null){
           return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "查询失败", "").toString();
        }

        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("course", JsonUtils.getJsonString4JavaPOJO(course, DateUtils.LONG_DATE_PATTERN));
        // 查询课程规格
        List<CourseSize> lstCourseSize = courseSizeDao.queryCourseSize(courseId);
        jo.put("lstCourseSize", CollectionUtils.isEmpty(lstCourseSize) ? "" : JsonUtils.
                getJsonString4JavaListDate(lstCourseSize, DateUtils.LONG_DATE_PATTERN));
        List<CourseSizeDetail> lstSizeDetail = courseSizeDetailDao.queryCourseSizeDetail(courseId);
        jo.put("lstSizeDetail", CollectionUtils.isEmpty(lstSizeDetail) ? "" :  JsonUtils.
                getJsonString4JavaListDate(lstSizeDetail, DateUtils.LONG_DATE_PATTERN));
        // 查询课程课程俱乐部
        List<Store> lstCourseStore = courseStoreDao.queryCourseStore(courseId);
        jo.put("lstCourseStore", CollectionUtils.isEmpty(lstCourseStore) ? "" : JsonUtils.
                getJsonString4JavaListDate(lstCourseStore, DateUtils.LONG_DATE_PATTERN));
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public String queryStoreCourseList(Integer storeId, QueryInfo queryInfo){
        if(storeId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageOffset", queryInfo.getPageOffset());
        map.put("pageSize", queryInfo.getPageSize());
        map.put("storeId", "," + storeId + ",");
        PageObjectUtil page = new PageObjectUtil();
        PageObject<Course> pager = page.savePageObject(courseDao.queryStoreCourseCount(map),
                courseDao.queryStoreCourseList(map), queryInfo);
        List<Course> lstCourse = pager.getDatas();
        JSONObject jo = new JSONObject();
        jo.put("totalRecord", pager.getTotalRecord());
        if(!CollectionUtils.isEmpty(lstCourse)){
            jo.put("lstCourse", JsonUtils.getJsonString4JavaListDate(lstCourse, DateUtils.LONG_DATE_PATTERN));
        }else{
            jo.put("lstCourse", "");
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public String queryCourseList(Integer courseType, String provice, String city, String district,
                                  QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageOffset", queryInfo.getPageOffset());
        map.put("pageSize", queryInfo.getPageSize());
        if(courseType != null){
            map.put("courseType", courseType);
        }
        if(!StringUtils.isEmpty(provice)){
            map.put("provice", provice);
        }
        if(!StringUtils.isEmpty(city)){
            map.put("city", city);
        }
        if(!StringUtils.isEmpty(district)){
            map.put("district", district);
        }
        PageObjectUtil page = new PageObjectUtil();
        PageObject<Course> pager = page.savePageObject(courseDao.queryCourseCount(map),
                courseDao.queryCourseList(map), queryInfo);
        List<Course> lstCourse = pager.getDatas();
        JSONObject jo = new JSONObject();
        jo.put("totalRecord", pager.getTotalRecord());
        if(!CollectionUtils.isEmpty(lstCourse)){
            jo.put("lstCourse", JsonUtils.getJsonString4JavaListDate(lstCourse, DateUtils.LONG_DATE_PATTERN));
        }else{
            jo.put("lstCourse", "");
        }
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public String queryCourseDetail(Integer courseId, Integer storeId, Integer detailId){
        if(courseId == null || storeId == null || detailId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 查询课程是否存在
        Course course = courseDao.queryCourseByCourseId(courseId);
        if(course == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程不存在", "").toString();
        }
        Store store = storeDao.queryStoreById(storeId);
        if(store == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "门店不存在，请重新选择", "").toString();
        }
        CourseSizeDetail courseSizeDetail = courseSizeDetailDao.queryCourseSizeDetailById(detailId);
        if(courseSizeDetail == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程规格不存在", "").toString();
        }
        if(courseSizeDetail.getIsSale() == CourseSizeDetail.IS_SALE_NO){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程规格不可售，请重新选择",
                    "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("courseId", courseId);
        map.put("lstSizeId", courseSizeDetail.getSizeDetail().split(","));
        List<CourseSize> lstCourseSize = courseSizeDao.queryCourseSizeInfo(map);
        if(CollectionUtils.isEmpty(lstCourseSize)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程规格不存在", "").toString();
        }
        List<String> lstSizeName = new ArrayList<String>();
        for (CourseSize courseSize : lstCourseSize){
            lstSizeName.add(courseSize.getSizeName());
        }
        JSONObject jo = new JSONObject();
        jo.put("course", JsonUtils.getJsonString4JavaPOJO(course, DateUtils.LONG_DATE_PATTERN));
        jo.put("store", JsonUtils.getJsonString4JavaPOJO(store, DateUtils.LONG_DATE_PATTERN));
        jo.put("courseSizeDetail", JsonUtils.getJsonString4JavaPOJO(courseSizeDetail, DateUtils.LONG_DATE_PATTERN));
        jo.put("sizeName", ArrayUtils.join(lstSizeName.toArray(), "-"));
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public PageObject<Course> queryWebCourseList(Integer courseType, String provice, String city,
                                              String district, QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageOffset", queryInfo.getPageOffset());
        map.put("pageSize", queryInfo.getPageSize());
        if(courseType != null){
            map.put("courseType", courseType);
        }
        if(!StringUtils.isEmpty(provice)){
            map.put("provice", provice);
        }
        if(!StringUtils.isEmpty(city)){
            map.put("city", city);
        }
        if(!StringUtils.isEmpty(district)){
            map.put("district", district);
        }
        PageObjectUtil page = new PageObjectUtil();
        return page.savePageObject(courseDao.queryCourseCount(map), courseDao.queryCourseList(map), queryInfo);
    }
}
