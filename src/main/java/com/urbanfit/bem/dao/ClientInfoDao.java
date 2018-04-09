package com.urbanfit.bem.dao;

import com.urbanfit.bem.entity.ClientInfo;

import java.util.Map;

/**
 * Created by Administrator on 2018/4/9.
 */
public interface ClientInfoDao {
    public void addClientInfo(ClientInfo clientInfo);

    public ClientInfo queryClientInfoByMobile(String mobile);

    public void updatePassword(Map<String, Object> map);

    public ClientInfo queryClientById(Integer clientId);

    public void updateClientInfo(Map<String, Object> map);
}
