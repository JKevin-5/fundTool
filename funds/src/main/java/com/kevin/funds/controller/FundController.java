package com.kevin.funds.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.FundDaily;
import com.kevin.funds.bean.Common.ResponseResult;
import com.kevin.funds.mapper.FundMapper;
import com.kevin.funds.service.FundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.kevin.funds.util.Network.sendGet;


@Api(tags = "基金接口")
@RestController
@RequestMapping("/funds")
public class FundController {
    @Autowired
    FundMapper fundMapper;
    @Autowired
    FundService fundService;

    @ApiOperation(value = "更新基金",notes = "更新基金基础数据")
    @GetMapping("/updateFundInfo")
    public ResponseResult getFunds() throws Exception{
        return fundService.updateFundInfo();
    }

    @ApiOperation(value = "更新基金历史信息",notes = "更新基金历史信息数据")
    @GetMapping("/updateFundHis")
    public ResponseResult getFundHis(@RequestBody JSONObject jsonParam) throws Exception{
        return fundService.updateFundHis(jsonParam.get("fundCode").toString(),jsonParam.get("date").toString());
    }

    @ApiOperation(value = "查询基金信息",notes = "查询基金历史信息")
    @GetMapping("/findFundHis/{info}")
    public ResponseResult findFundHis(@PathVariable("info") String info){
        return fundService.findFundHis(info);
    }

    @ApiOperation(value = "查询基金信息",notes = "查询基金基础信息")
    @GetMapping("/findFunds/{info}")
    public ResponseResult findFundInfo(@PathVariable("info") String info){
        return fundService.findFundInfo(info);
    }



}
