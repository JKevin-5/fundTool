package com.kevin.funds.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.kevin.funds.bean.Common.ResponseResult;
import com.kevin.funds.bean.User.User;
import com.kevin.funds.mapper.UserMapper;
import com.kevin.funds.service.UserService;
import com.kevin.funds.util.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

	@Inject
	private UserService userService;


	@GetMapping(value = "/login")
	public Map<String,Object> login(User user){
		log.info("用户名：[{}]",user.getUserName());
		log.info("密码：[{}]",user.getUserPassWord());
		Map<String, Object> map = new HashMap<>();
		try{
			User userDB = userService.userLogin(user);
			Map<String,String> payload = new HashMap<>();
			payload.put("userNo",userDB.getUserNo());
			payload.put("userName",userDB.getUserName());
			//生成JWT的令牌
			String token=JWTUtils.getToken(payload);
			map.put("state",true);
			map.put("msg","认证成功");
			map.put("token",token);
			map.put("userNo",userDB.getUserNo());
		}catch (Exception e){
			map.put("state",false);
			map.put("msg",e.getMessage());
		}
		return map;
	}

	@PostMapping("/test")
	public Map<String,Object> test(HttpServletRequest request){
		Map<String,Object> map = new HashMap<>();
		//处理自己的业务逻辑
		String token = request.getHeader("token");
		DecodedJWT verify = JWTUtils.verify(token);
		log.info("用户no：[{}]",verify.getClaim("userNo").asString());
		log.info("用户名：[{}]",verify.getClaim("userName").asString());
		map.put("state",true);
		map.put("msg","请求成功！");
		return map;
	}


}
