package com.urbanfit.bem.service;

import com.urbanfit.bem.dao.CoachDao;
import com.urbanfit.bem.entity.Coach;
import com.urbanfit.bem.query.PageObject;
import com.urbanfit.bem.query.PageObjectUtil;
import com.urbanfit.bem.query.QueryInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/8.
 */
@Service("coachService")
public class CoachService {
    @Resource
    private CoachDao coachDao;

    public PageObject<Coach> queryCoachList(QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }

        PageObjectUtil<Coach> page = new PageObjectUtil<Coach>();
        return page.savePageObject(coachDao.queryCoachCount(map), coachDao.queryCoachList(map), queryInfo);
    }
}
