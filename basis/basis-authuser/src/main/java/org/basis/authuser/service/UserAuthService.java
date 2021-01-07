/**
 * Project Name:basis-authuser
 * File Name:UserAuthService.java
 * Package Name:org.basis.authuser.service
 * Date:Apr 2, 20208:24:00 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.service;
/**
 * ClassName:UserAuthService <br/>
 * Function: ADD FUNCTION. <br/>
 * Date:     Apr 2, 2020 8:24:00 PM <br/>
 * @author   youpan
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.basis.authuser.client.QyWechatUserClient;
import org.basis.authuser.constant.RedisKey;
import org.basis.authuser.pojo.User;
import org.basis.authuser.pojo.base.MemberDetails;
import org.basis.authuser.util.JwtTokenUtil;
import org.basis.common.sdk.enumeration.ResultCodeEnum;
import org.basis.common.sdk.pojo.base.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserAuthService {
    @Autowired
    private QyWechatUserClient qyWechatUserClient;
    @Autowired
    private RedisService redisService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private LoginUserService loginUserService;
    private final long dateExpire = 30;// 过期时间小于三十分钟

    /**
     * 企微web端扫码登录
     * 
     * @author youpan
     * @param authCode
     * @return
     */
    @SuppressWarnings("unchecked")
    public Message<JSONObject> qyWechatWebSsoLogin(String authCode, String corpId) {
        JSONObject json = new JSONObject();
        String key = String.format(RedisKey.AUTH_CODE_KEY, authCode);
        Object result = redisService.get(key);
        if (null != result) {
            return (Message<JSONObject>) result;
        }
        Message<User> msg = qyWechatUserClient.webLogin2(authCode, corpId);
        log.info("login user:{}", JSONObject.toJSONString(msg));
        Assert.isTrue(ResultCodeEnum.SUCCESS.getCode() == msg.getCode(), "登录失败");
        User user = msg.getData();
        if (null == user) {
            // 说明登录用户企业并未入驻
            return Message.failure("您的企业还未入驻!");
        }
        json.put("user", user);
        // 生成TOKEN
        String token = jwtTokenUtil.generateToken(String.valueOf(user.getId()));
        json.put("token", token);
        // 缓存15分钟,重复授权登录直接返回
        Message<JSONObject> resultMsg = Message.sucess(json);
        redisService.set(key, resultMsg, 900);
        // 保存登录信息
        loginUserService.setLoginInfo(user);
        return resultMsg;
    }

    /**
     * 
     * @author youpan
     * @param code
     * @return
     */
    @SuppressWarnings("unchecked")
    public Message<JSONObject> qyWechatMobileSsoLogin(String code, String corpId) {
        JSONObject json = new JSONObject();
        String key = String.format(RedisKey.AUTH_CODE_KEY, code);
        Object result = redisService.get(key);
        if (null != result) {
            return (Message<JSONObject>) result;
        }
        Message<User> msg = qyWechatUserClient.mobileLogin(code, corpId);
        log.info("login user:{}", JSONObject.toJSONString(msg));
        Assert.isTrue(ResultCodeEnum.SUCCESS.getCode() == msg.getCode(), "登录失败");
        User user = msg.getData();
        json.put("user", user);
        // 生成TOKEN
        String token = jwtTokenUtil.generateToken(String.valueOf(user.getId()));
        json.put("token", token);
        // 缓存15分钟,重复授权登录直接返回
        Message<JSONObject> resultMsg = Message.sucess(json);
        redisService.set(key, resultMsg, 900);
        // 保存登录信息
        loginUserService.setLoginInfo(user);
        return resultMsg;
    }

    /**
     * 
     * @author youpan
     * @param username 对于微信授权登录来说就是内部mysql主键id
     * @return
     */
    public MemberDetails getWechatUser(String username) {
        if (StringUtils.isBlank(username)) {
            log.error("当前用户不存在！");
            return null;
        }
        Assert.notNull(username, "当前用户不存在！");
        Long id = Long.valueOf(username);
        Message<User> userMsg = qyWechatUserClient.getUserById(id);
        if (null == userMsg || ResultCodeEnum.SUCCESS.getCode() != userMsg.getCode()) {
            log.error("获取用户信息失败，:{}", JSONObject.toJSONString(userMsg));
            return null;
        }
        return new MemberDetails(userMsg.getData());
    }

    /**
     * 获取当前用户
     * 
     * @author youpan
     * @return
     */
    public User currentUser() {
        String token = getToken();
        String username = jwtTokenUtil.getUserNameFromToken(token);
        if (StringUtils.isBlank(username)) {
            return null;
        }
        MemberDetails md = getWechatUser(username);
        if (null == md) {
            return null;
        }
        User user = md.getUser();
        return user;
    }

    @Async("asyncExecutor")
    public void setLoginUser() {
        User user = currentUser();
        loginUserService.setLoginInfo(user);
    }

    public static void main(String[] args) {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String userNameFromToken = jwtTokenUtil.getUserNameFromToken(
                "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjQ1MzM0MTMzMDQzMTc1NDI1IiwiY3JlYXRlZCI6MTU4ODE0MTYxMzU3NSwiZXhwIjoxNTg4MTQ4ODEzfQ.mJqxpBTu5V0W41Qc7ZLAWQYy1LRNefkx5O8id44SOeLkUs5ap0kco0XMR25dZ6zvtX-grZBZYeGOfthR6pMJ5Q");
        System.out.println(userNameFromToken);
    }

    /**
     * 获取 Authorization
     * 
     * @author youpan
     * @return
     */
    private String getToken() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String token = request.getHeader("Authorization");
        return token;
    }

    /**
     * 获取新TOKEN（旧TOKEN过期之前获取才有效）
     * 
     * @author youpan
     * @return
     */
    public Message<JSONObject> getNewToken() {
        String token = getToken();
        if (jwtTokenUtil.isTokenExpired(token)) {
            // token 过期
            return Message.failure("token已过期，请重新登录");
        }
        Date date = jwtTokenUtil.getExpiredDateFromToken(token);
        // 相差分钟数
        long mins = DateUtil.between(new Date(), date, DateUnit.MINUTE);
        JSONObject json = new JSONObject();
        if (mins <= dateExpire) {
            log.info("生成新的token");
            // 获取新token
            String username = jwtTokenUtil.getUserNameFromToken(token);
            token = jwtTokenUtil.generateToken(username);
            json.put("token", token);
        } else {
            json.put("token", token);
        }
        return Message.sucess(json);
    }
}
