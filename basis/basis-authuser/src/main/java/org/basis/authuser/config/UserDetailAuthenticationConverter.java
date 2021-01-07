/**
 * Project Name:gmallplus-auth-server
 * File Name:UserDetailAuthenticationConverter.java
 * Package Name:com.gemii.gmallplus.auth.server.config
 * Date:2020年1月17日下午2:51:37
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

/**
 * ClassName:UserDetailAuthenticationConverter <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2020年1月17日 下午2:51:37 <br/>
 * @author   liuheqin
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class UserDetailAuthenticationConverter extends DefaultUserAuthenticationConverter{
	
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}

	@Override
	public void setDefaultAuthorities(String[] defaultAuthorities) {
		super.setDefaultAuthorities(defaultAuthorities);
	}

	@Override
	public Map<String, ?> convertUserAuthentication(Authentication authentication) {
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		// USERNAME不能边，否则资源服务器接收不到用户信息
		response.put(USERNAME, authentication.getPrincipal()); // TODO 去掉敏感信息 
		if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
			response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
		}
		return response;
	}

	@Override
	public Authentication extractAuthentication(Map<String, ?> map) {
		return super.extractAuthentication(map);
	}

}

