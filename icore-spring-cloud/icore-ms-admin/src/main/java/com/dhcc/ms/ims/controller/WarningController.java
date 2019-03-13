package com.dhcc.ms.ims.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dto.request.DowntimeInstaneInfoDtoPageReq;
import com.dhcc.ms.ims.dto.request.ExceptionWarnDtoPageReq;
import com.dhcc.ms.ims.dto.request.InconsistentTransactionDtoPageReq;
import com.dhcc.ms.ims.dto.response.ExceptionWarnDtoResp;
import com.dhcc.ms.ims.dto.response.InconsistentTransactionDtoResp;
import com.dhcc.ms.ims.dto.response.NewWarningCountDtoResp;
import com.dhcc.ms.ims.po.InstanceInfo;
import com.dhcc.ms.ims.service.ExceptionWarnService;
import com.dhcc.ms.ims.service.InconsistentTransactionFinder;
import com.dhcc.ms.ims.service.instance.InstancesFinder;
import com.dhcc.ms.utils.dto.BaseListDto;
import com.dhcc.ms.utils.dto.BaseObjectDto;
import com.dhcc.ms.utils.dto.ListRespDto;
@RestController
@RequestMapping(value = "/warn")
public class WarningController {
    @Value("${com.dhcc.ms.ims.warn.scannDelay:3000}")
    private long scannDelay;
    
    @Autowired
    private ExceptionWarnService exceptionWarnService;
    @Autowired
    private InstancesFinder downtimeInstanceService;
    @Autowired
    private InconsistentTransactionFinder inconsistentTransactionFinder;
    
    @RequestMapping(value = "/getexceptionwarnsbypage", method = RequestMethod.POST)
    BaseListDto<ExceptionWarnDtoResp> getExceptionWarnsByPage(@RequestBody ExceptionWarnDtoPageReq page) throws CommonException {
        ListRespDto<ExceptionWarnDtoResp> warnListResp = exceptionWarnService.getExceptionWarnsByPage(page);
        return new BaseListDto<ExceptionWarnDtoResp>(warnListResp);
    }
    @RequestMapping(value = "/getdowntimeinstanceinfosbypage", method = RequestMethod.POST)
    BaseListDto<InstanceInfo> getDowntimeInstaneInfosByPage(@RequestBody DowntimeInstaneInfoDtoPageReq page) throws CommonException {
        ListRespDto<InstanceInfo> infosResp = downtimeInstanceService.getDowntimeInstancesByPage(page);
        return new BaseListDto<InstanceInfo>(infosResp);
    }
    @RequestMapping(value = "/getInconsistentTransactionsbypage", method = RequestMethod.POST)
    BaseListDto<InconsistentTransactionDtoResp> getInconsistentTransactionsByPage(@RequestBody InconsistentTransactionDtoPageReq page) throws CommonException {
        ListRespDto<InconsistentTransactionDtoResp> infosResp = inconsistentTransactionFinder.inconsistentTransactions(page);
        return new BaseListDto<InconsistentTransactionDtoResp>(infosResp);
    }
    @RequestMapping(value = "/getwarningcounts", method = RequestMethod.GET)
    BaseObjectDto<NewWarningCountDtoResp> getWarningCounts(@RequestParam(value="timestamp", required=false) Long timestamp) throws CommonException {
        NewWarningCountDtoResp newWarningCountDtoResp = new NewWarningCountDtoResp();

        long endTimestamp = new Date().getTime() - scannDelay;
        long startTimestamp = timestamp==null?endTimestamp:timestamp;

        if (startTimestamp >= endTimestamp) {
            newWarningCountDtoResp.setStartTimestamp(endTimestamp);
            newWarningCountDtoResp.setEndTimestamp(endTimestamp);
            return new BaseObjectDto<NewWarningCountDtoResp>(newWarningCountDtoResp);
        }
        
        newWarningCountDtoResp.setNewExceptionCount(exceptionWarnService.getNewExceptionWarnsCount(String.valueOf(startTimestamp * 1000), String.valueOf(endTimestamp * 1000)));
        newWarningCountDtoResp.setNewServiceDownCount(downtimeInstanceService.getDowntimeInstancesCount(startTimestamp, endTimestamp));
        newWarningCountDtoResp.setNewInconsistentCount(inconsistentTransactionFinder.inconsistentTransactionsCount(startTimestamp, endTimestamp));
        newWarningCountDtoResp.setStartTimestamp(startTimestamp);
        newWarningCountDtoResp.setEndTimestamp(endTimestamp);

        return new BaseObjectDto<NewWarningCountDtoResp>(newWarningCountDtoResp);
    }
}
