package com.kevin.funds.controller;

import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.Common.ResponseResult;
import com.kevin.funds.bean.User.Possession;
import com.kevin.funds.service.PossessionService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Api(tags = "个人持仓接口")
@RestController
@RequestMapping("/possessions")
public class PossessionController {

	@Inject
	PossessionService possessionService;

	/*
	* 查询个人持仓记录
	* */
	@GetMapping("/getPossessions/{userNo}")
	public ResponseResult getPossessions(@PathVariable("userNo") String userNo){
		return possessionService.findPossession(userNo);
	}

	/*
	 * 新增个人持仓记录
	 * */
	@PostMapping("/addPossession")
	public ResponseResult addPossession(@RequestBody Possession possession){
		return possessionService.addPossession(possession);
	}

}
