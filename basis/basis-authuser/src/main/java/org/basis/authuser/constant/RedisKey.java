/**
 * Project Name:basis-authuser
 * File Name:RedisKey.java
 * Package Name:org.basis.authuser.constant
 * Date:Apr 2, 20208:27:37 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.constant;

/**
 * ClassName:RedisKey <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Apr 2, 2020 8:27:37 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
public class RedisKey {

	public static final String AUTH_CODE_KEY = "AUTH_CODE_KEY_%S";

	/**
	 * 已登录用户信息
	 */
	public static final String LOGIN_USER_KEY_ZSET = "LOGIN_USER_KEY_ZSET";

	/**
	 * 已登录企业信息
	 */
	public static final String LOGIN_CORPID_KEY_HASH = "LOGIN_CORPID_KEY_HASH";
	
   /**
     * 缓存用户信息
     */
    public static final String USER_CACHE_USER_ID = "USER_CACHE_USER_ID_%S";
}
