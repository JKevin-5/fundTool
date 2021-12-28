package com.kevin.funds;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kevin.funds.bean.Common.SysJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.ibatis.annotations.Param;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;

@Slf4j
@SpringBootTest
class FundsApplicationTests {

	@Test
	void contextLoads(){
		HashMap<String,Object> map = new HashMap<>();
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.SECOND,60);
		String token=JWT.create()
				.withHeader(map)
				.withClaim("userId",21)//payload
				.withClaim("username","xiaoqiang")
				.withExpiresAt(instance.getTime())//指定令牌过期时间
				.sign(Algorithm.HMAC256("ddssdd"));//签名
		//输出令牌
		System.out.println(token);
	}

	@Test
	public void test(){
//		//创建验证对象
//		JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("ddssdd")).build();
//
//		DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Mzc4NDcyMzksInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGlhb3FpYW5nIn0.MT0upR3q_d63y8uDCA2ge6woZkbrjtQeZzxPa7mLbuw");
//
//		System.out.println(verify.getClaim("userId").asInt());
//		System.out.println(verify.getClaim("username").asString());
//		System.out.println("过期时间："+verify.getExpiresAt());

	}

	/*
	* 使用Apache工具类实现SHA-256加密
	* @param str 加密后的报文
	* @return
	* */
	@Test
	public void encrypt(){
		MessageDigest messageDigest;
		String str="zhangsan";
		String encodeStr="";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte [] hash = messageDigest.digest(str.getBytes("UTF-8"));
			encodeStr = Hex.encodeHexString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("加密后："+encodeStr);
	}
}
