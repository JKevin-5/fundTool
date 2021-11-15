package com.kevin.funds.bean.User;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class Possession {

    //固定值
    private int    id;            //持仓信息id
	private String fundCode;      //基金代码
    private String userNo;        //个人No

    private double possessionWorth;//持有成本
    private double possessionNum;  //持有份额
    private double nowWorth;       //最新净值
    private double earnPercent;	   //持有收益率
    private double earn;           //持有收益
    private double totalWorth;     //持有市值

    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
    private String sellHis;     //持仓历史
    private int sellOutFlag;    //清仓标识
}
