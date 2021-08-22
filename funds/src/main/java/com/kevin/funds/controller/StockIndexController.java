package com.kevin.funds.controller;

import com.kevin.funds.bean.StockIndex;
import com.kevin.funds.mapper.StockIndexMapper;
import com.kevin.funds.service.StockIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class StockIndexController {

    @Autowired
    StockIndexService stockIndexService;

    /*
    *   获取指定日期的指数信息
    * */
    @RequestMapping("/stockIndex/{date}")
    public List<StockIndex> getStockIndex(@PathVariable("date") String date) throws Exception{

        return stockIndexService.getStockIndex(date);
    };


    @RequestMapping("/stockIndex")
    public List<StockIndex> getStockIndex(){
        return stockIndexService.getStockIndex();
    };

    @RequestMapping("/updateStockIndex")
    public int updateStockIndex() throws Exception{
        return stockIndexService.insertStockIndex();
    }
}
