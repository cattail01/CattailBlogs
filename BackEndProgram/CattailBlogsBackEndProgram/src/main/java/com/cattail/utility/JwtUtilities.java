package com.cattail.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author Cattail
 * @version 1.0
 * @create 2023/8/30 23:14
 * @description 生成和校验 jwt 的工具类
 */
@Slf4j  // log日志文件输出，提供log.debug等方法
@Repository  // 提交到spring容器
@Data  // 自动 getter setter
@ConfigurationProperties(prefix = "markerhub.jwt")  // jwt配置参数
public class JwtUtilities {
	
	/**
	 * 正确token重要组成原件
	 */
	private String secret;
	
	/**
	 * 过期时间，单位：ms
	 */
	private long expire;
	
	private String header;
	
	/**
	 * 生成过期时间
	 *
	 * @param nowDate 当前时间
	 * @return 过期时间
	 */
	private Date createExpirationTime(Date nowDate) {
		return new Date(nowDate.getTime() + expire * 1000);
	}
	
	/**
	 * 生成token
	 * 给予jwt生成的相关信息
	 *
	 * @return token string
	 */
	public String generateToken(long userId) {
		Date nowDate = new Date();
		Date expirationDate = createExpirationTime(nowDate);
		
		return Jwts.builder()
		           .setHeaderParam("typ", "JWT")
		           .setSubject(Long.toString(userId))
		           .setIssuedAt(nowDate)
		           .setExpiration(expirationDate)
		           .signWith(SignatureAlgorithm.ES512, secret)
		           .compact()
				;
	}
	
	/**
	 * 校验token
	 * 尝试使用JwtParser进行token校验
	 * 比较输入token是否和和正确原件相同
	 * 如果不相同则log debug，并返回null
	 *
	 * @param token token
	 * @return Claims
	 */
	public Claims getClaimsByToken(String token) {
		try {
			return Jwts.parser()
			           .setSigningKey(secret)
			           .parseClaimsJws(token)
			           .getBody();
		}
		catch (Exception e) {
			log.debug("validate is token error", e);
			log.debug(e.getStackTrace()[0].toString());
			return null;
		}
	}
	
	/**
	 * 校验token是否过期
	 * 参数获得到期日期，然后使用当前日期与到期日期比较
	 * 返回过期日期在当前日期之前
	 *
	 * @param expirationDate 到期日期
	 * @return boolean，true表示过期
	 */
	private boolean isTokenExpired(Date expirationDate)
	{
		return expirationDate.before(new Date());
	}
}
