package com.dhcc.ms.ims.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhcc.ms.ims.dto.response.UserDtoResp;
import com.dhcc.ms.ims.session.Session;
import com.dhcc.ms.ims.utils.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component	
public class SecurityInterceptor implements HandlerInterceptor {

	private List<String> excludedUrls= Arrays.asList("/login","/static","/error","/span");
	
	@Autowired
	private Session session;
	
	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj) throws Exception {
		String requestUri = request.getRequestURI();
		if (CollectionUtils.isNotEmpty(excludedUrls)) {
			for (String excludeUrl : excludedUrls) {
				if (requestUri.contains(excludeUrl)) {
					return true;
				}
			}
		}
		
		UserDtoResp user = (UserDtoResp)session.getAttribute(request, Constants.USER_SESSION_KEY);
		
		if (user == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
