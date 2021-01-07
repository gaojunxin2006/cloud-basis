/**
 * Project Name:basis-authuser
 * File Name:UserManagentController.java
 * Package Name:org.basis.authuser.controller
 * Date:2020年2月25日上午11:16:34
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.controller;

import java.util.List;

import org.basis.authuser.pojo.User;
import org.basis.authuser.service.LoginUserService;
import org.basis.authuser.service.UserAuthService;
import org.basis.common.sdk.pojo.base.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ClassName:UserManagentController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年2月25日 上午11:16:34 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */
@Api(tags = "用户管理API")
@RestController
public class UserManagentController {
	@Autowired
	private UserAuthService userAuthService;
	@Autowired
	private LoginUserService loginUserService;

	@ApiOperation("获取指定用户信息")
	@GetMapping(value = "/usermgmt/user/{id}")
	public Message<User> userInfo(@PathVariable(value = "id") Long id) {
		User user = userAuthService.getWechatUser(String.valueOf(id))
				.getUser();
		return Message.sucess(user);
	}

	/**
	 * 获取已登录企业corpId列表
	 * 
	 * @author youpan
	 * @return
	 */
	@ApiOperation("获取已登录企业corpId列表")
	@GetMapping(value = "/usermgmt/loginCorps")
	public Message<List<String>> loginCorpIds() {
		List<String> corpIds = loginUserService.getLoginCorpIds();
		return Message.sucess(corpIds);
	}

}
