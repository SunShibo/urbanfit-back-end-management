package com.urbanfit.bem.dao;

import com.urbanfit.bem.entity.Coach;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/8.
 */
public interface CoachDao{

    public int queryCoachCount(Map<String, Object> map);

    public List<Coach> queryCoachList(Map<String, Object> map);
}
