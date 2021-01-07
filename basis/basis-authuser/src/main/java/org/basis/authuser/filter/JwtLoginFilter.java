/**
 * Project Name:basis-authuser
 * File Name:LoginFilter.java
 * Package Name:org.basis.authuser.filter
 * Date:Apr 3, 20209:58:26 AM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.basis.authuser.service.AuthUserDetailServiceImpl;
import org.basis.authuser.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:LoginFilter <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Apr 3, 2020 9:58:26 AM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@Component
@Slf4j
public class JwtLoginFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private AuthUserDetailServiceImpl authUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = request.getHeader("Authorization");
		if (StringUtils.isBlank(authorization)) {
			filterChain.doFilter(request, response);
			return;
		}
		String username = jwtTokenUtil.getUserNameFromToken(authorization);
		log.debug("checking username:{}", username);
		UserDetails userDetails = authUserDetailService.loadUserByUsername(username);
		log.debug("userDetails:{}", JSONObject.toJSONString(userDetails));
		if (userDetails != null && userDetails.getUsername() != null) {
			if (userDetails != null && jwtTokenUtil.validateToken(authorization, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				log.info("authenticated user:{}", username);
				SecurityContextHolder.getContext()
						.setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}

}
