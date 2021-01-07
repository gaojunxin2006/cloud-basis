/**
 * Project Name:basis-authuser
 * File Name:RedisService.java
 * Package Name:org.basis.authuser.service
 * Date:Apr 2, 20208:31:16 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * ClassName:RedisService <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Apr 2, 2020 8:31:16 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
@Service
public class RedisService {

	@Autowired
	private RedisTemplate<Serializable, Object> redisTemplate;

	// - - - - - - - - - - - - - - - - - - - - - 公共方法 - - - - - - - - - - - - -
	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#expire(java.lang.String,
	 *      long)
	 */

	public boolean expire(String key, long time) {
		return redisTemplate.expire(key, time, TimeUnit.SECONDS);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#getTime(java.lang.String)
	 */

	public long getTime(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#hasKey(java.lang.String)
	 */

	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#persist(java.lang.String)
	 */

	public boolean persist(String key) {
		return redisTemplate.boundValueOps(key)
				.persist();
	}

	// - - - - - - - - - - - - - - - - - - - - - String类型 - - - - - - - - - - -
	// - - - - - - - - -

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#get(java.lang.String)
	 */

	public Object get(String key) {
		return key == null ? null
				: redisTemplate.opsForValue()
						.get(key);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#set(java.lang.String,
	 *      java.lang.String)
	 */

	public void set(String key, Object value) {
		redisTemplate.opsForValue()
				.set(key, value);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#set(java.lang.String,
	 *      java.lang.String, long)
	 */

	public void set(String key, Object value, long time) {
		if (time > 0) {
			redisTemplate.opsForValue()
					.set(key, value, time, TimeUnit.SECONDS);
		} else {
			redisTemplate.opsForValue()
					.set(key, value);
		}
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#batchSet(java.util.Map)
	 */

	public void batchSet(Map<String, String> keyAndValue) {
		redisTemplate.opsForValue()
				.multiSet(keyAndValue);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#batchSetIfAbsent(java.util.Map)
	 */

	public void batchSetIfAbsent(Map<String, String> keyAndValue) {
		redisTemplate.opsForValue()
				.multiSetIfAbsent(keyAndValue);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#increment(java.lang.String,
	 *      long)
	 */

	public Long increment(String key, long number) {
		return redisTemplate.opsForValue()
				.increment(key, number);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#increment(java.lang.String,
	 *      double)
	 */

	public Double increment(String key, double number) {
		return redisTemplate.opsForValue()
				.increment(key, number);
	}

	// - - - - - - - - - - - - - - - - - - - - - set类型 - - - - - - - - - - - - -
	// - - - - - - -
	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#sSet(java.lang.String,
	 *      java.lang.String)
	 */

	public void sSet(String key, String value) {
		redisTemplate.opsForSet()
				.add(key, value);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#members(java.lang.String)
	 */

	public Set<Object> members(String key) {
		return redisTemplate.opsForSet()
				.members(key);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#randomMembers(java.lang.String,
	 *      long)
	 */

	public void randomMembers(String key, long count) {
		redisTemplate.opsForSet()
				.randomMembers(key, count);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#randomMember(java.lang.String)
	 */

	public Object randomMember(String key) {
		return redisTemplate.opsForSet()
				.randomMember(key);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#pop(java.lang.String)
	 */

	public Object pop(String key) {
		return redisTemplate.opsForSet()
				.pop("setValue");
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#size(java.lang.String)
	 */

	public long size(String key) {
		return redisTemplate.opsForSet()
				.size(key);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#sHasKey(java.lang.String,
	 *      java.lang.Object)
	 */

	public boolean sHasKey(String key, Object value) {
		return redisTemplate.opsForSet()
				.isMember(key, value);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#isMember(java.lang.String,
	 *      java.lang.Object)
	 */

	public boolean isMember(String key, Object obj) {
		return redisTemplate.opsForSet()
				.isMember(key, obj);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#move(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */

	public boolean move(String key, String value, String destKey) {
		return redisTemplate.opsForSet()
				.move(key, value, destKey);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#remove(java.lang.String,
	 *      java.lang.Object)
	 */

	public void remove(String key, Object... values) {
		redisTemplate.opsForSet()
				.remove(key, values);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#difference(java.lang.String,
	 *      java.lang.String)
	 */

	public Set<Object> difference(String key, String destKey) {
		return redisTemplate.opsForSet()
				.difference(key, destKey);
	}

	// - - - - - - - - - - - - - - - - - - - - - hash类型 - - - - - - - - - - - -
	// - - - - - - - -

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#add(java.lang.String,
	 *      java.util.Map)
	 */

	public void add(String key, Map<String, String> map) {
		redisTemplate.opsForHash()
				.putAll(key, map);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#getHashEntries(java.lang.String)
	 */

	public Map<Object, Object> getHashEntries(String key) {
		return redisTemplate.opsForHash()
				.entries(key);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#hashKey(java.lang.String,
	 *      java.lang.String)
	 */

	public boolean hashKey(String key, String hashKey) {
		return redisTemplate.opsForHash()
				.hasKey(key, hashKey);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#getMapString(java.lang.String,
	 *      java.lang.String)
	 */

	public String getMapString(String key, String key2) {
		return redisTemplate.opsForHash()
				.get("map1", "key1")
				.toString();
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#getMapInt(java.lang.String,
	 *      java.lang.String)
	 */

	public Integer getMapInt(String key, String key2) {
		return (Integer) redisTemplate.opsForHash()
				.get("map1", "key1");
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#popValue(java.lang.String)
	 */

	public String popValue(String key) {
		return redisTemplate.opsForSet()
				.pop(key)
				.toString();
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#delete(java.lang.String,
	 *      java.lang.String)
	 */

	public Long delete(String key, String... hashKeys) {
		return redisTemplate.opsForHash()
				.delete(key, (Object) hashKeys);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#increment(java.lang.String,
	 *      java.lang.String, long)
	 */

	public Long increment(String key, String hashKey, long number) {
		return redisTemplate.opsForHash()
				.increment(key, hashKey, number);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#increment(java.lang.String,
	 *      java.lang.String, java.lang.Double)
	 */

	public Double increment(String key, String hashKey, Double number) {
		return redisTemplate.opsForHash()
				.increment(key, hashKey, number);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#hashKeys(java.lang.String)
	 */

	public Set<Object> hashKeys(String key) {
		return redisTemplate.opsForHash()
				.keys(key);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#hashSize(java.lang.String)
	 */

	public Long hashSize(String key) {
		return redisTemplate.opsForHash()
				.size(key);
	}

	// - - - - - - - - - - - - - - - - - - - - - list类型 - - - - - - - - - - - -
	// - - - - - - - -

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#leftPush(java.lang.String,
	 *      java.lang.Object)
	 */

	public void leftPush(String key, Object value) {
		redisTemplate.opsForList()
				.leftPush(key, value);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#index(java.lang.String,
	 *      long)
	 */

	public Object index(String key, long index) {
		return redisTemplate.opsForList()
				.index("list", 1);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#range(java.lang.String,
	 *      long, long)
	 */

	public List<Object> range(String key, long start, long end) {
		return redisTemplate.opsForList()
				.range(key, start, end);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#leftPush(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */

	public void leftPush(String key, String pivot, String value) {
		redisTemplate.opsForList()
				.leftPush(key, pivot, value);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#leftPushAll(java.lang.String,
	 *      java.lang.Object)
	 */

	public void leftPushAll(String key, Object... values) {
		redisTemplate.opsForList()
				.leftPushAll(key, values);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#leftPushAll(java.lang.String,
	 *      java.lang.String)
	 */

	public void leftPushAll(String key, String value) {
		redisTemplate.opsForList()
				.rightPush(key, value);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#rightPushAll(java.lang.String,
	 *      java.lang.String)
	 */

	public void rightPushAll(String key, String... values) {
		redisTemplate.opsForList()
				.rightPushAll(key, (Object[]) values);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#rightPushAll(java.lang.String,
	 *      java.util.Collection)
	 */

	public void rightPushAll(String key, Collection<Object> values) {
		redisTemplate.opsForList()
				.rightPushAll(key, values);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#rightPushIfPresent(java.lang.String,
	 *      java.lang.Object)
	 */

	public void rightPushIfPresent(String key, Object value) {
		redisTemplate.opsForList()
				.rightPushIfPresent(key, value);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#listLength(java.lang.String)
	 */

	public long listLength(String key) {
		return redisTemplate.opsForList()
				.size(key);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#leftPop(java.lang.String)
	 */

	public void leftPop(String key) {
		redisTemplate.opsForList()
				.leftPop(key);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#leftPop(java.lang.String,
	 *      long, java.util.concurrent.TimeUnit)
	 */

	public void leftPop(String key, long timeout, TimeUnit unit) {
		redisTemplate.opsForList()
				.leftPop(key, timeout, unit);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#rightPop(java.lang.String)
	 */

	public void rightPop(String key) {
		redisTemplate.opsForList()
				.rightPop(key);
	}

	/**
	 * TODO --Optional Description.
	 * 
	 * @see org.qywechat.action.service.impl.RedisService#rightPop(java.lang.String,
	 *      long, java.util.concurrent.TimeUnit)
	 */

	public void rightPop(String key, long timeout, TimeUnit unit) {
		redisTemplate.opsForList()
				.rightPop(key, timeout, unit);
	}

	public Long delete(String key) {
		return redisTemplate.delete(key) ? 1l : 0;
	}
}
