package com.kevin.funds.mapper;

import com.kevin.funds.bean.User.Possession;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PossessionMapper {


	//查找个人持仓信息
	@Select("select * from possession where userNo=#{userNo}")
	public List<Possession> findPossesionByUserNo(String userNo);

	//新增个人基金持仓
	@Insert("insert into possession (fundCode,userNo,possessionWorth,possessionNum,nowWorth,earnPercent,earn,totalWorth,sellHis,createTime,updateTime) " +
			"values (#{fundCode},#{userNo},#{possessionWorth},#{possessionNum},#{nowWorth},#{earnPercent},#{earn},#{totalWorth},#{sellHis},now(),now())")
	public boolean addPossession(Possession possession);

	//更新个人基金信息
	@Update("update possession set possessionWorth=#{possessionWorth},possessionNum=#{possessionNum},nowWorth=#{nowWorth},earnPercent=#{earnPercent},earn=#{earn},totalWorth=#{totalWorth},sellHis=#{sellHis},updateTime=now()" +
			"where fundCode=#{fundCode} and userNo=#{userNo}")
	public boolean updatePossession(Possession possession);

}
