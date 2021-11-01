package com.kevin.funds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.Possession;
import com.kevin.funds.bean.Common.ResponseResult;
import com.kevin.funds.mapper.UserMapper;
import com.kevin.funds.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserMapper userMapper;

	@ApiOperation(value = "查询用户",notes = "查询用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName",value = "用户名",required = true),
			@ApiImplicitParam(name = "userPassword",value = "用户密码",required = true)
	})
	@GetMapping(value = "/checkAuth")
	public ResponseResult findUser(@RequestBody JSONObject jsonParam){
		JSONObject jsonObject= new JSONObject();
		if ((jsonParam.get("userName")=="")&&jsonParam.get("userPassword")==""){
			return new ResponseResult("400","账号密码不得为空",jsonObject);
		}
		if(jsonParam.get("userPassword").equals(userMapper.getUserByUserName(jsonParam.get("userName").toString()))){
			return new ResponseResult("200","登陆成功",jsonObject);
		}else{
			return new ResponseResult("400","账号或密码错误",jsonObject);
		}
	}


}
