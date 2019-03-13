package com.dhcc.ms.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhcc.ms.utils.ErrorCodes;
import com.dhcc.ms.utils.dto.MetaDataDto;
import com.netflix.hystrix.exception.HystrixRuntimeException;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 捕获全局异常
	 */
	@ResponseBody
	@ExceptionHandler(RuntimeException.class)
	public MetaDataDto exceptionHandler(RuntimeException ex) {
		MetaDataDto meta = new MetaDataDto(ErrorCodes.CODE_000001,ErrorCodes.CODE_000001_MSG);
		LOGGER.error("Global RuntimeException:", ex);
		return meta;
	}
	
	@ResponseBody
	@ExceptionHandler(CommonException.class)
	public MetaDataDto exceptionHandler(CommonException ex) {
		MetaDataDto meta = new MetaDataDto(ex.getErrorCode(),ex.getMessage());
		LOGGER.error("CommonException:", ex);
		return meta;
	}
	
	@ResponseBody
    @ExceptionHandler(HystrixRuntimeException.class)
    public MetaDataDto exceptionHandler(HystrixRuntimeException ex) {
	    MetaDataDto meta;
	    
	    if(ex.getCause()!=null && (ex.getCause().getCause() instanceof CommonException)) {
	        CommonException ce = (CommonException)ex.getCause().getCause();
	        meta = new MetaDataDto(ce.getErrorCode(),ce.getMessage());
	    } else {
	        meta = new MetaDataDto(ErrorCodes.CODE_000001,ErrorCodes.CODE_000001_MSG);
	    }
        LOGGER.error("HystrixRuntimeException:", ex);
        
        return meta;
    }

}
