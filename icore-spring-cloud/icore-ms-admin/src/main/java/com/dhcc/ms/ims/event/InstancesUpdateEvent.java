package com.dhcc.ms.ims.event;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class InstancesUpdateEvent extends ApplicationEvent {
    public InstancesUpdateEvent(Object source) {
        super(source);
    }
}
