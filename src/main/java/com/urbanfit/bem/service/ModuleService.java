package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.cfg.pop.SystemConfig;
import com.urbanfit.bem.dao.BannerDao;
import com.urbanfit.bem.dao.ModuleDao;
import com.urbanfit.bem.entity.Banner;
import com.urbanfit.bem.entity.Module;
import com.urbanfit.bem.util.DateUtils;
import com.urbanfit.bem.util.JsonUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Service("moduleService")
@Transactional
public class ModuleService {
    @Resource
    private ModuleDao moduleDao;
    @Resource
    private BannerDao bannerDao;

    public Module queryModuleList(Integer type){
        return moduleDao.queryModuleByType(type);
    }

    public String queryClientModuleList(Integer type){
        if(type == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        List<Banner> lstBanner = bannerDao.queryBannerByType(type);
        Module module = moduleDao.queryModuleByType(type);
        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("lstBanner", CollectionUtils.isEmpty(lstBanner) ? "" : JsonUtils.getJsonString4JavaListDate(
                lstBanner, DateUtils.LONG_DATE_PATTERN));
        jo.put("module", module == null ? "" : JsonUtils.getJsonString4JavaPOJO(module, DateUtils.LONG_DATE_PATTERN));

        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }
}
