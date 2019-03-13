package com.dhcc.ms.ims.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import com.dhcc.ms.http.wrapper.ResettableStreamRequestWrapper;
import com.dhcc.ms.ims.event.ExceptionWarnEvent;
import com.dhcc.ms.ims.po.ExceptionWarn;
import com.dhcc.ms.utils.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@WebFilter(filterName = "zipkinSpansFilter", urlPatterns = "/api/v2/spans", asyncSupported=true)
public class ZipkinSpansFilter implements Filter  {
    private static Logger LOGGER = LoggerFactory.getLogger(ZipkinSpansFilter.class);
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Gson gson = new Gson();
        ResettableStreamRequestWrapper resettableStreamRequestWrapper = new ResettableStreamRequestWrapper((HttpServletRequest)request);
        String url = resettableStreamRequestWrapper.getRequestURI();
        try {  
            resettableStreamRequestWrapper.setCharacterEncoding(StandardCharsets.UTF_8.name());  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        }  
        LOGGER.debug("REQUEST: 【" + url + "】 BEGIN...");
        InputStream is = resettableStreamRequestWrapper.getInputStream();
        String requestBody = StreamUtils.copyToString(is, StandardCharsets.UTF_8);
        LOGGER.debug("Request Body:{}", requestBody);
        //做拦截处理
        if(requestBody.contains(CommonConstants.ZUUL_ERROR_NOTIFICATION_TAG)) {
            try {
                JsonArray requestJson = new JsonParser().parse(requestBody).getAsJsonArray();
                ExceptionWarn zError = new ExceptionWarn();
                for(JsonElement jEle : requestJson) {
                    JsonObject jTags = jEle.getAsJsonObject().getAsJsonObject("tags");
                    if(jTags.get("error") == null) continue;
                    String traceId = jEle.getAsJsonObject().get("traceId").getAsString();
                    Date date = new Date(jEle.getAsJsonObject().get("timestamp").getAsLong()/1000);
                    zError.setTraceId(traceId);
                    zError.setHttpMethod(jTags.get("http.method").getAsString());
                    zError.setHttpPath(jTags.get("http.path").getAsString());
                    zError.setError(jTags.get("error").isJsonNull()?"":jTags.get("error").getAsString());
                    zError.setTimestamp(jEle.getAsJsonObject().get("timestamp").getAsString());
                    zError.setDatetime(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss:SSS"));
                    ExceptionWarnEvent eWarnEvent = new ExceptionWarnEvent(this, zError);
                    applicationEventPublisher.publishEvent(eWarnEvent);
                    LOGGER.debug("Exception Trace ID: " + traceId);
                }
            } catch (Exception e) {
                LOGGER.error("span parse fail",e);
            }
        }
        if(!StringUtils.isEmpty(requestBody)) {
            resettableStreamRequestWrapper.setBodyData(requestBody.getBytes(StandardCharsets.UTF_8));
        } 
        resettableStreamRequestWrapper.resetInputStream();
        chain.doFilter(resettableStreamRequestWrapper, response);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }

}
