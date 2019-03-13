package com.dhcc.ms.ims.event;

import org.springframework.context.ApplicationEvent;

import com.dhcc.ms.ims.po.ExceptionWarn;

public class ExceptionWarnEvent extends ApplicationEvent {

    private static final long serialVersionUID = -9117448355522262420L;
    private ExceptionWarn exceptionWarn;

    public ExceptionWarnEvent(Object source, ExceptionWarn exceptionWarn) {
        super(source);
        this.exceptionWarn = exceptionWarn;
    }

    public ExceptionWarn getExceptionWarn() {
        return exceptionWarn;
    }

    public void setExceptionWarn(ExceptionWarn exceptionWarn) {
        this.exceptionWarn = exceptionWarn;
    }

}
