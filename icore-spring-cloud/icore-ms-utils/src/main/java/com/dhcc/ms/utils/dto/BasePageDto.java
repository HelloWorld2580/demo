package com.dhcc.ms.utils.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class BasePageDto {
	@NotNull
	private int pageIndex;
	@NotNull
	private int pageSize;
	public BasePageDto () {
    }
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
