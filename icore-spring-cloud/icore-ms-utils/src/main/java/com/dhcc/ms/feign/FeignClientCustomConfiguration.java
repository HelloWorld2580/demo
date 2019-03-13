package com.dhcc.ms.feign;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.dhcc.ms.utils.CommonConstants;
import com.dhcc.ms.utils.MetaDataHelper;
import com.dhcc.ms.utils.dto.MetaDataDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import feign.Feign;
import feign.Feign.Builder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.ResponseMapper;
import feign.Retryer;
import feign.codec.Decoder;

@ConditionalOnClass(Feign.class)
@Configuration
public class FeignClientCustomConfiguration {
	private static Logger LOGGER = LoggerFactory.getLogger(FeignClientCustomConfiguration.class);

	@Autowired
	private ObjectFactory<HttpMessageConverters> messageConverters;
	//@Autowired Decoder decoder
	@Bean
	public RequestInterceptor headerInterceptor() {
		final Gson gson = new GsonBuilder().serializeNulls().create();
		return new RequestInterceptor() {
			public void apply(RequestTemplate requestTemplate) {
				MetaDataDto metaData = MetaDataHelper.getMetaData();
				requestTemplate.header(CommonConstants.META_DATA_HEADER, gson.toJson(metaData));
			}
		};
	}
	
	@Bean
	public FeignCustomErrorDecoder feignCustomErrorDecoder() {
		ResponseEntityDecoder decoder = new ResponseEntityDecoder(new SpringDecoder(this.messageConverters));
		return new FeignCustomErrorDecoder(decoder);
	}
	
	/*@Bean
    public FeignCustomResponseMapper feignDecoder(@Autowired Feign.Builder builder) {
	    //ResponseEntityDecoder decoder = new ResponseEntityDecoder(new SpringDecoder(this.messageConverters));
	    //builder.mapAndDecode(new FeignCustomResponseMapper(), null);
	    
	    //Feign.builder().mapAndDecode(new FeignCustomResponseMapper(), decoder);
	    //ResponseMappingDecoder decoder = new ResponseMappingDecoder(new FeignCustomResponseMapper(), decoder1);
        return new FeignCustomResponseMapper();
    }*/
	
	@Bean
    public Decoder feignDecoder() {
	    ResponseEntityDecoder decoder = new ResponseEntityDecoder(new SpringDecoder(this.messageConverters));
        return new FeignCustomDecoder(new FeignCustomResponseMapper(), decoder);
    }
}
