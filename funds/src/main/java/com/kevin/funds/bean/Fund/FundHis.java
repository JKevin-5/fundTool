package com.kevin.funds.bean.Fund;

import lombok.Data;

import java.util.Date;

@Data
public class FundHis {
	private String fundCode;
	private String fundName;
	private double netWorth;		//当前基金单位净值
	private double expectWorth;		//当前基金单位净值估算
	private double dayGrowth;		//日涨幅
	private double expectGrowth;	//当前基金单位净值估算日涨幅
	private double lastWeekGrowth;	//最近一周涨幅
	private double lastMonthGrowth;	//最近一个月涨幅
	private double thisYearGrowth;	//今年涨幅
	private Date   netWorthDate;		//净值更新日期
	private Date   expectWorthDate;	//净值估算更新日期
	private String netWorthData;	//历史净值信息
}
