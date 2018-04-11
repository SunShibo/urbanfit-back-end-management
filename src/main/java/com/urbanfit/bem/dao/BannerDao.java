package com.urbanfit.bem.dao;

import com.urbanfit.bem.entity.Banner;

import java.util.List;

/**
 * Created by Administrator on 2018/4/1.
 */
public interface BannerDao {
    public List<Banner> queryBannerByType(Integer type);
}
