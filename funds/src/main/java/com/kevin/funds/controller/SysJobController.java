package com.kevin.funds.controller;

import com.kevin.funds.bean.Common.ResponseResult;
import com.kevin.funds.bean.Common.SysJob;
import com.kevin.funds.mapper.SysJobMapper;
import com.kevin.funds.service.SysJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@Api(tags = "定时任务接口")
@RestController
@RequestMapping("/sysjobs")
public class SysJobController {

	@Inject
	SysJobService sysJobService;

	@ApiOperation(value = "新增任务",notes = "新增定时任务")
	@GetMapping("/addSysJob")
	public ResponseResult addSysJob(@RequestBody SysJob sysJob) {
		return sysJobService.addSysJob(sysJob);
	}

	@ApiOperation(value = "修改任务",notes = "修改定时任务")
	@GetMapping("/editSysJob")
	public ResponseResult editSysJob(@RequestBody SysJob sysJob) {
		return sysJobService.editSysJob(sysJob);
	}

	@ApiOperation(value = "删除任务",notes = "删除定时任务")
	@GetMapping("/deleteSysJob/{jobId}")
	public ResponseResult deleteSysJob(@PathVariable("jobId") int jobId) {
		return sysJobService.deleteSysJob(jobId);
	}

}
