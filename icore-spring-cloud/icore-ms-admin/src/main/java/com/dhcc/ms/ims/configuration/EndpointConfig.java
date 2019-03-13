package com.dhcc.ms.ims.configuration;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author WangPeng
 * @ClassName: EndpointConfig
 * @Description: EndpointConfig配置类
 * @Package com.dhcc.ms.ims.configuration
 * Copyright DHC CO.LTD 2018
 * @date 2018/4/26
 */
@Configuration
public class EndpointConfig {
    @Bean
    public static Endpoint<Map<String, Object>> serverTime() {
        return new TimeEndpoint();
    }
}
