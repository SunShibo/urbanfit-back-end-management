package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.dao.ClientInfoDao;
import com.urbanfit.bem.entity.ClientInfo;
import com.urbanfit.bem.util.DateUtils;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.MD5Util;
import com.urbanfit.bem.util.StringUtils;
import com.urbanfit.bem.util.redisUtils.RedissonHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/9.
 */
@Service("clientInfoService")
@Transactional
public class ClientInfoService {
    @Resource
    private ClientInfoDao clientInfoDao;

    /**
     * 注册用户
     */
    public String register(String mobile, String password, String confirmPassword, String authCode){
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(confirmPassword)
                || StringUtils.isEmpty(authCode)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 判断手机号码是否注册过
        ClientInfo clientInfo = clientInfoDao.queryClientInfoByMobile(mobile);
        if(clientInfo != null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "手机号码已经存在", "").toString();
        }
        if(!password.equals(confirmPassword)){
            return JsonUtils.encapsulationJSON(2, "两次密码输入不正确", "").toString();
        }
        // 判断验证码输入是否正确
        String mobileAuthCode = RedissonHandler.getInstance().get(mobile + "_register");
        if(StringUtils.isEmpty(mobileAuthCode) || !mobileAuthCode.equals(authCode)){
            return JsonUtils.encapsulationJSON(3, "验证码有误", "").toString();
        }
        // 注册用户
        ClientInfo newClient = new ClientInfo();
        newClient.setMobile(mobile);
        newClient.setPassword(MD5Util.digest(password));
        clientInfoDao.addClientInfo(newClient);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "注册成功", "").toString();
    }

    public ClientInfo queryClientInfoByMobile(String mobile){
        return clientInfoDao.queryClientInfoByMobile(mobile);
    }

    public String updatePassword(Integer type, String mobile, String newPassword, String confirmPassword,
                                 String authCode){
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(confirmPassword)
                || type == null || StringUtils.isEmpty(authCode)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        ClientInfo clientInfo = clientInfoDao.queryClientInfoByMobile(mobile);
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "用户不存在", "").toString();
        }
        if(!newPassword.equals(confirmPassword)){
            return JsonUtils.encapsulationJSON(2, "两次密码输入不正确", "").toString();
        }
        // 判断验证码输入是否正确
        String mobileAuthCode = RedissonHandler.getInstance().get(mobile + "_reset");
        if(type == 2){
            mobileAuthCode = RedissonHandler.getInstance().get(mobile + "_update");
        }
        if(StringUtils.isEmpty(mobileAuthCode) || !mobileAuthCode.equals(authCode)){
            return JsonUtils.encapsulationJSON(3, "验证码有误", "").toString();
        }
        // 修改客户密码
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", mobile);
        map.put("password", MD5Util.digest(newPassword));
        clientInfoDao.updatePassword(map);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "修改密码成功", "").toString();
    }

    public String queryClientInfo(Integer clientId){
        if(clientId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        ClientInfo clientInfo = clientInfoDao.queryClientById(clientId);
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "用户不存在", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询信息成功", JsonUtils.
                getJsonString4JavaPOJO(clientInfo, DateUtils.LONG_DATE_PATTERN)).toString();
    }

    public String updateClientInfo(String name, Integer clientId) {
        if(StringUtils.isEmpty(name) || clientId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        ClientInfo clientInfo = clientInfoDao.queryClientById(clientId);
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "用户不存在", "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("clientId", clientId);
        clientInfoDao.updateClientInfo(map);
        clientInfo.setName(name);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "修改信息成功", JsonUtils.
                getJsonString4JavaPOJO(clientInfo, DateUtils.LONG_DATE_PATTERN)).toString();
    }

    public ClientInfo queryClientInfoByClientId(Integer clientId){
        return clientInfoDao.queryClientById(clientId);
    }

    public String updatePassword(Integer type, String mobile, String newPassword, String confirmPassword){
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(confirmPassword)
                || type == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        ClientInfo clientInfo = clientInfoDao.queryClientInfoByMobile(mobile);
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "用户不存在", "").toString();
        }
        if(!newPassword.equals(confirmPassword)){
            return JsonUtils.encapsulationJSON(2, "两次密码输入不正确", "").toString();
        }
        // 修改客户密码
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", mobile);
        map.put("password", MD5Util.digest(newPassword));
        clientInfoDao.updatePassword(map);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "修改密码成功", "").toString();
    }

    /**
     * 注册用户
     */
    public String register(String mobile, String password, String confirmPassword){
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(confirmPassword)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 判断手机号码是否注册过
        ClientInfo clientInfo = clientInfoDao.queryClientInfoByMobile(mobile);
        if(clientInfo != null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "手机号码已经存在", "").toString();
        }
        if(!password.equals(confirmPassword)){
            return JsonUtils.encapsulationJSON(2, "两次密码输入不正确", "").toString();
        }
        // 注册用户
        ClientInfo newClient = new ClientInfo();
        newClient.setMobile(mobile);
        newClient.setPassword(MD5Util.digest(password));
        clientInfoDao.addClientInfo(newClient);
        ClientInfo client = clientInfoDao.queryClientInfoByMobile(mobile);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "注册成功", JsonUtils.
                getJsonString4JavaPOJO(client, DateUtils.LONG_DATE_PATTERN)).toString();
    }
}
