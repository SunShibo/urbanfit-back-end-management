package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.dao.ActivityMessageDao;
import com.urbanfit.bem.entity.ActivityMessage;
import com.urbanfit.bem.query.PageObject;
import com.urbanfit.bem.query.PageObjectUtil;
import com.urbanfit.bem.query.QueryInfo;
import com.urbanfit.bem.util.DateUtils;
import com.urbanfit.bem.util.JsonUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyubo on 2018/3/15.
 */
@Service("activityMessageService")
@Transactional
public class ActivityMessageService {

    @Resource
    private ActivityMessageDao activityMessageDao;

    public PageObject<ActivityMessage> queryActivityMessageList(QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null) {
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        PageObjectUtil page = new PageObjectUtil<ActivityMessage>();
        return page.savePageObject(activityMessageDao.queryActivityMessageCount(map),
                activityMessageDao.queryActivityMessageList(map), queryInfo);
    }

    public ActivityMessage queryActivityMessageDetail(Integer messageId){
        return activityMessageDao.queryActivityMessageById(messageId);
    }

    public String queryClientActivityMessageList(QueryInfo queryInfo){
        if(queryInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageOffset", queryInfo.getPageOffset());
        map.put("pageSize", queryInfo.getPageSize());

        PageObjectUtil page = new PageObjectUtil<ActivityMessage>();
        PageObject<ActivityMessage> pager = page.savePageObject(activityMessageDao.queryActivityMessageCount(map),
                activityMessageDao.queryActivityMessageList(map), queryInfo);
        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("lstMessage", JsonUtils.getJsonString4JavaListDate(pager.getDatas(), DateUtils.LONG_DATE_PATTERN));
        jo.put("totalRecord", pager.getTotalRecord());
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public String queryClientActivityMessageDetail(Integer messageId){
        if(messageId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        ActivityMessage activityMessage = activityMessageDao.queryActivityMessageById(messageId);
        if(activityMessage == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "查询不到数据", "").toString();
        }

        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("activityMessage", JsonUtils.getJsonObject4JavaPOJO(activityMessage, DateUtils.LONG_DATE_PATTERN));
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }
}
