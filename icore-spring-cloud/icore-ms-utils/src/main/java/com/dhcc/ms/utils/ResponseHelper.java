package com.dhcc.ms.utils;


import com.dhcc.ms.utils.dto.BaseListDto;
import com.dhcc.ms.utils.dto.BaseObjectDto;
import com.dhcc.ms.utils.dto.MetaDataDto;

public class ResponseHelper {
	public static boolean isSuccess(BaseObjectDto<?> response) {
		if(response==null || !ErrorCodes.CODE_000000.equalsIgnoreCase(response.getMeta().getCode()))
			return false;
		return true;
	}
	
	public static boolean isSuccess(BaseListDto<?> response) {
		if(response==null || !ErrorCodes.CODE_000000.equalsIgnoreCase(response.getMeta().getCode()))
			return false;
		return true;
	}
	
	public static BaseObjectDto<?> objectFallbackError(){
		BaseObjectDto<?> result = new BaseObjectDto<Object>(null);
		MetaDataDto meta = new MetaDataDto(ErrorCodes.CODE_000003,ErrorCodes.CODE_000003_MSG);
		result.setMeta(meta);
		return result;
	}
	
	public static BaseListDto<?> listFallbackError(){
		BaseListDto<?> result = new BaseListDto<Object>(null);
		MetaDataDto meta = new MetaDataDto(ErrorCodes.CODE_000003,ErrorCodes.CODE_000003_MSG);
		result.setMeta(meta);
		return result;
	}
}
