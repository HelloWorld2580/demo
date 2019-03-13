package com.dhcc.ms.zuul.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.dhcc.ms.filters.ZuulServiceDataFilter;

@SpringBootApplication
@ComponentScan(basePackages = { "com.dhcc.ms" })
@EnableEurekaClient
@EnableZuulProxy
public class MsdemoZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsdemoZuulGatewayApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean packetFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new ZuulServiceDataFilter());
		registration.addUrlPatterns("/*");
		registration.setName("zuulServiceDataFilter");
		registration.setOrder(1);
		return registration;
	}
}
