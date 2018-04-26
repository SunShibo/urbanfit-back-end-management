package com.urbanfit.bem.web.controller.api;

import com.urbanfit.bem.service.StoreService;
import com.urbanfit.bem.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/26.
 */
@Controller
@RequestMapping("/apiStore")
public class ApiStoreController extends BaseCotroller{
    @Resource(name = "storeService")
    private StoreService storeService;

    @RequestMapping(value = "/list")
    public void queryApiStoreList(HttpServletResponse response, String provice, String city, String district,
                                  Integer pageNo, Integer pageSize){

        String result = storeService.queryApiStoreList(provice, city, district, getQueryInfo(pageNo, pageSize));
        safeTextPrint(response, result);
    }
}
