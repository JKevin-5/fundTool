package com.kevin.funds.service;

import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.Common.ResponseResult;
import com.kevin.funds.bean.Fund.FundHis;
import com.kevin.funds.bean.User.Possession;
import com.kevin.funds.mapper.FundMapper;
import com.kevin.funds.mapper.PossessionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Slf4j
@Service
public class PossessionService {

	@Inject
	PossessionMapper possessionMapper;
	@Inject
	FundMapper fundMapper;

	/**
	 * 新增持仓信息
	 */

	public ResponseResult addPossession(Possession possession){
		log.info(possession.toString());
		//填充该基金的基础信息
		FundHis fundHis = new FundHis();
		List<FundHis> fundHisList =fundMapper.findFundHis(possession.getFundCode());
		if(fundHisList.size()==0)return new ResponseResult("500","新增持仓失败，无此基金信息，请检查后重新提交");
		fundHis=fundHisList.get(0);


		if(possession.getPossessionWorth()>0){

			// 持有成本 = 持有单价 x 持有份额
			double worth = Math.round(possession.getPossessionWorth()*possession.getPossessionNum());
			log.info("持有成本: "+(possession.getPossessionWorth()*possession.getPossessionNum()));
			double totalWorth = Math.round(fundHis.getNetWorth()*possession.getPossessionNum());
			log.info("市值: "+(fundHis.getNetWorth()*possession.getPossessionNum()));
			// 持有收益
			double earn = totalWorth - worth;
			// 持有收益率
			double earnPercent = Math.round((earn/worth)*100);
			log.info("持有收益率: "+(earn/worth));

			possession.setFundName(fundHis.getFundName());
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

	/**
	 * 查询持仓信息
	 */
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
