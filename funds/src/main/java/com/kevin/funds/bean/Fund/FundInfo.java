package com.kevin.funds.bean.Fund;

import lombok.Data;
import java.util.Date;


@Data
public class FundInfo {
	private String fundCode;        //基金代码
	private String fundName;        //基金名称
	private String fundType;        //基金类型
	private String fundPossession;  //基金持仓
	private Date updateTime;      //基金更新时间
}

