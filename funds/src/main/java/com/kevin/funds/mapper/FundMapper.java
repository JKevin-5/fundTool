package com.kevin.funds.mapper;

import com.kevin.funds.bean.FundInfo;
import com.kevin.funds.bean.FundDaily;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FundMapper {

    /*查找所有基金信息*/
    @Select("select * from funds")
    public List<FundInfo> selectAll();

    /*查找基金名称、基金代码*/
    @Select("select * from funds where fundCode LIKE CONCAT('%',#{info},'%') or fundName LIKE CONCAT('%',#{info},'%') or fundType LIKE CONCAT('%',#{info},'%') ")
    public List<FundInfo> findFunds(String info);

    /*基金代码查找基金信息*/
    @Select("select * from funds where fundCode=#{fundCode}")
    public FundInfo findFundByCode(String fundCode);

    /*插入基金信息*/
    @Insert("insert into funds(fundCode,fundName,fundType) values(#{fundCode},#{fundName},#{fundType})")
    public int insertFundData(FundInfo fundInfo);

    /*插入基金当日数据*/
    @Insert("insert into funds_info values(#{fundCode},#{netWorth},#{expectWorth},#{expectGrowth},#{dayGrowth},#{lastWeekGrowth},#{netWorthDate},#{expectWorthDate})")
    public int insertFundTodayData(FundDaily fundDaily);

    /*更新基金当日数据*/
    @Update("update funds_info set " +
                "netWorth=#{netWorth}," +
                "expectWorth=#{expectWorth}," +
                "expectGrowth=#{expectGrowth}," +
                "dayGrowth=#{dayGrowth}," +
                "lastWeekGrowth=#{lastWeekGrowth}," +
                "netWorthDate=#{netWorthDate}," +
                "expectWorthDate=#{expectWorthDate}" +
            "where fundCode=#{fundCode}")
    public int updateFundTodayData(FundDaily fundDaily);





}
