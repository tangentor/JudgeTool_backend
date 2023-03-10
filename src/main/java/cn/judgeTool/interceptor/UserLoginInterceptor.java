package cn.judgeTool.interceptor;

import cn.judgeTool.util.HttpUtil;
import cn.judgeTool.util.JwtUtil;
import cn.judgeTool.util.TokenUtil;
import cn.judgeTool.util.UsernameUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		//拦截器取到请求先进行判断，如果是OPTIONS请求，则放行
		if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			return true;
		}
		//过滤
//		System.out.println(request.getRequestURI());
		if(request.getRequestURI().equals("/login"))
			return true;
		//检查token
		String token = request.getHeader("token");
		if(token==null || token==""){
			HttpUtil.writeUtf8Message(response,"未登录");
			return false;
		}
		try {
			String subject = JwtUtil.parseJWT(token).getSubject();
			if(subject==null){
				HttpUtil.writeUtf8Message(response,"登陆信息无效");
			}
			//保存到ThreadLocal
			UsernameUtil.setLoginUser(subject,false);
			TokenUtil.setToken(token);
		} catch (Exception e) {
			HttpUtil.writeUtf8Message(response,"登陆信息无效");
		}
		return true;
	}
}
