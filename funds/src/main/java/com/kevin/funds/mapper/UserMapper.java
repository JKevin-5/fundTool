package com.kevin.funds.mapper;

import com.kevin.funds.bean.User.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface UserMapper {

	/*查询所有用户*/
	@Select("Select * from users")
	public List<User> getAllUsers();


	@Select("Select userPassword from users where userName=#{userName}")
	public String getUserByUserName(String userName);
}
