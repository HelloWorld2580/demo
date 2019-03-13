package com.dhcc.ms.zuul.gateway.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class CustomPathZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        Object originalRequestPath = context.get(FilterConstants.REQUEST_URI_KEY);
        //String modifiedRequestPath = "/api/microservicePath" + originalRequestPath;
        //context.put(FilterConstants.REQUEST_URI_KEY, modifiedRequestPath);

        return null;
    }
}
