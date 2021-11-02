package com.kevin.funds.util;

import org.springframework.stereotype.Component;

/*
* 添加定时任务例类
* */
@Component("demoTask")
public class DemoTask {
	public void taskWithParams(String params) {
		System.out.println("执行有参示例任务：" + params);
	}

	public void taskNoParams() {
		System.out.println("执行无参示例任务");
	}
}
