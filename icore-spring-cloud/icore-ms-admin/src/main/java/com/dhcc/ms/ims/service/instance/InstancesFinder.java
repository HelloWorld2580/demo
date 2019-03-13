package com.dhcc.ms.ims.service.instance;

import java.util.Set;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dto.request.DowntimeInstaneInfoDtoPageReq;
import com.dhcc.ms.ims.dto.request.ExceptionWarnDtoPageReq;
import com.dhcc.ms.ims.po.InstanceInfo;
import com.dhcc.ms.utils.dto.ListRespDto;

public interface InstancesFinder {

    Set<String> allInstanceIds();

    InstanceInfo instance(String instanceId);

    ListRespDto<InstanceInfo> instances(ExceptionWarnDtoPageReq page);
    ListRespDto<InstanceInfo> instancesWeight(ExceptionWarnDtoPageReq page);

    ListRespDto<InstanceInfo> getDowntimeInstancesByPage(DowntimeInstaneInfoDtoPageReq page) throws CommonException;

    int getDowntimeInstancesCount(long startTimestamp, long endTimestamp) throws CommonException;

}
