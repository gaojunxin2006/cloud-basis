/**
 * Project Name:basis-getway
 * File Name:SwaggerResourceController.java
 * Package Name:org.basis.getway.controller
 * Date:2020年2月25日下午6:18:30
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.gateway.controller;

import java.util.List;

import org.basis.gateway.config.FluxSwaggerResourceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

/**
 * ClassName:SwaggerResourceController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年2月25日 下午6:18:30 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */

@RestController
@RequestMapping("/swagger-resources")
public class SwaggerResourceController {

	private FluxSwaggerResourceProvider swaggerResourceProvider;

	@Autowired
	public SwaggerResourceController(FluxSwaggerResourceProvider swaggerResourceProvider) {
		this.swaggerResourceProvider = swaggerResourceProvider;
	}

	@RequestMapping(value = "/configuration/security")
	public ResponseEntity<SecurityConfiguration> securityConfiguration() {
		return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
	}

	@RequestMapping(value = "/configuration/ui")
	public ResponseEntity<UiConfiguration> uiConfiguration() {
		return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
	}

	@RequestMapping
	public ResponseEntity<List<SwaggerResource>> swaggerResources() {
		return new ResponseEntity<>(swaggerResourceProvider.get(), HttpStatus.OK);
	}
}
