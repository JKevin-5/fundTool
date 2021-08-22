package com.kevin.funds.bean;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
public class StockIndex {
    private String code;
    private String name;
    private double changePercent;
    private double open;
    private double close;
    private Date date;
}
