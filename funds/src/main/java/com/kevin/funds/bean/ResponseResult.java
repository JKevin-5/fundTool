package com.kevin.funds.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

@Data
public class ResponseResult {

	private String status;		//状态码
	private String err_massage;	//错误内容
	private JSONObject data;		//备用，需要传到前端的信息

	public ResponseResult(String status,String err_massage,JSONObject data){
		this.status=status;
		this.err_massage=err_massage;
		this.data=data;
	}
}
