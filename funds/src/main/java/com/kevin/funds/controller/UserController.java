package com.kevin.funds.controller;

import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.Possession;
import com.kevin.funds.bean.ResponseResult;
import com.kevin.funds.bean.StockIndex;
import com.kevin.funds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	//获取个人持仓信息
	@RequestMapping("/possession/{userId}")
	public List<Possession> getPersonalPossession(@PathVariable("userId") int userId) throws Exception{
		return userService.getProssessionByUserId(userId);
	};

	//新增个人持仓信息
	@RequestMapping(value ="/possession",produces = "application/json;charset=UTF-8")
	public ResponseResult addPersonalPossession(@RequestBody JSONObject jsonObject){
		Possession possession = JSONObject.toJavaObject(jsonObject, Possession.class);
		System.out.println(possession);
		return new ResponseResult(userService.addProssessionByUserId(possession)+"","插入成功",jsonObject);
	}
}
