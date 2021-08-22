package com.kevin.funds.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.Fund;
import com.kevin.funds.bean.ResponseResult;
import com.kevin.funds.mapper.FundMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class FundService {

    private static CloseableHttpClient httpClient;
    private static HttpGet httpGet;
    public static final String CONTENT_TYPE = "Content-Type";

    @Autowired
    private FundMapper fundMapper;

    /**
     * 发送get请求
     * @param url 发送链接
     * @return
     * @throws IOException
     */
    public static String sendGet(String url) throws IOException {
        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        String resp;
        try {
            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        //LoggerFactory.getLogger(getClass()).info(" resp:{}", resp);
        return resp;
    }

    /**
     * 模糊查询基金信息
     * @return
     */
    public ResponseResult findFundInfo(String info){
        System.out.println("--------"+info);
        JSONObject jsonObject = new JSONObject();

        try{
            List<Fund> fundList = fundMapper.findFunds(info);
            System.out.println("--------"+fundList);
            //查询有结果
            if(fundList.size()!=0){
                JSONArray jsonArray=new JSONArray();
                for(Fund fund:fundList){
                    JSONObject fundinfo = (JSONObject) JSONObject.toJSON(fund);
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
}
