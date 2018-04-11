package com.urbanfit.bem.service;

import com.urbanfit.bem.dao.BannerDao;
import com.urbanfit.bem.entity.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */
@Service("bannerService")
@Transactional
public class BannerService {
    @Resource
    private BannerDao bannerDao;

    public List<Banner> queryBannerList(Integer type){
        return bannerDao.queryBannerByType(type);
    }
}
