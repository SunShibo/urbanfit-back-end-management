package com.urbanfit.bem.dao;

import com.urbanfit.bem.entity.ActivityMessage;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyubo on 2018/3/15.
 */
public interface ActivityMessageDao {
    public int queryActivityMessageCount(Map<String, Object> map);

    public List<ActivityMessage> queryActivityMessageList(Map<String, Object> map);

    public ActivityMessage queryActivityMessageById(Integer messageId);
}
