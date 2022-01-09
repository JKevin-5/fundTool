package com.kevin.funds.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Calendar;
import java.util.Map;

@Slf4j
public class JWTUtils {

	//设置盐
	private static final String SINGLE = "abcshitabc";

	/**
	 * 生成token
	 */
	public static String getToken(Map<String,String> map){

		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.HOUR,7);//默认20分钟过期

		//创建JWT builder
		JWTCreator.Builder builder = JWT.create();

		//payload 将参数健值进行操作
		map.forEach((k,v)->{
			builder.withClaim(k,v);
		});

		String token = builder.withExpiresAt(instance.getTime())//指定令牌过期时间
				.sign(Algorithm.HMAC256(SINGLE));//签名

		return token;
	}

	/**
	 * 验证token 合法性 并返回token内的信息
	 */
	public static DecodedJWT verify(String token){
//		log.info("token:"+token);
		return JWT.require(Algorithm.HMAC256(SINGLE)).build().verify(token);
	}

}
