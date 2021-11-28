package com.kevin.funds.config;

import com.kevin.funds.Interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new JWTInterceptor())
				.addPathPatterns("/**")//拦截所有
				.excludePathPatterns("/user/login");//排除用户相关
	}
}
