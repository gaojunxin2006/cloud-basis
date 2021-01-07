/**
 * Project Name:basis-authuser
 * File Name:LoginUserService.java
 * Package Name:org.basis.authuser.service
 * Date:Apr 6, 202012:49:21 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.basis.authuser.constant.RedisKey;
import org.basis.authuser.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * ClassName:LoginUserService <br/>
 * Function: 已登录用户/企业管理 <br/>
 * Date: Apr 6, 2020 12:49:21 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@Service
@Slf4j
public class LoginUserService {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	private long expire = 1000 * 60 * 60;// 过期时间一个小时

	/**
	 * 获取已登录用户的企业corpId列表
	 * 
	 * @author youpan
	 * @return
	 */
	public List<String> getLoginCorpIds() {
		HashOperations<String, Object, Object> ops = stringRedisTemplate.opsForHash();
		String corpKey = RedisKey.LOGIN_CORPID_KEY_HASH;
		Set<Object> set = ops.keys(corpKey);
		List<String> loginCorpIds = new ArrayList<String>();
		set.stream()
				.forEach(x -> {
					String corpId = (String) x;
					loginCorpIds.add(corpId);
				});
		return loginCorpIds;
	}

	/**
	 * 保存已登录企业信息 ,后期可优化为zset
	 * 
	 * @author youpan
	 */
	public void setLoginInfo(User user) {
		String corpKey = RedisKey.LOGIN_CORPID_KEY_HASH;
		HashOperations<String, Object, Object> ops = stringRedisTemplate.opsForHash();
		Long exp = System.currentTimeMillis() + expire;
		ops.put(corpKey, user.getCorpId(), String.valueOf(exp));
	}

	/**
	 * 删除过期的企业信息
	 * 
	 * @author youpan
	 * @param id 用户ID
	 */
	public void deleteLogoutUserCorp() {
		String corpKey = RedisKey.LOGIN_CORPID_KEY_HASH;
		HashOperations<String, Object, Object> ops = stringRedisTemplate.opsForHash();
		Map<Object, Object> map = ops.entries(corpKey);
		log.info("corps:{}", JSONObject.toJSONString(map));
		long now = System.currentTimeMillis();
		map.entrySet()
				.forEach(x -> {
					String expObj = (String) map.get(x);
					if (StringUtils.isBlank(expObj)) {
						return;
					}
					Long exp = Long.valueOf(expObj);
					if (now >= exp) {
						ops.delete(corpKey, x);
						log.info("delete expired corp:{}", x);
					}
				});
	}

}
