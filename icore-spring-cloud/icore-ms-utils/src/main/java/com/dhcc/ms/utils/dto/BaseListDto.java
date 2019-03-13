package com.dhcc.ms.utils.dto;

import com.dhcc.ms.utils.ErrorCodes;

public class BaseListDto<T>{
	private MetaDataDto meta;
	private ListRespDto<T> data;
	public BaseListDto (){
	}
	public BaseListDto (ListRespDto<T> list){
		this.meta = new MetaDataDto(ErrorCodes.CODE_000000,ErrorCodes.CODE_000000_MSG);
		this.data = list;
	}

	public MetaDataDto getMeta() {
		return meta;
	}

	public void setMeta(MetaDataDto meta) {
		this.meta = meta;
	}

	public ListRespDto<T> getData() {
		return data;
	}

	public void setData(ListRespDto<T> data) {
		this.data = data;
	}
}
