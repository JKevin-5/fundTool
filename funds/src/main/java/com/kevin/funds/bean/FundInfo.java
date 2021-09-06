package com.kevin.funds.bean;

import lombok.Data;

@Data
public class FundInfo {
    private String FundCode;            //基金代码
    private String FundName;            //基金名称
    private String FundType;            //基金类型
    private String FundInfo;            //基金持仓
}
