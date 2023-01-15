package cn.judgeTool.config;

import cn.judgeTool.interceptor.ResponseResultInterceptor;
import cn.judgeTool.interceptor.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private ResponseResultInterceptor responseResultInterceptor;

	@Autowired
	private UserLoginInterceptor userLoginInterceptor;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 设置允许跨域的路径
		registry.addMapping("/**")
				// 设置允许跨域请求的域名
				.allowedOrigins("*")
				// 是否允许cookie
//				.allowCredentials(true)
				// 设置允许的请求方式
				.allowedMethods("*")
				// 设置允许的header属性
				.allowedHeaders("*")
				// 跨域允许时间
				.maxAge(3600);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		// 登陆拦截
		registry.addInterceptor(userLoginInterceptor);
		// 统一处理响应结果
		registry.addInterceptor(responseResultInterceptor);
	}
}
