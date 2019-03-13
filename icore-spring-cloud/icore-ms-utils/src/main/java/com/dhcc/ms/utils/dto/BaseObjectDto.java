package com.dhcc.ms.utils.dto;

import com.dhcc.ms.utils.ErrorCodes;

public class BaseObjectDto<T> {
	private MetaDataDto meta;
	private T data;

	public BaseObjectDto(T data) {
		this.meta = new MetaDataDto(ErrorCodes.CODE_000000,ErrorCodes.CODE_000000_MSG);
		this.data = data;

	}
	public BaseObjectDto(MetaDataDto meta, T data) {
		this.meta = meta;
		this.data = data;

	}
	public BaseObjectDto() {
	}
	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	public MetaDataDto getMeta() {
		return meta;
	}

	public void setMeta(MetaDataDto meta) {
		this.meta = meta;
	}

}
