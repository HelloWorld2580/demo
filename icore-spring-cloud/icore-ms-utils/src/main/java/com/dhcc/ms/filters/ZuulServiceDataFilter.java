package com.dhcc.ms.filters;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.UUID;

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
import org.springframework.beans.BeanUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import com.dhcc.ms.http.wrapper.ResettableStreamRequestWrapper;
import com.dhcc.ms.http.wrapper.ResettableStreamResponseWrapper;
import com.dhcc.ms.utils.CommonConstants;
import com.dhcc.ms.utils.dto.MetaDataDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ZuulServiceDataFilter implements Filter {
	private static Logger LOGGER = LoggerFactory.getLogger(ZuulServiceDataFilter.class);
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        ResettableStreamRequestWrapper resettableStreamRequestWrapper = new ResettableStreamRequestWrapper((HttpServletRequest)request);
        String url = resettableStreamRequestWrapper.getRequestURI();
        Gson gson = new Gson();
        LOGGER.debug("REQUEST: 【" + url + "】 BEGIN...");
        String requestBody = null;
        MetaDataDto metaObject = null;
        MetaDataDto metaObjectResp = null;
        InputStream is = resettableStreamRequestWrapper.getInputStream();
        requestBody = StreamUtils.copyToString(is, Charset.forName("UTF-8"));
        LOGGER.debug("Request Body:{}", requestBody);
        if(!StringUtils.isEmpty(requestBody)) {
            try {
    			JsonObject requestJson = new JsonParser().parse(requestBody).getAsJsonObject();
    			JsonObject meta = requestJson.getAsJsonObject(CommonConstants.DATAGRAM_META);
    			metaObject = gson.fromJson(meta, MetaDataDto.class);
    			//Modify meta data you interested...
    			metaObject.setTraceId(UUID.randomUUID().toString());
    			JsonObject data = requestJson.getAsJsonObject(CommonConstants.DATAGRAM_DATA);
    			if (!data.isJsonNull()) {
    	            resettableStreamRequestWrapper.setBodyData(data.toString().getBytes());
    	        }
    		} catch (Exception e) {
    			LOGGER.error("Parse request body error:", e);
    		}
        } 
        if(metaObject == null) {
        	//Create a meta data part for all requests
        	metaObject = new MetaDataDto();
        	metaObject.setTraceId(UUID.randomUUID().toString());// you'd better use another uuid solution for distributed system.
        	LOGGER.debug("Zull UUID: "+metaObject.getTraceId());
        }
        if(StringUtils.isEmpty(metaObject.getTraceId())) {
        	metaObject.setTraceId(UUID.randomUUID().toString());// you'd better use another uuid solution for distributed system.
        	LOGGER.debug("Zull UUID: "+metaObject.getTraceId());
        }
        resettableStreamRequestWrapper.resetInputStream();
        ResettableStreamResponseWrapper resettableStreamResponseWrapper = new ResettableStreamResponseWrapper((HttpServletResponse)response);
        chain.doFilter(resettableStreamRequestWrapper, resettableStreamResponseWrapper);
		String responseBody = new String(resettableStreamResponseWrapper.getResponseData());
        LOGGER.debug("Response Body:", responseBody);
        JsonObject responseJson = new JsonObject();
        JsonObject responseJsonData = null;
        if(!StringUtils.isEmpty(responseBody)) {
            try {
    			responseJson = new JsonParser().parse(responseBody).getAsJsonObject();
    			JsonObject meta = responseJson.getAsJsonObject(CommonConstants.DATAGRAM_META);
    			metaObjectResp = gson.fromJson(meta, MetaDataDto.class);
    			
    		} catch (Exception e) {
    			LOGGER.error("Parse response meta error:", e);
    		}    			
			try {
				responseJsonData = responseJson.getAsJsonObject(CommonConstants.DATAGRAM_DATA);
			} catch (Exception e) {
				LOGGER.error("Parse response data error:", e);
			}

        } 
        if (metaObjectResp!=null && !StringUtils.isEmpty(metaObjectResp.getTraceId())){
        	//Replace the meta data from the response
        	BeanUtils.copyProperties(metaObjectResp, metaObject);
        }
        responseJson.add(CommonConstants.DATAGRAM_META, gson.toJsonTree(metaObject));
        responseJson.add(CommonConstants.DATAGRAM_DATA, responseJsonData);
        response.setContentLength(responseJson.toString().getBytes().length);
        response.getOutputStream().write(responseJson.toString().getBytes());
        LOGGER.debug("REQUEST【" + url + "】END.");
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
