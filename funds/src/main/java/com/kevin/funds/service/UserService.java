package com.kevin.funds.service;

import com.kevin.funds.bean.Common.ResponseResult;
import com.kevin.funds.bean.User.User;
import com.kevin.funds.mapper.UserMapper;
import com.kevin.funds.util.Encrypt.EncryptUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Map;

@Service
@Transactional
public class UserService {
	@Inject
	UserMapper userMapper;

	public User userLogin(User user) {

		User userDB = userMapper.getUserByUserName(user.getUserName());

		if (userDB != null && userDB.getUserPassWord().equals(EncryptUtil.encrypt(user.getUserPassWord()))) {
			return userDB;
		}

		throw new RuntimeException("认证失败");
	}
}
