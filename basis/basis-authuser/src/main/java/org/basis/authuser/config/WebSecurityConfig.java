/**
 * Project Name:basis-authuser
 * File Name:WebSecurityConfig.java
 * Package Name:org.basis.authuser.config
 * Date:2020年2月27日下午5:01:32
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.basis.authuser.filter.JwtLoginFilter;
import org.basis.common.sdk.enumeration.ResultCodeEnum;
import org.basis.common.sdk.pojo.base.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:WebSecurityConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年2月27日 下午5:01:32 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */
@Configuration
@EnableWebSecurity
@Order(2)
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtLoginFilter jwtLoginFilter;
	@Autowired
	private UserDetailsService userDetailService;

	/**
	 * 配置用户签名服务 主要是user-details 机制，
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService)
				.passwordEncoder(passwordEncoder());
	}

	/**
	 * 用来构建 Filter 链
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/", "/swagger*/**", "/css/**", "/js/**", "/favicon.ico", "/webjars/**", "/csrf", "/v2/api-docs", "/error", "/auth/**",
						"/actuator/**", "/usermgmt/loginCorps")
				.antMatchers(HttpMethod.OPTIONS);
		super.configure(web);
	}

	/**
	 * 用来配置拦截保护的请求
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.csrf()
				.disable()
				.httpBasic()
				.and()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.and()
				.userDetailsService(userDetailService)
				.exceptionHandling()
				.accessDeniedHandler(new AccessDeniedHandlerImpl());
		http.headers()
				.cacheControl();
		http.addFilterBefore(jwtLoginFilter, UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling()
				.accessDeniedHandler(restfulAccessDeniedHandler())
				.authenticationEntryPoint(restAuthenticationEntryPoint());
	}

	/**
	 * 当未登录或者token失效访问接口时，自定义的返回结果
	 * 
	 * @author youpan
	 * @return
	 */
	public AuthenticationEntryPoint restAuthenticationEntryPoint() {
		return new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
					throws IOException, ServletException {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				response.getWriter()
						.println(JSONObject.toJSONString(Message.failure(ResultCodeEnum.UNAUTHORIZED)));
				response.getWriter()
						.flush();

			}
		};
	}

	/**
	 * 当访问接口没有权限时，自定义的返回结果
	 * 
	 * @author youpan
	 * @return
	 */
	public AccessDeniedHandler restfulAccessDeniedHandler() {
		return new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
					throws IOException, ServletException {
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				response.getWriter()
						.println(JSONObject.toJSONString(Message.failure()));
				response.getWriter()
						.flush();
			}
		};
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new PasswordEncoder() {

			public String encode(CharSequence rawPassword) {
				String encode = DigestUtils.md5DigestAsHex(rawPassword.toString()
						.getBytes());
				log.info("do encode rawPassword:" + rawPassword + "encode:" + encode);
				return encode;
			}

			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				// String encode =
				// DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
				log.info("do matches start!encode:" + encodedPassword);
				boolean res = encodedPassword.equals(rawPassword);
				return res;
			}
		};
	}

}
