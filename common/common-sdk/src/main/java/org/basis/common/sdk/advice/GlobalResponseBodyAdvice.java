/**
 * Project Name:common-sdk
 * File Name:GlobalResponseBodyAdvice.java
 * Package Name:org.basis.common.sdk.advice
 * Date:Mar 9, 20205:00:48 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.common.sdk.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:GlobalResponseBodyAdvice <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Mar 9, 2020 5:00:48 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@RestControllerAdvice
@Slf4j
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Autowired
    Tracer tracer;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
//        if (null != object) {
//            try {
//                String jsonStr = JSONObject.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
//                log.debug("response :{}", jsonStr);
//                String traceId = tracer.currentSpan().context().traceIdString();
//                JSONObject obj = JSONObject.parseObject(jsonStr);
//                obj.put("requestId", traceId);
//                return obj;
//            } catch (Exception e) {
//                log.error("增加traceId 失败:{}", e.getMessage());
//                return object;
//            }
//        }
        return object;
    }

}
