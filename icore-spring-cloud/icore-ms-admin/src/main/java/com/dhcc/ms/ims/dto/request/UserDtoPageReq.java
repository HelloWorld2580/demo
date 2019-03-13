package com.dhcc.ms.ims.dto.request;

import com.dhcc.ms.utils.dto.BasePageDto;

public class UserDtoPageReq extends BasePageDto {
	private String name;//过滤条件

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
