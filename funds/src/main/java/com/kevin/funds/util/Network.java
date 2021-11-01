package com.kevin.funds.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Network {

	private static CloseableHttpClient httpClient;
	private static HttpGet httpGet;
	public static final String CONTENT_TYPE = "Content-Type";

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
		return resp;
	}
}
