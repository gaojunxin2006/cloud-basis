/**
 * Project Name:basis-getway
 * File Name:SwaggerResourceConfig.java
 * Package Name:org.basis.getway.config
 * Date:2020年2月25日下午2:35:06
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.gateway.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
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
@Component
@Primary
public class FluxSwaggerResourceProvider implements SwaggerResourcesProvider {

	public static final String API_URI = "/v2/api-docs";
	@Autowired
	private RouteLocator routeLocator;
	@Value("${spring.application.name}")
    private String self;

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		List<String> routes = new ArrayList<>();
		// 取出gateway的route
		routeLocator.getRoutes().subscribe(route -> {
			if (route.getUri().getHost() != null && !routes.contains(route.getUri().getHost())
					&& !self.equalsIgnoreCase(route.getUri().getHost())) {
				routes.add(route.getUri().getHost());
				resources.add(swaggerResource(route.getUri().getHost(),
						"/" + route.getUri().getHost().toLowerCase() + API_URI));
			}
		});
		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion("2.0");
		return swaggerResource;
	}
}

