/**
 * Project Name:liz-common-utils
 * File Name:MyRequestBodyAdviceAdapter.java
 * Package Name:com.gemii.lizroom.utils.interceptor
 * Date:Oct 11, 20195:27:41 PM
 * Copyright (c) 2019, Gemii All Rights Reserved.
 *
*/

package org.basis.common.sdk.advice;

import java.lang.reflect.Type;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:MyRequestBodyAdviceAdapter <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Oct 11, 2019 5:27:41 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@ControllerAdvice
@Slf4j
public class MyRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		if (null != body) {
			log.debug("request body :{}", JSONObject.toJSONString(body));
		}
		return body;
	}

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

}