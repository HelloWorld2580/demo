package com.dhcc.ms.ims.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.dhcc.ms.ims.dao.ExceptionWarnDao;
import com.dhcc.ms.ims.event.ExceptionWarnEvent;
import com.dhcc.ms.ims.filter.ZipkinSpansFilter;
@Component
public class CustomEventListener {
    private static Logger LOGGER = LoggerFactory.getLogger(CustomEventListener.class);
    @Value("${com.dhcc.ms.ims.maxexceptionwarn:100}")
    private int maxExceptionWarn;
    @Autowired
    private ExceptionWarnDao exceptionWarnDao;
    @Async
    @EventListener
    //@Transactional
    public void handleExceptionWarnEvent(ExceptionWarnEvent event) {
        //do some operations
        LOGGER.debug("Got ExceptionWarnEvent: " + event.getExceptionWarn().getTraceId());
        int result = exceptionWarnDao.insert(event.getExceptionWarn());
        result = exceptionWarnDao.deleteOldRecords(maxExceptionWarn);
    }
}
