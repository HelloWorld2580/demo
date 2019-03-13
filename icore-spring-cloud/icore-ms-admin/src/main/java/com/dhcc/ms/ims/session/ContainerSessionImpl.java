package com.dhcc.ms.ims.session;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ContainerSessionImpl implements Session {

	@Override
	public void setAttribute(HttpServletRequest request, String name,
			Object value) {
		request.getSession().setAttribute(name, value);

	}

	@Override
	public Object getAttribute(HttpServletRequest request, String name) {
		return request.getSession().getAttribute(name);
	}

	@Override
	public String getId(HttpServletRequest request) {
		return request.getSession().getId();
	}

	@Override
	public void invalidate(HttpServletRequest request) {
		request.getSession().invalidate();
		
	}

}
