package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.dao.ClientInfoDao;
import com.urbanfit.bem.entity.ClientInfo;
import com.urbanfit.bem.entity.bo.WechatBo;
import com.urbanfit.bem.util.*;
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

    public static final String APPID = "wxfceafb8ea3eae188";
    public static final String SECRET = "39f122b6b85706b0b0e43682e3a69841";

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

    public String updateClientInfo(ClientInfo client) {
        if(client == null || (client != null && client.getClientId() == null)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        ClientInfo clientInfo = clientInfoDao.queryClientById(client.getClientId());
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "用户不存在", "").toString();
        }
        clientInfo.setName(client.getName());
        clientInfo.setNickname(client.getNickname());
        clientInfo.setGender(client.getGender());
        clientInfo.setEmail(client.getEmail());
        clientInfo.setHeadPortrait(client.getHeadPortrait());
        clientInfoDao.updateClientInfo(clientInfo);
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

    public String wechatRegister(String code){
        if(StringUtils.isEmpty(code)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 获取access_token、openId信息
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID
                +"&secret=" + SECRET + "&code=" + code +"&grant_type=authorization_code";
        String result = HttpClientUtil.httpGetRequest(url);
        System.out.println("result：" + result);
        // 刷新access_token
        WechatBo wechatBo = (WechatBo)JsonUtils.getObject4JsonString(result, WechatBo.class);
        String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + APPID
                + "&grant_type=refresh_token&refresh_token=" + wechatBo.getRefresh_token();
        String accessTokenResult = HttpClientUtil.httpGetRequest(accessTokenUrl);
        System.out.println("accessTokenResult：" + accessTokenResult);
        // 获取登录用户信息
        WechatBo accessTokenWechatBo = (WechatBo)JsonUtils.getObject4JsonString(accessTokenResult, WechatBo.class);
        String wechatInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="
                +  accessTokenWechatBo.getAccess_token() + "&openid=" + accessTokenWechatBo.getOpenid()
                + "&lang=zh_CN";
        String wechatInfoResult = HttpClientUtil.httpGetRequest(wechatInfoUrl);
        try {
            wechatInfoResult = new String(wechatInfoResult.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("wechatInfoResult：" + wechatInfoResult);
            WechatBo wechatInfoBo = (WechatBo)JsonUtils.getObject4JsonString(wechatInfoResult, WechatBo.class);
            System.out.println(wechatInfoBo.getNickname());
            // 判断openId是否存在
            ClientInfo clientInfo = clientInfoDao.queryClientByOpenId(wechatInfoBo.getOpenid());
            if(clientInfo == null){
                clientInfo = new ClientInfo();
                clientInfo.setName(wechatInfoBo.getNickname());
                clientInfo.setNickname(wechatInfoBo.getNickname());
                clientInfo.setOpenId(wechatInfoBo.getOpenid());
                // 获取信息成功  添加用户
                clientInfoDao.addClientInfo(clientInfo);
            }else{
                clientInfo.setNickname(wechatInfoBo.getNickname());
                clientInfoDao.updateClient(clientInfo);
            }
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "登录成功", JsonUtils.
                    getJsonString4JavaPOJO(clientInfo, DateUtils.LONG_DATE_PATTERN)).toString();
        }catch (Exception e){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "登录失败", "").toString();
        }
    }

    public ClientInfo wechatClientWebRegister(String code){
        if(StringUtils.isEmpty(code)){
            return null;
        }
        // 获取access_token、openId信息
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID
                +"&secret=" + SECRET + "&code=" + code +"&grant_type=authorization_code";
        String result = HttpClientUtil.httpGetRequest(url);
        System.out.println("result：" + result);
        // 刷新access_token
        WechatBo wechatBo = (WechatBo)JsonUtils.getObject4JsonString(result, WechatBo.class);
        String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + APPID
                + "&grant_type=refresh_token&refresh_token=" + wechatBo.getRefresh_token();
        String accessTokenResult = HttpClientUtil.httpGetRequest(accessTokenUrl);
        System.out.println("accessTokenResult：" + accessTokenResult);
        // 获取登录用户信息
        WechatBo accessTokenWechatBo = (WechatBo)JsonUtils.getObject4JsonString(accessTokenResult, WechatBo.class);
        String wechatInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="
                +  accessTokenWechatBo.getAccess_token() + "&openid=" + accessTokenWechatBo.getOpenid()
                + "&lang=zh_CN";
        String wechatInfoResult = HttpClientUtil.httpGetRequest(wechatInfoUrl);
        try {
            wechatInfoResult = new String(wechatInfoResult.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println("wechatInfoResult：" + wechatInfoResult);
            WechatBo wechatInfoBo = (WechatBo)JsonUtils.getObject4JsonString(wechatInfoResult, WechatBo.class);
            System.out.println(wechatInfoBo.getNickname());
            // 判断openId是否存在
            ClientInfo clientInfo = clientInfoDao.queryClientByOpenId(wechatInfoBo.getOpenid());
            if(clientInfo == null){
                clientInfo = new ClientInfo();
                clientInfo.setName(wechatInfoBo.getNickname());
                clientInfo.setNickname(wechatInfoBo.getNickname());
                clientInfo.setOpenId(wechatInfoBo.getOpenid());
                // 获取信息成功  添加用户
                clientInfoDao.addClientInfo(clientInfo);
            }else{
                clientInfo.setNickname(wechatInfoBo.getNickname());
                clientInfoDao.updateClient(clientInfo);
            }
            return clientInfo;
        }catch (Exception e){
            return null;
        }
    }
}
