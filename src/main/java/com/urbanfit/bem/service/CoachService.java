package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.dao.CoachDao;
import com.urbanfit.bem.entity.Coach;
import com.urbanfit.bem.query.PageObject;
import com.urbanfit.bem.query.PageObjectUtil;
import com.urbanfit.bem.query.QueryInfo;
import com.urbanfit.bem.util.DateUtils;
import com.urbanfit.bem.util.JsonUtils;
import net.sf.json.JSONObject;
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

    public String queryApiCoachList(QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageOffset", queryInfo.getPageOffset());
        map.put("pageSize", queryInfo.getPageSize());

        PageObjectUtil page = new PageObjectUtil<Coach>();
        PageObject<Coach> pager = page.savePageObject(coachDao.queryCoachCount(map), coachDao.
                queryCoachList(map), queryInfo);
        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("lstCoach", JsonUtils.getJsonString4JavaListDate(pager.getDatas(), DateUtils.LONG_DATE_PATTERN));
        jo.put("totalRecord", pager.getTotalRecord());
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }
}
