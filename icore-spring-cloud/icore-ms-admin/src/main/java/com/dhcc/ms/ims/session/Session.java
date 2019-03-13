package com.dhcc.ms.ims.session;

import javax.servlet.http.HttpServletRequest;

public interface Session {
	public void setAttribute(HttpServletRequest request, String name,
			Object value);

	public Object getAttribute(HttpServletRequest request, String name);
	
	public String getId(HttpServletRequest request);
	
	public void invalidate(HttpServletRequest request);
}
