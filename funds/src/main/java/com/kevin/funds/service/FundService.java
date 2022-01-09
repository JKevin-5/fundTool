package com.kevin.funds.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.Common.DropDownItem;
import com.kevin.funds.bean.Common.ResponseResult;
import com.kevin.funds.bean.Fund.FundHis;
import com.kevin.funds.bean.Fund.FundInfo;
import com.kevin.funds.mapper.FundMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.kevin.funds.util.Network.sendGet;


@Service
public class FundService {

    @Inject
    private FundMapper fundMapper;


    /**
     * 模糊查询基金信息
     * @return
     */
    public ResponseResult findFundInfo(String info){
        JSONObject jsonObject = new JSONObject();

        try{
            List<FundInfo> fundInfoList = fundMapper.findFundInfo(info);
            //查询有结果
            if(fundInfoList.size()!=0){
                JSONArray jsonArray=new JSONArray();
                for(FundInfo fundInfo : fundInfoList){
                    JSONObject fundinfo = (JSONObject) JSONObject.toJSON(fundInfo);
                    jsonArray.add(fundinfo);
                }
                jsonObject.put("data",jsonArray);
                return new ResponseResult("200","查询成功",jsonObject);
            }else{
                return new ResponseResult("404","暂无数据",jsonObject);
            }
        } catch (Exception e){
            jsonObject.put("err_detial",e.toString());
            return new ResponseResult("500","系统出错",jsonObject);
        }

    }

    /**
     * 模糊查询基金历史信息
     * @return
     */
    public ResponseResult findFundHis(String info){
        JSONObject jsonObject = new JSONObject();
        List<FundHis> fundHisList = fundMapper.findFundHis(info);
        if(fundHisList.size()>0){
            JSONArray jsonArray=new JSONArray();
            for(FundHis fundHis : fundHisList){
                JSONObject fundHisObject = (JSONObject) JSONObject.toJSON(fundHis);
                jsonArray.add(fundHisObject);
            }
            jsonObject.put("data",jsonArray);
            return new ResponseResult("200","查询成功",jsonObject);
        }else{
            return new ResponseResult("404","暂无数据",jsonObject);
        }
    }

    /*
    * 模糊查询基金下拉列表
    * */
    public ResponseResult fundFundList(String info){
        JSONObject jsonObject = new JSONObject();
        List<FundInfo> fundList = fundMapper.fundList(info);
        List<DropDownItem> dropDownItems = fundList.stream()
                                            .map(e -> new DropDownItem(e.getFundName(),e.getFundCode()))
                                            .collect(Collectors.toList());
        if(dropDownItems.size()>0){
            JSONArray jsonArray=new JSONArray();
            for(DropDownItem dropDownItem : dropDownItems){
                JSONObject dropDownItemObject = (JSONObject) JSONObject.toJSON(dropDownItem);
                jsonArray.add(dropDownItemObject);
            }
            jsonObject.put("data",jsonArray);
            return new ResponseResult("200","查询成功",jsonObject);
        }else{
            return new ResponseResult("404","暂无数据",jsonObject);
        }
    }

    //更新基金基础信息
    public ResponseResult updateFundInfo() throws Exception{
        long start = System.currentTimeMillis();
        String str = sendGet("http://fund.eastmoney.com/js/fundcode_search.js");
        str=str.substring(9,str.length()-1);
        JSONArray jsonArray = JSON.parseArray(str);

        List<FundInfo> fundInfoList = new ArrayList<FundInfo>();
        for(int i=0;i<jsonArray.size();i++){
            FundInfo fundInfo =new FundInfo();
            JSONArray jsonArray1= JSON.parseArray(jsonArray.get(i).toString());
            fundInfo.setFundCode(jsonArray1.get(0).toString());
            fundInfo.setFundName(jsonArray1.get(2).toString());
            fundInfo.setFundType(jsonArray1.get(3).toString());
            fundInfo.setUpdateTime(new Date());
            fundInfoList.add(fundInfo);
        }

        int status = fundMapper.insertFundInfo(fundInfoList);
        long end = System.currentTimeMillis();

        JSONObject jsonObject= new JSONObject();
        jsonObject.put("size",jsonArray.size());
        jsonObject.put("timeSpend",(end - start)+"ms");
        if(status>0){
            return new ResponseResult("200","信息更新完毕",jsonObject);
        }else{
            return new ResponseResult("500","信息更新失败",jsonObject);
        }

    }

    //更新基金的历史数据
    public ResponseResult updateFundHis(String fundCode,String date) throws Exception{
        long start = System.currentTimeMillis();
        String str = sendGet("https://api.doctorxiong.club/v1/fund/detail?code="+fundCode+"&startDate="+date);
        JSONObject jsonObject= JSON.parseObject(str);

        JSONObject data = jsonObject.getJSONObject("data");
        FundHis fundHis = new FundHis();
        fundHis.setFundCode(data.getString("code"));
        fundHis.setFundName(data.getString("name"));
        fundHis.setNetWorth(Double.parseDouble(data.get("netWorth").toString()));
        fundHis.setExpectWorth(Double.parseDouble(data.get("expectWorth").toString()));
        fundHis.setDayGrowth(Double.parseDouble(data.get("dayGrowth").toString()));
        fundHis.setExpectGrowth(Double.parseDouble(data.get("expectGrowth").toString()));
        fundHis.setLastWeekGrowth(Double.parseDouble(data.get("lastWeekGrowth").toString()));
        fundHis.setLastMonthGrowth(Double.parseDouble(data.get("lastMonthGrowth").toString()));
        fundHis.setThisYearGrowth(Double.parseDouble(StringUtils.isNotEmpty(data.get("lastYearGrowth").toString())?data.get("lastYearGrowth").toString():"0"));

        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date indate1=sdf1.parse(data.get("expectWorthDate").toString());
        fundHis.setExpectWorthDate(indate1);

        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
        Date indate2=sdf2.parse(data.get("netWorthDate").toString());
        fundHis.setNetWorthDate(indate2);

        fundHis.setNetWorthData(data.get("netWorthData").toString());


        int status = fundMapper.insertFundHis(fundHis);
        long end = System.currentTimeMillis();

        JSONObject jsonObjectMessage= new JSONObject();
        jsonObjectMessage.put("timeSpend",(end - start)+"ms");
        if(status>0){
            return new ResponseResult("200","历史信息更新完毕",jsonObjectMessage);
        }else {
            return new ResponseResult("500","历史信息更新失败",jsonObjectMessage);
        }

    }
}
