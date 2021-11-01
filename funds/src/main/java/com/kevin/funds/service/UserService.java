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

}
