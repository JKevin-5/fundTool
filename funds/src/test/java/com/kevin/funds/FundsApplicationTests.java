package com.kevin.funds;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kevin.funds.bean.Common.SysJob;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;

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
		//创建验证对象
		JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("ddssdd")).build();

		DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Mzc4NDcyMzksInVzZXJJZCI6MjEsInVzZXJuYW1lIjoieGlhb3FpYW5nIn0.MT0upR3q_d63y8uDCA2ge6woZkbrjtQeZzxPa7mLbuw");

		System.out.println(verify.getClaim("userId").asInt());
		System.out.println(verify.getClaim("username").asString());
		System.out.println("过期时间："+verify.getExpiresAt());

	}
}
