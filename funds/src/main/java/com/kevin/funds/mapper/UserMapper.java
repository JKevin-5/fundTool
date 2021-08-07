package com.kevin.funds.mapper;

import com.kevin.funds.bean.Possession;
import com.kevin.funds.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface UserMapper {

	/*查找个人所有持有基金记录*/
	@Select("Select * from possession where userId=#{userId}")
	public List<Possession> getPersonalPossession(int userId);

	/*新增个人持有基金记录*/
	@Insert("Insert into possession values(#{userId},#{fundCode},#{fundOwn},#{fundCost},#{nowTarget})")
	public int addPossession(Possession possession);

	/*修改个人持仓基金记录 根据持仓用户id、基金代码*/
	@Update("update possession set fundOwn=#{fundOwn},fundCost=#{fundCost},nowTarget=#{nowTarget} where fundCode=#{fundCode},userId=#{userId}")
	public int alterPossession(Possession possession);

	/*删除个人持仓基金记录 根据持仓id*/
	@Delete("Delete from possession where possessionId=#{possessionId}")
	public int deletePossession(int possessionId);

	/*查询所有用户*/
	@Select("Select * from users")
	public List<User> getAllUsers();

	/*通过用户名查询用户*/
	@Select("Select * from users where userName=#{userName}")
	public List<User> getUserByUserName(String userName);

	/**/
}
