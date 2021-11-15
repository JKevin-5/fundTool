package com.kevin.funds.service;

import com.kevin.funds.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserService {
	@Inject
	UserMapper userMapper;


}
