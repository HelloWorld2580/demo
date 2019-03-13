package com.dhcc.ms.ims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import zipkin.server.EnableZipkinServer;

@EnableCircuitBreaker
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = { "com.dhcc.ms" })
@MapperScan(basePackages={ "com.dhcc.ms.ims.dao" })
@ServletComponentScan
@EnableTurbine
@EnableHystrixDashboard
@EnableZipkinServer
@EnableAsync
public class MsdemoMsimsAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsdemoMsimsAdminApplication.class, args);
	}
}
