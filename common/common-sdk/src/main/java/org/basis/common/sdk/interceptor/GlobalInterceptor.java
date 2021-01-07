/**
 * Project Name:common-sdk
 * File Name:GlobalInterceptor.java
 * Package Name:org.basis.common.sdk.interceptor
 * Date:Mar 9, 20205:50:37 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.common.sdk.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:GlobalInterceptor <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Mar 9, 2020 5:50:37 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@Slf4j
@Component
public class GlobalInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Enumeration<String> headers = request.getHeaderNames();
		StringBuilder sb = new StringBuilder();
		while (headers.hasMoreElements()) {
			String string = (String) headers.nextElement();
			String value = request.getHeader(string);
			sb.append(string + ":" + value + ",");
		}
		log.info("header:{}", sb.toString());
		log.info("请求基本信息：请求地址：{}\t请求参数:{}", request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap()));

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO 后续处理

		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
