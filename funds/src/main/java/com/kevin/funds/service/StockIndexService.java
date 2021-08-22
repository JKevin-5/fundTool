package com.kevin.funds.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.StockIndex;
import com.kevin.funds.mapper.StockIndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.SybaseAnywhereMaxValueIncrementer;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StockIndexService {
    @Autowired
    StockIndexMapper stockIndexMapper;

    public List<StockIndex> getStockIndex(String dateString) throws Exception{
//        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
//        Date date = fmt.parse(dateString);
        System.out.println(dateString);
        return stockIndexMapper.selectStockIndex(dateString);
    }

    public List<StockIndex> getStockIndex(){
        System.out.println("所有信息");
        return stockIndexMapper.selectAllStockIndex();
    }

    //新增大盘指数
    public int insertStockIndex() throws Exception{
        String str = FundService.sendGet("https://api.doctorxiong.club/v1/stock/board");
        JSONObject jsonObject= JSON.parseObject(str);
        if(jsonObject.get("code").equals(200)){
            JSONArray jsonArray=JSON.parseArray(jsonObject.get("data").toString());

            //循环插入
            for(int i=0;i<jsonArray.size();i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                StockIndex stockIndex=new StockIndex();

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                stockIndex.setCode(jsonObject1.get("code").toString());
                stockIndex.setName(jsonObject1.get("name").toString());
                stockIndex.setChangePercent(Double.parseDouble(jsonObject1.get("changePercent").toString()));
                stockIndex.setOpen(Double.parseDouble(jsonObject1.get("open").toString()));
                stockIndex.setClose(Double.parseDouble(jsonObject1.get("close").toString()));

                stockIndex.setDate(format.parse(jsonObject1.get("date").toString()));
                stockIndexMapper.insertStockIndex(stockIndex);
            }
            return 200;
        }else {
            return 500;
        }

    }

    //更新大盘指数


}
