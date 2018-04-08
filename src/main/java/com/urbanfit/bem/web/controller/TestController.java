package com.urbanfit.bem.web.controller;

import com.urbanfit.bem.dao.TestDAO;
import com.urbanfit.bem.entity.HostDO;
import com.urbanfit.bem.entity.dto.ResultDTOBuilder;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shibo Sun
 *         主机controller
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource
    private TestDAO testDAO;

    @RequestMapping("/test")
    public void addHost(HttpServletResponse response, HostDO hostDO) {
//        hostDAO.insertHost(hostDO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(testDAO.test()));
        super.safeJsonPrint(response, json);
    }

}
