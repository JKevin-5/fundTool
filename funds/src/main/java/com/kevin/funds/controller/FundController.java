package com.kevin.funds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.Fund;
import com.kevin.funds.bean.FundInfo;
import com.kevin.funds.bean.ResponseResult;
import com.kevin.funds.mapper.FundMapper;
import com.kevin.funds.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.kevin.funds.service.FundService.sendGet;

@RestController
public class FundController {

    @Autowired
    FundMapper fundMapper;

    @Autowired
    FundService fundService;

    @RequestMapping("/funds")
    public List<Fund> getAllFunds(){return fundMapper.selectAll();}

    /**
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

    /**
    * 根据list查询所需的基金信息更新到基金信息表里
    * */
    @RequestMapping(value ="/getNowData",produces = "application/json;charset=UTF-8")
    public ResponseResult getNowData(@RequestBody JSONArray fundArray )throws Exception{

        List<String> list=  new ArrayList<>();
        //List<String> list=  new ArrayList<>(Arrays.asList("001102","161725","000596"));

        //值不为空
        if(fundArray != null){
            for(Object fund:fundArray){
                list.add((String) fund);
            }
        }

        //API请求头
        String url="https://api.doctorxiong.club/v1/fund?code=";

        //拼接参数url，获取str
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
                System.out.println("更新成功");
                return new ResponseResult("200","更新成功",jsonObject);
            }else{
                System.out.println("插入成功");
                return new ResponseResult("200","插入成功",jsonObject);
            }
        }
        return new ResponseResult("500","插入失败",jsonObject);
    }

    /**
     * 模糊搜索基金信息
     * 返回格式为 fundCode,fundName
     * */
    @RequestMapping("/findFunds/{info}")
    public ResponseResult findFundInfo(@PathVariable("info") String info){
        return fundService.findFundInfo(info);
    }

}
