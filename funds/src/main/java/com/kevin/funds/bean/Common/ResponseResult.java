package com.kevin.funds.bean.Common;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

@Data
public class ResponseResult {

	private String status;		//状态码
	private String msg;	//错误内容
	private JSONObject data;		//备用，需要传到前端的信息

	public ResponseResult(String status,String msg,JSONObject data){
		this.status=status;
		this.msg=msg;
		this.data=data;
	}
	public ResponseResult(String status,String msg){
		this.status=status;
		this.msg=msg;
	}
}
