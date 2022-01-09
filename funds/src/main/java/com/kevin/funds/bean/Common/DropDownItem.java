package com.kevin.funds.bean.Common;

import lombok.Data;

@Data
public class DropDownItem {
	private String label;
	private String value;
	public DropDownItem(String label,String value){
		this.label=label;
		this.value=value;
	}
}
