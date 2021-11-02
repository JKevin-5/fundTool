package com.kevin.funds.mapper;

import com.kevin.funds.bean.Common.SysJob;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysJobMapper {

	@Insert("insert into sysJobPO values(#{jobId},#{beanName},#{methodName},#{methodParams},#{cronExpression},#{remark},#{jobStatus},NOW(),NOW()) ")
	public boolean addSysJob(SysJob sysJob);

	@Select("select * from sysjobPO where jobId=#{jobId}")
	public SysJob findSysJobByJobId(int jobId);

	@Update("update sysJobPO set beanName=#{beanName},methodName=#{methodName},methodParams=#{methodParams},cronExpression=#{cronExpression},remark=#{remark},jobStatus=#{jobStatus},updateTime=NOW()")
	public boolean editSysJob(SysJob sysJob);

	@Delete("delete from sysJobPO where jobId=#{jobId}")
	public boolean deleteSysJobById(int jobId);
}
