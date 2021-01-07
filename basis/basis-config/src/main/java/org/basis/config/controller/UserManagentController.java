/**
 * Project Name:basis-authuser
 * File Name:UserManagentController.java
 * Package Name:org.basis.authuser.controller
 * Date:2020年2月25日上午11:16:34
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.config.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * ClassName:UserManagentController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2020年2月25日 上午11:16:34 <br/>
 * @author   liuheqin
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Api("用户管理API")
@RestController
public class UserManagentController {

	@ApiOperation("获取用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "header", name = "Authorization", dataType = "String", required = true, value = "Token", defaultValue = "bearer ") })
	@GetMapping(value = "/authsec/user/{_userId}")
	public Object userInfo(@ApiParam(value = "用户ID", required = true) @PathVariable(value = "_userId") String userId) {
		return "this is test user" + userId;
	}

}
