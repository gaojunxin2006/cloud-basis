/**
 * Project Name:basis-zuul
 * File Name:SecurityConfig.java
 * Package Name:org.basis.zuul.config
 * Date:2020年3月2日下午2:17:42
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.zuul.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * ClassName:SecurityConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2020年3月2日 下午2:17:42 <br/>
 * @author   liuheqin
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@EnableOAuth2Sso
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("**/authsec/**").authenticated() // 只对需要授权的链接进行授权拦截
			.anyRequest().permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers("/", "/swagger*/**", "/css/**", "/js/**", "/favicon.ico", "/webjars/**", "/csrf",
				"**/v2/api-docs", "/error", "/login");
	}
}
