package com.kevin.funds.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kevin.funds.bean.Fund;
import com.kevin.funds.mapper.FundMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
public class FundService {

    private static CloseableHttpClient httpClient;
    private static HttpGet httpGet;
    public static final String CONTENT_TYPE = "Content-Type";

    @Autowired
    FundMapper fundMapper;

    public static void  getFunds(){

        String Url="c";
        //System.out.println(sendGet());
    }


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
     * 查询所有基金基本信息
     * @para 发送链接 拼接参数  http://localhost:8090/order?a=1
     * @return
     * @throws IOException
     */
    public static void getFundsInfo() throws IOException{

    }
}
