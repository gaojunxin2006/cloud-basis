package org.basis.authuser.config;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.parser.ParserConfig;

@Configuration
@EnableCaching
public class JedisConfig extends CachingConfigurerSupport {

	@Bean
	public KeyGenerator wiselyKeyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass()
						.getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		return RedisCacheManager.create(factory);
	}

	@Bean
	public RedisTemplate<Serializable, Object> redisTemplate(RedisConnectionFactory factory) {
		ParserConfig.getGlobalInstance().setAutoTypeSupport(true); 
		RedisTemplate<Serializable, Object> template = new RedisTemplate<Serializable, Object>();
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
		template.setKeySerializer(stringSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(fastJsonRedisSerializer);
		template.setValueSerializer(fastJsonRedisSerializer);
		template.setConnectionFactory(factory);
		return template;
	}

}