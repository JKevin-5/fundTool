package com.kevin.funds.mapper;

import com.kevin.funds.bean.Fund;
import com.kevin.funds.bean.FundInfo;
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
    public List<Fund> selectAll();

    /*查找基金名称、基金代码*/
    @Select("select fundCode,fundName from funds")
    public List<Fund> selectFundCode();

    /*基金代码查找基金信息*/
    @Select("select * from funds where fundCode=#{fundCode}")
    public Fund findFundByCode(String fundCode);

    /*插入基金信息*/
    @Insert("insert into funds(fundCode,fundName,fundType) values(#{fundCode},#{fundName},#{fundType})")
    public int insertFundData(Fund fund);

    /*插入基金当日数据*/
    @Insert("insert into funds_info values(#{fundCode},#{netWorth},#{expectWorth},#{expectGrowth},#{dayGrowth},#{lastWeekGrowth},#{netWorthDate},#{expectWorthDate})")
    public int insertFundTodayData(FundInfo fundInfo);

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
    public int updateFundTodayData(FundInfo fundInfo);





}
