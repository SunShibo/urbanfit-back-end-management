package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.dao.StoreDao;
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
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }
}
