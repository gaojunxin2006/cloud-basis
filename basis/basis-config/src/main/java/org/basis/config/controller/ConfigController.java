/**
 * Project Name:basis-config
 * File Name:ConfigController.java
 * Package Name:org.basis.config.controller
 * Date:Mar 3, 20204:36:01 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.config.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:ConfigController <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Mar 3, 2020 4:36:01 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@RestController
@RequestMapping("/config")
@RefreshScope
@Slf4j
public class ConfigController {
    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @GetMapping("/get")
	public Object get() throws UnknownHostException {
		log.info("useLocalCache:{},name:{},age:{}", useLocalCache);
		String msg = "my ip is :" + InetAddress.getLocalHost()
				.getHostAddress() + ",and -my config is :" + useLocalCache;
		return msg;
	}

}
