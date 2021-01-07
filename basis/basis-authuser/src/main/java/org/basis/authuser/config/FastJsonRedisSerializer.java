/**
 * Project Name:basis-authuser
 * File Name:FastJsonRedisSerializer.java
 * Package Name:org.basis.authuser.config
 * Date:Apr 3, 20205:18:59 PM
 * Copyright (c) 2020, Gemii All Rights Reserved.
 *
*/

package org.basis.authuser.config;

import java.nio.charset.Charset;

import org.apache.commons.lang.SerializationException;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * ClassName:FastJsonRedisSerializer <br/>
 * Function: ADD FUNCTION. <br/>
 * Date: Apr 3, 2020 5:18:59 PM <br/>
 * 
 * @author youpan
 * @version
 * @since JDK 1.8
 * @see
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	private Class<T> clazz;

	public FastJsonRedisSerializer(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	@Override
	public byte[] serialize(T t) throws SerializationException {
		if (null == t) {
			return new byte[0];
		}
		return JSON.toJSONString(t, SerializerFeature.WriteClassName)
				.getBytes(DEFAULT_CHARSET);
	}

	@Override
	public T deserialize(byte[] bytes) throws SerializationException {
		if (null == bytes || bytes.length <= 0) {
			return null;
		}
		String str = new String(bytes, DEFAULT_CHARSET);
		return (T) JSON.parseObject(str, clazz);
	}

}
