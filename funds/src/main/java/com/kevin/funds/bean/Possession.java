package com.kevin.funds.bean;

import lombok.Data;

@Data
public class Possession {
	private int    userId;		//用户id
    private String fundCode;    //基金编号
    private double fundOwn;     //持有份额
    private double fundCost;    //成本价
    private double nowTarget;   //止盈点
    private double nowRevence;	//当前盈利
}
