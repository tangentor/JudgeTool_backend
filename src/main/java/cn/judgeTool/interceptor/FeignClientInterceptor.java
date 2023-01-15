package cn.judgeTool.interceptor;

import cn.judgeTool.util.TokenUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignClientInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate) {
		if(!requestTemplate.path().contains("login"))
			requestTemplate.header("token", TokenUtil.getToken());
	}

}