package com.kevin.funds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.Fund;
import com.kevin.funds.bean.FundInfo;
import com.kevin.funds.mapper.FundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.kevin.funds.service.FundService.sendGet;

@RestController
public class FundController {

    @Autowired
    FundMapper fundMapper;

    @RequestMapping("/funds")
    public List<Fund> getAllFunds(){return fundMapper.selectAll();}

    /*
    * 插入所有的基金信息到基金信息表中
    * */
    @RequestMapping("/getAllFunds")
    public String getFunds() throws Exception{
        String str = sendGet("http://fund.eastmoney.com/js/fundcode_search.js");

        str=str.substring(9,str.length()-1);
        JSONArray jsonArray = JSON.parseArray(str);
        for(int i=0;i<jsonArray.size();i++){
            Fund fund =new Fund();
            JSONArray jsonArray1= JSON.parseArray(jsonArray.get(i).toString());
            fund.setFundCode(jsonArray1.get(0).toString());
            fund.setFundName(jsonArray1.get(2).toString());
            fund.setFundType(jsonArray1.get(3).toString());
            fundMapper.insertFundData(fund);
        }
        return "插入完毕！";
    }

    /*
    * 根据list查询所需的基金信息更新到基金信息表里
    * */
    @RequestMapping("/getNowData")
    public String getNowData() throws Exception{
        List<String> list=  new ArrayList<>(Arrays.asList("001102","161725","000596"));
        String url="https://api.doctorxiong.club/v1/fund?code=";
        for(int i=0;i<list.size();i++)
            url=url+list.get(i)+",";
        url=url.substring(0,url.length()-1);
        String str = sendGet(url);

        JSONObject jsonObject=JSON.parseObject(str);

        int updateFlag=0;
        if(jsonObject.get("code").equals(200)){
            JSONArray jsonArray=JSON.parseArray(jsonObject.get("data").toString());
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);

                FundInfo fundInfo=new FundInfo();
                fundInfo.setFundCode(jsonObject1.get("code").toString());
                fundInfo.setNetWorth(jsonObject1.get("netWorth").toString());
                fundInfo.setExpectWorth(jsonObject1.get("expectWorth").toString());
                fundInfo.setExpectGrowth(jsonObject1.get("expectGrowth").toString());
                fundInfo.setDayGrowth(jsonObject1.get("dayGrowth").toString());
                fundInfo.setLastWeekGrowth(jsonObject1.get("lastWeekGrowth").toString());
                fundInfo.setNetWorthDate(jsonObject1.get("netWorthDate").toString());
                fundInfo.setExpectWorthDate(jsonObject1.get("expectWorthDate").toString());

                if(fundMapper.findFundByCode(fundInfo.getFundCode())==null){
                    fundMapper.insertFundTodayData(fundInfo);
                }else{
                    fundMapper.updateFundTodayData(fundInfo);
                    updateFlag=1;
                }
            }
            if(updateFlag==1){
                return "更新成功";
            }else{
                return "插入成功";
            }
        }
        return "插入失败";
    }


    /*
     * 登记个人持有基金份额、成本单位净值
     * */
    @RequestMapping("/setPensolPossessions")
    public String setPossessions(){

        return "更新完成";
    }

}
