package org.basis.authuser.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

/**
 * JwtToken生成的工具类 JWT token的格式：header.payload.signature header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"} payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781} signature的生成算法：
 * HMACSHA256(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 * https://github.com/shenzhuan/mallplus on 2018/4/26.
 */
@Component
@Slf4j
public class JwtTokenUtil {
	private static final String CLAIM_KEY_USERNAME = "sub";
	private static final String CLAIM_KEY_CREATED = "created";
	private String secret = "qywechat-secret";
	private Long expiration = 7200l;

	/**
	 * 根据负责生成JWT的token
	 */
	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims, Date expireDate) {
        return Jwts.builder().setClaims(claims).setExpiration(expireDate)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret).compact();
    }

	/**
	 * 从token中获取JWT中的负载
	 */
	private Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			log.error("jwt error info:{}", e.getMessage());
			log.error("JWT格式验证失败:{}", token);
		}
		return claims;
	}

	/**
	 * 生成token的过期时间
	 */
	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	/**
	 * 从token中获取登录用户名
	 */
	public String getUserNameFromToken(String token) {
		String username;
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	/**
	 * 验证token是否还有效
	 *
	 * @param token       客户端传入的token
	 * @param userDetails 从数据库中查询出来的用户信息
	 */
	public boolean validateToken(String token, UserDetails userDetails) {
		if (userDetails != null && StringUtils.isNotBlank(userDetails.getUsername())) {
			String username = getUserNameFromToken(token);
			return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		}
		return false;
	}

	/**
	 * 判断token是否已经失效
	 */
	public boolean isTokenExpired(String token) {
		Date expiredDate = getExpiredDateFromToken(token);
		return expiredDate.before(new Date());
	}

	/**
	 * 从token中获取过期时间
	 */
	public Date getExpiredDateFromToken(String token) {
		Claims claims = getClaimsFromToken(token);
		return claims.getExpiration();
	}

	/**
	 * 根据用户信息生成token
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, username);
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}
	
    public String generateToken(String username, Date expireDate) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims, expireDate);
    }
	/**
	 * 判断token是否可以被刷新
	 */
	public boolean canRefresh(String token) {
		return !isTokenExpired(token);
	}

	/**
	 * 刷新token
	 */
	public String refreshToken(String token) {
		Claims claims = getClaimsFromToken(token);
		claims.put(CLAIM_KEY_CREATED, new Date());
		return generateToken(claims);
	}
}
