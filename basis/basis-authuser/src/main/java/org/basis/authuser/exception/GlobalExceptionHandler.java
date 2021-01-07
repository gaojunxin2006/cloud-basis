/**
 * Project Name:liz-qywechat-client
 * File Name:GlobalExceptionHandler.java
 * Package Name:org.qywechat.client.exception
 * Date:Apr 3, 202011:29:28 AM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.exception;

import javax.servlet.http.HttpServletRequest;

import org.basis.common.sdk.enumeration.ResultCodeEnum;
import org.basis.common.sdk.pojo.base.Message;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:GlobalExceptionHandler <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Apr 3, 2020 11:29:28 AM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	@RequestMapping("/error")
	public Message<Object> defaultErrorHandler(HttpServletRequest request, Exception ex) {
		log.error("系统异常信息：{}", ex.toString(), ex);
		Message<Object> msg = Message.failure(ResultCodeEnum.SERVICE_EXCEPTION);
		msg.setMsg("服务器连接失败");
		return msg;
	}

}
