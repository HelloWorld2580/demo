package com.dhcc.ms.ims.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
public class AdminConfigure extends WebMvcConfigurerAdapter {
    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(jacksonSupportsMoreTypes());
        return restTemplate;
    }

    @Bean
    @LoadBalanced //实现客户端负载均衡
    public RestTemplate balancedRestTemplate() {
        return builder.build();
    }

  
    private HttpMessageConverter jacksonSupportsMoreTypes() {//eg. Gitlab returns JSON as plain text application/octet-stream
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.parseMediaType("text/plain;charset=utf-8"), MediaType.APPLICATION_OCTET_STREAM));
        return converter;
    }


    @Bean
    public ThreadPoolTaskScheduler updateTransactionsTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(1);
        taskScheduler.setRemoveOnCancelPolicy(true);
        taskScheduler.setThreadNamePrefix("updateTransactionsTask");
        return taskScheduler;
    }

    // https://stackoverflow.com/questions/26213050/spring-boot-enable-async-supported-like-in-web-xml
    // It overrides that default one from DispatcherServletAutoConfiguration.
//    @Bean
//    public ServletRegistrationBean dispatcherServlet() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(
//                new DispatcherServlet(), "/");
//        registration.setAsyncSupported(true);
//        return registration;
//    }
}
