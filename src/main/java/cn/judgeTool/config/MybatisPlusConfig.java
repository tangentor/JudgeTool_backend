package cn.judgeTool.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
	@Bean
	public MybatisPlusInterceptor getMybatisPlusInterceptor(){
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		//添加分页插件，指定数据库类型MYSQL
		mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
		return mybatisPlusInterceptor;
	}

}
