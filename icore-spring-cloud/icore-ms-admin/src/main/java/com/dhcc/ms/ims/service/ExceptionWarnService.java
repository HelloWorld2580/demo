package com.dhcc.ms.ims.service;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dto.request.ExceptionWarnDtoPageReq;
import com.dhcc.ms.ims.dto.response.ExceptionWarnDtoResp;
import com.dhcc.ms.ims.po.ExceptionWarn;
import com.dhcc.ms.utils.dto.ListRespDto;

public interface ExceptionWarnService {
    public ListRespDto<ExceptionWarnDtoResp> getExceptionWarnsByPage(ExceptionWarnDtoPageReq page) throws CommonException;
    public int getNewExceptionWarnsCount(String startTimestamp, String endTimrestamp) throws CommonException;
    public int addExceptionWarn(ExceptionWarn ewarn) throws CommonException;
}
