package com.dhcc.ms.utils.dto;

import com.dhcc.ms.utils.ErrorCodes;

public class MetaDataDto {
	private String code;
	private String message;
	private String traceId;
	
	public MetaDataDto (){
		this.code = ErrorCodes.CODE_000000;
		this.message = ErrorCodes.CODE_000000_MSG;
	}
	public MetaDataDto (String code, String message){
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

}
