package com.dhcc.ms.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import com.dhcc.ms.ims.po.InconsistentTransaction;
import com.dhcc.ms.ims.service.InconsistentTransactionFactory;
import com.dhcc.ms.ims.service.InconsistentTransactionServiceAdapter;

@Component
public class InconsistentTransactionSource {
    @Value("${com.dhcc.ms.ims.instance.transactions.inconsistent.refushInterval:5000}")
    private long refushInterval;

    @Autowired
    private InconsistentTransactionServiceAdapter[] services;

    @Autowired
    private InconsistentTransactionFactory factory;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    private void refushAllInconsistentTransactions() {
        factory.createInconsistentTransactions(getAllInconsistentTransactions());
    }

    private List<InconsistentTransaction> getAllInconsistentTransactions() {
        List<InconsistentTransaction> inconsistentTransactions = new ArrayList<InconsistentTransaction>();

        for (InconsistentTransactionServiceAdapter service : services) {
            inconsistentTransactions.addAll(service.getAllInconsistentTransactions());
        }

        return inconsistentTransactions;
    }

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        taskScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                refushAllInconsistentTransactions();
            }
        }, refushInterval);
    }

    @EventListener
    public void onContextClosed(ContextClosedEvent event) {
        taskScheduler.shutdown();
    }

}
