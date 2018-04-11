package com.urbanfit.bem.dao;

import com.urbanfit.bem.entity.Store;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/28.
 */
public interface StoreDao {

    public int queryClientStoreCount(Map<String, Object> map);

    public List<Store> queryClientStoreList(Map<String, Object> map);
}
