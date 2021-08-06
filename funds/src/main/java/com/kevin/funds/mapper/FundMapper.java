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

    @Select("select * from funds")
    public List<Fund> selectAll();

    @Select("select fundCode,fundName from funds")
    public List<Fund> selectFundCode();

    @Select("select * from funds where fundCode=#{fundCode}")
    public Fund findFundByCode(String fundCode);

    @Insert("insert into funds(fundCode,fundName,fundType) values(#{fundCode},#{fundName},#{fundType})")
    public int insertFundData(Fund fund);

    @Insert("insert into funds_info values(#{fundCode},#{netWorth},#{expectWorth},#{expectGrowth},#{dayGrowth},#{lastWeekGrowth},#{netWorthDate},#{expectWorthDate})")
    public int insertFundTodayData(FundInfo fundInfo);

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
