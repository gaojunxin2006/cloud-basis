/**
 * Project Name:basis-authuser
 * File Name:UserAuthController.java
 * Package Name:org.basis.authuser.controller
 * Date:2020年2月27日下午3:46:32
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.controller;

import org.basis.authuser.client.QyWechatUserClient;
import org.basis.authuser.pojo.User;
import org.basis.authuser.service.UserAuthService;
import org.basis.authuser.service.third.SSOManagerService;
import org.basis.common.sdk.pojo.base.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.codec.Base64;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ClassName:UserAuthController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年2月27日 下午3:46:32 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */
@Api(tags = "用户授权登录管理")
@RestController
@RequestMapping(value = "/auth/")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private QyWechatUserClient qyWechatUserClient;
    @Autowired
    private SSOManagerService ssoManagerService;

    @ApiOperation("获取当前用户信息")
    @GetMapping(value = "/userInfo")
    public Message<User> userinfo() {
        User user = userAuthService.currentUser();
        return Message.sucess(user);
    }

    @ApiOperation("增加当前登录时长")
    @GetMapping(value = "/addLoginTime")
    public Message<Void> addLoginTime() {
        userAuthService.setLoginUser();
        return Message.sucess();
    }

    /**
     * 企业微信扫码登录
     * 
     * @author youpan
     * @param authCode
     * @return
     */
    @ApiOperation("企业微信扫码登录")
    @GetMapping(value = "/web/qywechat/sso")
    public Message<JSONObject> qyWechatWebSsoLogin(String authCode, String corpId) {
        Assert.notNull(authCode, "authcode不能为null！");
        return userAuthService.qyWechatWebSsoLogin(authCode, corpId);
    }

    /**
     * 移动端企业微信登录
     * 
     * @author youpan
     * @param code
     * @return
     */
    @ApiOperation("移动端企业微信登录")
    @GetMapping(value = "/mobile/qywechat/sso")
    public Message<JSONObject> qyWechatMobileSsoLogin(String code, String corpId) {
        Assert.notNull(code, "code不能为null！");
        return userAuthService.qyWechatMobileSsoLogin(code, corpId);
    }

    @ApiOperation("获取新TOKEN")
    @GetMapping(value = "/token/new")
    public Message<JSONObject> getNewToken() {
        return userAuthService.getNewToken();
    }

    @ApiOperation("获取获取签名,config注入使用")
    @GetMapping(value = "/user/signature")
    public Message<JSONObject> getSignature(String url) {
        return qyWechatUserClient.getSignature(Base64.encode(url));
    }

    @ApiOperation("获取获取签名,agentConfig注入使用")
    @GetMapping(value = "/user/agentConfig/signature")
    public Message<JSONObject> getAgentConfigSignature(String url) {
        return qyWechatUserClient.getAgentConfigSignature(Base64.encode(url));
    }

    @ApiOperation("第三方登录接口")
    @GetMapping(value = "/third/sso")
    public Message<JSONObject> getAgentConfigSignature(String code, String state) {
        return ssoManagerService.thirdLogin(code, state);
    }
}
