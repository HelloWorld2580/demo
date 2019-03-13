package com.dhcc.ms.ims.filter;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @author WangPeng
 * @ClassName: DruidStatFilter
 * @Description: DruidStatFilter
 * @Package com.dhcc.ms.ims.filter
 * Copyright DHC CO.LTD 2018
 * @date 2018/5/4
 */
//@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
//        initParams={
//                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
//        })
public class DruidStatFilter extends WebStatFilter {
}
