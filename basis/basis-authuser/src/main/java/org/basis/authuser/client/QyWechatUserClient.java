/**
 * Project Name:basis-authuser
 * File Name:WechatUserClient.java
 * Package Name:org.basis.authuser.client
 * Date:Apr 2, 20208:09:44 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.client;

import org.basis.authuser.pojo.User;
import org.basis.common.sdk.pojo.base.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiOperation;

/**
 * ClassName:WechatUserClient <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Apr 2, 2020 8:09:44 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@FeignClient("liz-qywechat-client")
public interface QyWechatUserClient {

    /**
     * web端通过authcode获取用户信息
     * 
     * @author youpan
     * @param authCode
     * @return
     */
    @PostMapping("/user/login/web")
    public Message<User> webLogin(@RequestParam("authCode") String authCode);

    /**
     * web端通过authcode获取用户信息
     *
     * @author youpan
     * @param authCode
     * @return
     */
    @PostMapping("/user/login/web2")
    Message<User> webLogin2(@RequestParam("authCode") String authCode, @RequestParam(value = "corpId", required = false) String corpId);

    /**
     * 手机端登录
     * 
     * @author youpan
     * @param authCode
     * @return
     */
    @PostMapping("/user/login/mobile")
    public Message<User> mobileLogin(@RequestParam("code") String code, @RequestParam("corpId") String corpId);

    /**
     * 获取用户信息
     * 
     * @author youpan
     * @param corpId
     * @param userId
     * @return
     */
    @GetMapping("/user/info")
    public Message<User> getUser(@RequestParam("corpId") String corpId, @RequestParam("userId") String userId);

    /**
     * 获取用户信息
     * 
     * @author youpan
     * @param corpId
     * @param userId
     * @return
     */
    @GetMapping("/user/info/{id}")
    public Message<User> getUserById(@PathVariable("id") Long id);

    @ApiOperation("获取获取签名,config注入使用")
    @GetMapping(value = "/user/signature")
    public Message<JSONObject> getSignature(@RequestParam("url") String url);

    @ApiOperation("获取获取签名,agentConfig注入使用")
    @GetMapping(value = "/user/agentConfig/signature")
    public Message<JSONObject> getAgentConfigSignature(@RequestParam("url") String url);
}
