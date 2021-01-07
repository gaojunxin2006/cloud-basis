/**
 * Project Name:basis-authuser
 * File Name:MemberDetails.java
 * Package Name:org.basis.authuser.pojo.base
 * Date:Apr 3, 202011:38:36 AM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.pojo.base;

import java.util.Arrays;
import java.util.Collection;

import org.basis.authuser.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * ClassName:MemberDetails <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Apr 3, 2020 11:38:36 AM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
public class MemberDetails implements UserDetails {
	private static final long serialVersionUID = 1L;
	private User user;

	public MemberDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ADMIN"));
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return String.valueOf(user.getId());
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}

}
