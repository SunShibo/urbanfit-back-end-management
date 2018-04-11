package com.urbanfit.bem.service;

import com.urbanfit.bem.dao.StoreDao;
import com.urbanfit.bem.entity.Store;
import com.urbanfit.bem.entity.dto.ResultDTOBuilder;
import com.urbanfit.bem.query.PageObject;
import com.urbanfit.bem.query.PageObjectUtil;
import com.urbanfit.bem.query.QueryInfo;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
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
            map.put("district", "district");
        }
        PageObjectUtil page = new PageObjectUtil<Store>();
        return page.savePageObject(storeDao.queryClientStoreCount(map), storeDao.queryClientStoreList(map),
                queryInfo);
    }
}
