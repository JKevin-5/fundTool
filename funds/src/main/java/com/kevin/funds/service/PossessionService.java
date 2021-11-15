package com.kevin.funds.service;

import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.Common.ResponseResult;
import com.kevin.funds.bean.Fund.FundHis;
import com.kevin.funds.bean.Fund.FundInfo;
import com.kevin.funds.bean.User.Possession;
import com.kevin.funds.mapper.FundMapper;
import com.kevin.funds.mapper.PossessionMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class PossessionService {

	@Inject
	PossessionMapper possessionMapper;
	@Inject
	FundMapper fundMapper;

	public ResponseResult addPossession(Possession possession){
		//填充该基金的基础信息
		FundHis fundHis = fundMapper.findFundHis(possession.getFundCode()).get(0);

		if(fundHis==null)return new ResponseResult("500","新增持仓失败，无此基金信息，请检查后重新提交");
		if(possession.getPossessionWorth()>0){
			double totalWorth=Math.round(fundHis.getNetWorth()*possession.getPossessionNum());
			double earn = totalWorth - possession.getPossessionWorth();
			double earnPercent = Math.round(earn/possession.getPossessionWorth());

			possession.setTotalWorth(totalWorth);
			possession.setEarn(earn);
			possession.setEarnPercent(earnPercent);

			boolean success=possessionMapper.addPossession(possession);
			if(!success){
				return new ResponseResult("500","新增持仓失败！");
			}else{
				return new ResponseResult("200","新增持仓成功！");
			}
		}

		return new ResponseResult("500","新增持仓失败，提交数据有误，请检查后重新提交");
	}

	public ResponseResult findPossession(String userNo){
		List<Possession> possessionList = possessionMapper.findPossesionByUserNo(userNo);
		if(possessionList.size()==0){
			return new ResponseResult("404","无持仓信息");
		}else{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("data",possessionList);
			return new ResponseResult("200","查找持仓信息成功！",jsonObject);
		}
	}


}
