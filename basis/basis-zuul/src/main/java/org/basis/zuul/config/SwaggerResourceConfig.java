/**
 * Project Name:basis-getway
 * File Name:SwaggerResourceConfig.java
 * Package Name:org.basis.getway.config
 * Date:2020年2月25日下午2:35:06
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.zuul.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.spring4all.swagger.EnableSwagger2Doc;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;


/**
 * ClassName:SwaggerResourceConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年2月25日 下午2:35:06 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */
@Configuration
@EnableSwagger2Doc
@Primary
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

	@Autowired
	private RouteLocator routeLocator;

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
//	    resources.add(swaggerResource("default", "/v2/api-docs","1.0"));
	    List<Route> routes= routeLocator.getRoutes();
	    routes.forEach(route->{
	        resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "1.0"));
	    });
		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location, String version) {
	    SwaggerResource swaggerResource = new SwaggerResource();
	    swaggerResource.setName(name);
	    swaggerResource.setLocation(location);
	    swaggerResource.setSwaggerVersion(version);
	    return swaggerResource;
	}
}

