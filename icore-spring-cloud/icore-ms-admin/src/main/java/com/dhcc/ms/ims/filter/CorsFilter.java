package com.dhcc.ms.ims.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

@Order(1)
@WebFilter(filterName = "corsFilter", urlPatterns = "/*", asyncSupported = true)
public class CorsFilter implements Filter {
    private static Logger LOGGER = LoggerFactory.getLogger(CorsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Intentionally empty.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String origin = request.getHeader("Origin");
        // Zipkin post
        /*
         * String url = request.getRequestURI(); if(UrlHelper.isMonitorUrl(url)) {
         * filterChain.doFilter(request, response); return; }
         */
        LOGGER.debug("Origin: " + origin);
        if(request != null) {
            LOGGER.debug("Request URI: "+ request.getRequestURI());
        }
        if (origin != null && (origin.equals("http://localhost:9091") || origin.equals("http://localhost:8081")
                || origin.equals("http://localhost:8080"))) {
            response.setHeader("Access-Control-Allow-Origin", origin);//对简单请求设置跨域访问
        }
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, PATCH, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization, X-Requested-With");
        //注意:CORS请求默认不发送Cookie和HTTP认证信息。
        response.setHeader("Access-Control-Allow-Credentials", "true");//服务器明确许可，cookie可以包含在请求中，一起发给服务器。

        // Returns an empty OK response on any OPTION request, otherwise proceed with the filter
        // chain.
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Intentionally empty.
    }
}
