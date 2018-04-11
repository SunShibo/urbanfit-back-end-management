package com.urbanfit.bem.dao;

import com.urbanfit.bem.entity.Module;

/**
 * Created by Administrator on 2018/4/2.
 */
public interface ModuleDao {
    public Module queryModuleByType(Integer type);
}
