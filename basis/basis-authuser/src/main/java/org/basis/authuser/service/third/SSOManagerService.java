/**
 * 
 */
package org.basis.authuser.service.third;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.basis.authuser.model.UserInfo;
import org.basis.common.sdk.pojo.base.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author youpan
 *
 */
@Component
@Slf4j
public class SSOManagerService {

    /**
     * 数据事件集发送策略集合.<br/>
     */
    private Map<String, SSOService> ssoStrategy = new HashMap<>();

    @Autowired
    public SSOManagerService(Map<String, SSOService> ssoStrategy) {
        log.info("do regist third sso service ");
        this.ssoStrategy.clear();
        log.info("third sso service :{}", JSONObject.toJSONString(ssoStrategy.keySet()));
        ssoStrategy.forEach((k, v) -> this.ssoStrategy.put(v.getState(), v));
    }

    /**
     * 第三方登录
     * 
     * @param code
     * @param state
     * @return
     * @author youpan
     */
    public Message<JSONObject> thirdLogin(String code, String state) {
        log.info("code:{},state:{}", code, state);
        if (StringUtils.isBlank(state)) {
            return Message.failure("当前state不正确");
        }
        SSOService ssoService = ssoStrategy.get(state);
        if (null == ssoService) {
            return Message.failure("没有对应第三方登录处理策略！");
        }
        String thirdAccessToken = ssoService.getAccessToken(code);
        if (StringUtils.isBlank(thirdAccessToken)) {
            return Message.failure("第三方登录失败！");
        }
        log.info("第三方登录成功，开始获取用户信息");
        UserInfo userInfo = ssoService.getUserInfo(thirdAccessToken);
        // TODO
        log.info("判断用户信息是否正确");
        if (StringUtils.isBlank(userInfo.getUserId()) || StringUtils.isBlank(userInfo.getCorpId())) {
            return Message.failure("用户信息不正确，无法绑定");
        }

        // TODO
        log.info("用户信息绑定企业微信");

        // TODO
        log.info("返回松果圈ACCESS_TOKEN");

        return Message.sucess();
    }
}
