package com.dhcc.ms.feign;

import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.utils.ErrorCodes;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignCustomErrorDecoder implements ErrorDecoder {


	  private final ResponseEntityDecoder decoder;
	  
	  public FeignCustomErrorDecoder(ResponseEntityDecoder decoder) {
	    this.decoder = decoder;
	  }
	  public Exception decode(String methodKey, Response response) {
	    try {
	    	Object decodedObject = decoder.decode(response, String.class);
	        CommonException error = (CommonException) decoder.decode(response, CommonException.class);
	        return error;
	    } catch (Exception e) {
	    	
	    }
		return new CommonException(ErrorCodes.CODE_000001,ErrorCodes.CODE_000001_MSG);
	  }

}
