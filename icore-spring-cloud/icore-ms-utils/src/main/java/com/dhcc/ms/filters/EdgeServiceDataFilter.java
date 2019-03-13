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
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import com.dhcc.ms.http.wrapper.MSIMSHystrixConcurrencyStrategy;
import com.dhcc.ms.http.wrapper.ResettableStreamRequestWrapper;
import com.dhcc.ms.http.wrapper.ResettableStreamResponseWrapper;
import com.dhcc.ms.utils.CommonConstants;
import com.dhcc.ms.utils.MetaDataHelper;
import com.dhcc.ms.utils.dto.MetaDataDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.strategy.HystrixPlugins;

public class EdgeServiceDataFilter implements Filter {
	private static Logger LOGGER = LoggerFactory.getLogger(EdgeServiceDataFilter.class);
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
        	LOGGER.debug("EdgeService UUID: "+metaObject.getTraceId());
        }
        if(StringUtils.isEmpty(metaObject.getTraceId())) {
        	metaObject.setTraceId(UUID.randomUUID().toString());// you'd better use another uuid solution for distributed system.
        	LOGGER.debug("EdgeService UUID: "+metaObject.getTraceId());
        }
        resettableStreamRequestWrapper.resetInputStream();
        MetaDataHelper.setMetaData(metaObject);
        ResettableStreamResponseWrapper resettableStreamResponseWrapper = new ResettableStreamResponseWrapper((HttpServletResponse)response);
        chain.doFilter(resettableStreamRequestWrapper, resettableStreamResponseWrapper);
		String responseBody = new String(resettableStreamResponseWrapper.getResponseData());
		
        metaObjectResp = MetaDataHelper.getMetaData();
        JsonObject responseJson = new JsonObject();
        
        JsonObject responseJsonData = new JsonObject();
        if(!StringUtils.isEmpty(responseBody)) {
        	responseJsonData = new JsonParser().parse(responseBody).getAsJsonObject();
        	if(responseJsonData.get(CommonConstants.DATAGRAM_ERROR_CODE)!=null) {
        		//Got an exception
        		metaObjectResp.setCode(responseJsonData.get(CommonConstants.DATAGRAM_ERROR_CODE).getAsString());
        		metaObjectResp.setMessage(responseJsonData.get(CommonConstants.DATAGRAM_ERROR_MESSAGE).getAsString());
        		responseJsonData=null;
        	}
        }
        responseJson.add(CommonConstants.DATAGRAM_META, gson.toJsonTree(metaObjectResp));
        responseJson.add(CommonConstants.DATAGRAM_DATA, responseJsonData);
        response.setContentLength(responseJson.toString().getBytes().length);
        response.getOutputStream().write(responseJson.toString().getBytes());
        MetaDataHelper.removeMetaData();
        LOGGER.debug("Response Body:{}", responseJson.toString());
        LOGGER.debug("REQUEST【" + url + "】END.");
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
