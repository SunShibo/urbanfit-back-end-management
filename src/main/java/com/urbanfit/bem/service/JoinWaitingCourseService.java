package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.dao.JoinWaitingCourseDao;
import com.urbanfit.bem.entity.JoinWaitingCourse;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/27.
 */
@Service("joinWaitingCourseService")
@Transactional
public class JoinWaitingCourseService {
    @Resource
    private JoinWaitingCourseDao joinWaitingCourseDao;

    public String addJoinWaitingCourse(Integer clientId, String clientName, String clientMobile){
        if(StringUtils.isEmpty(clientName) || StringUtils.isEmpty(clientMobile)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }

        JoinWaitingCourse joinWaitingCourse = new JoinWaitingCourse();
        joinWaitingCourse.setClientId(clientId);
        joinWaitingCourse.setClientName(clientName);
        joinWaitingCourse.setClientMobile(clientMobile);

        joinWaitingCourseDao.addJoinWaitingCourse(joinWaitingCourse);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "添加成功", "").toString();
    }
}
