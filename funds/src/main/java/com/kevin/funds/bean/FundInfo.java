package com.kevin.funds.bean;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FundInfo {
    private String fundCode;        //基金代码
    private String netWorth;        //当前基金单位净值
    private String expectWorth;     //当前基金单位估算净值
    private String expectGrowth;    //当前基金单位净值估算日涨幅,单位为百分比
    private String dayGrowth;       //单位净值日涨幅,单位为百分比
    private String lastWeekGrowth;  //单位净值周涨幅,单位为百分比
    private String netWorthDate;    //净值更新日期,日期格式为yy-MM-dd HH:mm.2019-06-27 15:00代表当天下午3点
    private String expectWorthDate; //净值估算更新日期,,日期格式为yy-MM-dd HH:mm.2019-06-27 15:00代表当天下午3点
}
