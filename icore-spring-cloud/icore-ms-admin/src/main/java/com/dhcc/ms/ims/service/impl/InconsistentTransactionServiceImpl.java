package com.dhcc.ms.ims.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhcc.ms.ims.dao.InconsistentTransactionCallDao;
import com.dhcc.ms.ims.po.InconsistentTransaction;
import com.dhcc.ms.ims.service.InconsistentTransactionService;
import com.dhcc.ms.ims.service.InconsistentTransactionServiceAdapter;

@Service
public class InconsistentTransactionServiceImpl implements InconsistentTransactionService {
    @Autowired
    private InconsistentTransactionServiceAdapter[] services;

    @Autowired
    private InconsistentTransactionCallDao dao;

    @Override
    public void deleteInconsistentTransaction(String inconsistentTransactionId) {
        InconsistentTransaction inconsistentTransaction = dao.selectInconsistentTransaction(inconsistentTransactionId);

        if (inconsistentTransaction == null) {
            return;
        }

        for (InconsistentTransactionServiceAdapter service : services) {
            service.deleteTransaction(inconsistentTransaction);
        }
        dao.deleteInconsistentTransaction(inconsistentTransaction);
    }
}
