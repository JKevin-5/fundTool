package com.kevin.funds.service;

import com.kevin.funds.bean.Possession;
import com.kevin.funds.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	/*
	* 获取所有持仓信息
	* */
	public List<Possession> getProssessionByUserId(int userId){
		return userMapper.getPersonalPossession(userId);
	}

	/*
	* 新增个人持仓信息
	* */
	public int addProssessionByUserId(Possession possession){
		return userMapper.addPossession(possession);
	}

}
