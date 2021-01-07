/**
 * Project Name:basis-authuser
 * File Name:AuthUserDetailServiceImpl.java
 * Package Name:org.basis.authuser.service
 * Date:2020年2月27日下午5:13:43
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:AuthUserDetailServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2020年2月27日 下午5:13:43 <br/>
 * 
 * @author liuheqin
 * @version
 * @since JDK 1.8
 * @see
 */
@Service
@Slf4j
public class AuthUserDetailServiceImpl implements UserDetailsService, Serializable {
	@Autowired
	private UserAuthService userAuthService;
	private static final long serialVersionUID = 1977639577339193534L;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername :{}", username);
		return userAuthService.getWechatUser(username);
	}

}
