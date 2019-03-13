package com.dhcc.ms.filters;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import com.dhcc.ms.http.wrapper.MSIMSHystrixConcurrencyStrategy;
import com.dhcc.ms.http.wrapper.ResettableStreamRequestWrapper;
import com.dhcc.ms.http.wrapper.ResettableStreamResponseWrapper;
import com.dhcc.ms.utils.CommonConstants;
import com.dhcc.ms.utils.MetaDataHelper;
import com.dhcc.ms.utils.dto.MetaDataDto;
import com.google.gson.Gson;
import com.netflix.hystrix.strategy.HystrixPlugins;

public class ServiceDataFilter implements Filter {
	private static Logger LOGGER = LoggerFactory.getLogger(ServiceDataFilter.class);
	public void init(FilterConfig filterConfig) throws ServletException {
		HystrixPlugins.getInstance().registerConcurrencyStrategy(new MSIMSHystrixConcurrencyStrategy());
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        ResettableStreamRequestWrapper resettableStreamRequestWrapper = new ResettableStreamRequestWrapper((HttpServletRequest)request);
        String url = resettableStreamRequestWrapper.getRequestURI();
        Gson gson = new Gson();
        LOGGER.debug("REQUEST: 【" + url + "】 BEGIN...");
        String requestBody = null;
        MetaDataDto metaObject = null;
        InputStream is = resettableStreamRequestWrapper.getInputStream();
        requestBody = StreamUtils.copyToString(is, Charset.forName("UTF-8"));
        String meta = resettableStreamRequestWrapper.getHeader(CommonConstants.META_DATA_HEADER);
        metaObject = gson.fromJson(meta, MetaDataDto.class);
        MetaDataHelper.setMetaData(metaObject);
        LOGGER.debug("Request Meta:{}", meta);
        LOGGER.debug("Request Body:{}", requestBody);
        if(!StringUtils.isEmpty(requestBody)) {
        	resettableStreamRequestWrapper.setBodyData(requestBody.getBytes());
        } 
        resettableStreamRequestWrapper.resetInputStream();
        ResettableStreamResponseWrapper resettableStreamResponseWrapper = new ResettableStreamResponseWrapper((HttpServletResponse)response);
        chain.doFilter(resettableStreamRequestWrapper, resettableStreamResponseWrapper);
		String responseBody = new String(resettableStreamResponseWrapper.getResponseData());
        LOGGER.debug("Response Body:{}", responseBody);
        //resettableStreamResponseWrapper.addHeader(CommonConstants.META_DATA_HEADER, meta);
        response.setContentLength(responseBody.getBytes().length);
        response.getOutputStream().write(responseBody.getBytes());
        MetaDataHelper.removeMetaData();
        LOGGER.debug("REQUEST【" + url + "】END.");
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
