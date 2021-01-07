/**
 * Project Name:basis-authuser
 * File Name:MyErrorController.java
 * Package Name:org.basis.authuser.controller
 * Date:Apr 3, 20206:58:31 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.controller;

import javax.servlet.http.HttpServletRequest;

import org.basis.common.sdk.pojo.base.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:MyErrorController <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Apr 3, 2020 6:58:31 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@RestController
@RequestMapping(value = "/error", method = { RequestMethod.GET, RequestMethod.POST })
public class MyErrorController {

	@RequestMapping("info")
	public Message<Object> error(HttpServletRequest request) {
		Message<Object> result = Message.failure();
		return result;
	}
}
