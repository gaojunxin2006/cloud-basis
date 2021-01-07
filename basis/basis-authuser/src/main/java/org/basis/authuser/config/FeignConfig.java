/**
 * Project Name:basis-authuser
 * File Name:FeignConfig.java
 * Package Name:org.basis.authuser.config
 * Date:Apr 6, 202011:29:44 AM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:FeignConfig <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Apr 6, 2020 11:29:44 AM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@Configuration
@Slf4j
public class FeignConfig {

    @Autowired
    private HttpServletRequest request;
    public static final String SOURCE_TYPE = "SOURCE_TYPE";
    public static final String SOURCE_VALUE = "IN";// 内部请求标志

    @Bean
    public RequestInterceptor requestInterceptor() {
        return (template) -> {
            try {
                String token = request.getHeader("Authorization");
                template.header(SOURCE_TYPE, SOURCE_VALUE);
                if (StringUtils.isNotBlank(token)) {
                    log.info("add Authorization header");
                    template.header("Authorization", token);
                }
            } catch (Exception e) {
                log.error("异步调用无法透传 Authorization header");
            }

        };

    }
}
