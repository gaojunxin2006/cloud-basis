/**
 * Project Name:basis-discovery
 * File Name:WebSecurityConfig.java
 * Package Name:org.basis.discovery.config
 * Date:2020年2月24日下午5:45:33
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.discovery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * ClassName:WebSecurityConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年2月24日 下午5:45:33 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().disable();
		http.csrf().disable();
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		http.formLogin().defaultSuccessUrl("/", true);
		http.logout().deleteCookies("JSESSIONID").logoutSuccessUrl("/login").permitAll();
	}

}
