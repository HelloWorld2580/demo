package com.dhcc.ms.ims.dto.request;

import com.dhcc.ms.utils.dto.BasePageDto;

public class DowntimeInstaneInfoDtoPageReq extends BasePageDto {
	private String filter;//过滤条件

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
