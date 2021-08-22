package com.kevin.funds.mapper;

import com.kevin.funds.bean.StockIndex;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface StockIndexMapper {

    //插入大盘指数
    @Insert("insert into StockIndex(code,name,changePercent,open,close,date) values(#{code},#{name},#{changePercent},#{open},#{close},#{date})")
    public int insertStockIndex(StockIndex stockIndex);

    //更新今日大盘指数
    @Update("update StockIndex set changePercent=#{changePercent},open=#{open},close=#{close},date=#{date} where substr(date)=substr(#{date})")
    public int updateStockIndex(StockIndex stockIndex);

    //查看指定时间的大盘指数
    @Select("select * from StockIndex where date=#{date}")
    public List<StockIndex> selectStockIndex(String date);

    //查看大盘指数
    @Select("SELECT * FROM StockIndex order by date DESC limit 5")
    public List<StockIndex> selectAllStockIndex();

}
