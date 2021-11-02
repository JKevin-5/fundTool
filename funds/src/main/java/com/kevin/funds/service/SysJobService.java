package com.kevin.funds.service;

import com.alibaba.fastjson.JSONObject;
import com.kevin.funds.bean.Common.ResponseResult;
import com.kevin.funds.bean.Common.SysJob;
import com.kevin.funds.mapper.SysJobMapper;
import com.kevin.funds.util.CronTaskRegistrar;
import com.kevin.funds.util.SchedulingRunnable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysJobService {

	@Autowired
	private SysJobMapper sysJobMapper;

	@Autowired
	private CronTaskRegistrar cronTaskRegistrar;
	final int NORMAL=1;
	/*
	* add task
	* */
	@Transactional(rollbackFor = {Exception.class})
	public ResponseResult addSysJob(SysJob sysJob){
		boolean success = sysJobMapper.addSysJob(sysJob);
		JSONObject data = new JSONObject();
		if (!success)
			return new ResponseResult("500","创建任务失败",data);
		else {
			if (sysJob.getJobStatus().equals(NORMAL)) {
				SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
				cronTaskRegistrar.addCronTask(task, sysJob.getCronExpression());
			}
		}
		return new ResponseResult("200","创建任务成功",data);
	}

	/*
	* edit task
	* */
	public ResponseResult editSysJob(SysJob sysJob){
		JSONObject data = new JSONObject();
		SysJob existedSysJob=sysJobMapper.findSysJobByJobId(sysJob.getJobId());
		boolean success = sysJobMapper.editSysJob(sysJob);
		if (!success)
			return new ResponseResult("500","修改任务失败",data);
		else {
			//先移除再添加
			if (existedSysJob.getJobStatus().equals(NORMAL)) {
				SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
				cronTaskRegistrar.removeCronTask(task);
			}

			if (sysJob.getJobStatus().equals(NORMAL)) {
				SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
				cronTaskRegistrar.addCronTask(task, sysJob.getCronExpression());
			}
		}

		return new ResponseResult("200","修改任务成功",data);
	}

	/*
	* delete task
	* */
	public ResponseResult deleteSysJob(int jobId){
		JSONObject data = new JSONObject();
		SysJob existedSysJob=sysJobMapper.findSysJobByJobId(jobId);
		boolean success = sysJobMapper.deleteSysJobById(jobId);
		if (!success)
			return new ResponseResult("500","删除任务失败",data);
		else{
			if (existedSysJob.getJobStatus().equals(NORMAL)) {
				SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
				cronTaskRegistrar.removeCronTask(task);
			}
		}

		return new ResponseResult("200","删除任务成功",data);
	}
}
