package com.kevin.funds.mapper;

import com.kevin.funds.bean.Fund.FundHis;
import com.kevin.funds.bean.Fund.FundInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FundMapper {

    /*查找所有基金信息*/
    @Select("select * from funds")
    public List<FundInfo> selectAll();


    /*基金代码查找基金信息*/
    @Select("select * from funds where fundCode=#{fundCode}")
    public FundInfo findFundByCode(String fundCode);


    /*更新基金基础信息*/
    @Insert("<script> " +
            "insert into fundInfo(fundCode,fundName,fundType,updateTime) values " +
            "<foreach collection=\"fundInfos\" index=\"index\" item=\"fundInfo\" separator=\",\"> " +
            "(#{fundInfo.fundCode},#{fundInfo.fundName},#{fundInfo.fundType},#{fundInfo.updateTime})" +
            "</foreach> " +
            "ON DUPLICATE KEY UPDATE updateTime = VALUES(updateTime)" +
            "</script>")
    public int insertFundInfo(@Param("fundInfos") List<FundInfo> fundInfos);

    /*批量更新基金历史信息*/
//    @Insert("<script> " +
//            "insert into funds_his(fundCode,fundName,netWorth,expectWorth,dayGrowth,expectGrowth,lastWeekGrowth,lastMonthGrowth,thisYearGrowth,netWorthDate,expectWorthDate,netWorthData) values " +
//            "<foreach collection=\"fundHisList\" index=\"index\" item=\"fundHis\" separator=\",\"> " +
//            "(#{fundHis.fundCode},#{fundHis.fundName},#{fundHis.netWorth},#{fundHis.expectWorth},#{fundHis.dayGrowth},#{fundHis.expectGrowth},#{fundHis.lastWeekGrowth},#{fundHis.lastMonthGrowth},#{fundHis.thisYearGrowth},#{fundHis.netWorthDate},#{fundHis.expectWorthDate},#{fundHis.netWorthData})" +
//            "</foreach> " +
//            "ON DUPLICATE KEY UPDATE updateTime = NOW()" +
//            "</script>")
//    public int insertFundHis(@Param("fundHisList") List<FundHis> fundHisList);
    /*更新/新增基金历史信息*/
    @Insert("insert into funds_his(fundCode,fundName,netWorth,expectWorth,dayGrowth,expectGrowth,lastWeekGrowth,lastMonthGrowth,thisYearGrowth,netWorthDate,expectWorthDate,netWorthData) values " +
            "(#{fundHis.fundCode},#{fundHis.fundName},#{fundHis.netWorth},#{fundHis.expectWorth},#{fundHis.dayGrowth},#{fundHis.expectGrowth},#{fundHis.lastWeekGrowth},#{fundHis.lastMonthGrowth},#{fundHis.thisYearGrowth},#{fundHis.netWorthDate},#{fundHis.expectWorthDate},#{fundHis.netWorthData})" +
            "ON DUPLICATE KEY UPDATE updateTime = NOW()")
    public int insertFundHis(@Param("fundHis") FundHis fundHis);

    /*模糊查询基金基础信息*/
    @Select("select * from fundInfo where fundCode LIKE CONCAT('%',#{info},'%') or fundName LIKE CONCAT('%',#{info},'%') or fundType LIKE CONCAT('%',#{info},'%') ")
    public List<FundInfo> findFundInfo(String info);

    /*模糊查询基金历史数据*/
    @Select("select * from funds_his where fundCode LIKE CONCAT('%',#{info},'%') or fundName LIKE CONCAT('%',#{info},'%') ")
    public List<FundHis> findFundHis(String info);

    /*
    * 查询所有基金的名称 选择器用
    * */
    @Select("select fundName,fundCode from fundInfo where fundName LIKE CONCAT('%',#{info},'%') or fundCode =#{info} ")
    public List<FundInfo> fundList(String info);
}
