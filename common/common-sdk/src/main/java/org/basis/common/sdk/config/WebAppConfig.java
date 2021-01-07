/**
 * Project Name:common-sdk
 * File Name:WebAppConfig.java
 * Package Name:org.basis.common.sdk.config
 * Date:Mar 9, 20206:05:07 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.common.sdk.config;

import org.basis.common.sdk.interceptor.GlobalInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:WebAppConfig <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Mar 9, 2020 6:05:07 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
//@Configuration
@Slf4j
public class WebAppConfig {

	@Autowired
	private GlobalInterceptor globalInterceptor;

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			/**
			 * 添加拦截器
			 * 
			 * @param registry
			 */
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				log.info("add interceptor");
				registry.addInterceptor(globalInterceptor)
						.addPathPatterns("/**");
			}
		};
	}

}
