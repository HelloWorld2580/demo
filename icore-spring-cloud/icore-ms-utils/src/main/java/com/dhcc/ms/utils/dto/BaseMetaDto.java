package com.dhcc.ms.utils.dto;

import com.dhcc.ms.utils.ErrorCodes;

public class BaseMetaDto {
	private MetaDataDto meta;
	private Object data;

	public BaseMetaDto() {
		this.meta = new MetaDataDto(ErrorCodes.CODE_000000,ErrorCodes.CODE_000000_MSG);
		this.setData(null);

	}
	public MetaDataDto getMeta() {
		return meta;
	}

	public void setMeta(MetaDataDto meta) {
		this.meta = meta;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
