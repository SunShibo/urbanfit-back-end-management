package com.urbanfit.bem.service;

import com.urbanfit.bem.cfg.pop.Constant;
import com.urbanfit.bem.dao.ClientInfoDao;
import com.urbanfit.bem.dao.ClientMessageDao;
import com.urbanfit.bem.entity.ClientInfo;
import com.urbanfit.bem.entity.ClientMessage;
import com.urbanfit.bem.util.JsonUtils;
import com.urbanfit.bem.util.RandomUtils;
import com.urbanfit.bem.util.StringUtils;
import com.urbanfit.bem.util.message.SendMessageUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/9.
 */
@Service("clientMessageService")
@Transactional
public class ClientMessageService {
    @Resource
    private ClientMessageDao clientMessageDao;
    @Resource
    private ClientInfoDao clientInfoDao;

    /**
     * @param mobile  手机号码
     * @param type    发送类型  0：注册  1：重置密码
     */
    public String sendCodeForSignIn(String mobile, Integer type){
        if(StringUtils.isEmpty(mobile) || type == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 查询当前手机号码是否存在
        ClientInfo clientInfo = clientInfoDao.queryClientInfoByMobile(mobile);
        if(type == 0){   // 如果是注册，账号不存在
            if(clientInfo != null){
                return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "手机号码已经存在", "").toString();
            }
        }else if(type == 1){  // 如果重置密码，账号存在
            if(clientInfo == null){
                return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "手机号码不存在", "").toString();
            }
        }
        String number = RandomUtils.getRandomNumber(6);
        SendMessageUtil.sendSignInCodeMessage(mobile, number);
        ClientMessage clientMessage = new ClientMessage();
        if(type == 0){
            clientMessage.setType("注册验证码");
        }else if(type == 1){
            clientMessage.setType("重置密码验证码");
        }
        clientMessage.setContent("您好，您的验证码为" + number);
        clientMessage.setSendTime(new Date());
        clientMessage.setMobile(mobile);
        // 添加发送短信记录信息
        clientMessageDao.addClientMessage(clientMessage);
        JSONObject jo = new JSONObject();
        jo.put("messageCode", number);
        jo.put("mobile", mobile);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "发送短信验证码成功", jo.toString()).toString();
    }

}
