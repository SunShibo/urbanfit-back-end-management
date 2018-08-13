package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.dao.CourseStoreDao;
import com.urbanfit.bem.dao.StoreDao;
import com.urbanfit.bem.entity.Course;
import com.urbanfit.bem.entity.Store;
import com.urbanfit.bem.query.PageObject;
import com.urbanfit.bem.query.PageObjectUtil;
import com.urbanfit.bem.query.QueryInfo;
import com.urbanfit.bem.util.DateUtils;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.StringUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/28.
 */
@Service("storeService")
@Transactional
public class StoreService {
    @Resource
    private StoreDao storeDao;
    @Resource
    private CourseStoreDao courseStoreDao;

    public PageObject<Store> queryStoreList(String provice, String city, String district, QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageOffset", queryInfo.getPageOffset());
        map.put("pageSize", queryInfo.getPageSize());
        if(!StringUtils.isEmpty(provice)){
            map.put("provice", provice);
        }
        if(!StringUtils.isEmpty(city)){
            map.put("city", city);
        }
        if(!StringUtils.isEmpty(district)){
            map.put("district", district);
        }
        PageObjectUtil page = new PageObjectUtil<Store>();
        return page.savePageObject(storeDao.queryClientStoreCount(map), storeDao.queryClientStoreList(map),
                queryInfo);
    }

    public String queryApiStoreList(String provice, String city, String district, QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageOffset", queryInfo.getPageOffset());
        map.put("pageSize", queryInfo.getPageSize());
        if(!StringUtils.isEmpty(provice)){
            map.put("provice", provice);
        }
        if(!StringUtils.isEmpty(city)){
            map.put("city", city);
        }
        if(!StringUtils.isEmpty(district)){
            map.put("district", district);
        }
        PageObjectUtil page = new PageObjectUtil<Store>();
        PageObject<Store> pager = page.savePageObject(storeDao.queryClientStoreCount(map),
                storeDao.queryClientStoreList(map), queryInfo);
        List<Store> lstStore = pager.getDatas();
        JSONObject jo = new JSONObject();
        jo.put("totalRecord", pager.getTotalRecord());
        if(!CollectionUtils.isEmpty(lstStore)){
            jo.put("lstStore", JsonUtils.getJsonString4JavaListDate(lstStore, DateUtils.LONG_DATE_PATTERN));
        }else{
            jo.put("lstStore", "");
        }
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public Store queryStoreByStoreId(Integer storeId){
        return storeDao.queryStoreById(storeId);
    }

    public String queryStoreDetail(Integer storeId){
        if(storeId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Store store = storeDao.queryStoreById(storeId);
        if(store == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "门店不存在", "").toString();
        }
        JSONObject jo = new JSONObject();
        jo.put("store", JsonUtils.getJsonString4JavaPOJO(store, DateUtils.LONG_DATE_PATTERN));
        List<Course> lstCourse = courseStoreDao.queryCourseByStoreId(storeId);
        jo.put("lstCourse", JsonUtils.getJsonString4JavaListDate(lstCourse, DateUtils.LONG_DATE_PATTERN));
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }
}
