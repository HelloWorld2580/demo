package com.dhcc.ms.ims.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.ms.ims.dao.InconsistentTransactionCallDao;
import com.dhcc.ms.ims.po.InconsistentTransaction;
import com.dhcc.ms.ims.po.Transaction;
import com.dhcc.ms.ims.service.InconsistentTransactionFactory;

@Service
public class InconsistentTransactionFactoryImpl implements InconsistentTransactionFactory {
    @Autowired
    private InconsistentTransactionCallDao dao;

    @Override
    @Transactional
    public void createInconsistentTransactions(List<InconsistentTransaction> allInconsistentTransactions) {
        Set<String> existedIds = new HashSet<String>(dao.selectAllInconsistentTransactionIds());
        Set<String> retrieveIds = new HashSet<String>();

        for (InconsistentTransaction call : allInconsistentTransactions) {
            if (isRetrieveTransactionExisted(call, existedIds, retrieveIds)) {
                continue;
            }

            addNewInconsistentTransaction(call);
        }

        deleteUnRetrievTransactionFromExisted(existedIds, retrieveIds);
    }

    private boolean isRetrieveTransactionExisted(InconsistentTransaction call, Set<String> existedIds,
            Set<String> retrieveIds) {
        retrieveIds.add(call.getId());
        return existedIds.contains(call.getId());
    }

    private void addNewInconsistentTransaction(InconsistentTransaction call) {
        for (Transaction transaction : call.getTransactions()) {
            dao.insertInconsistentTransaction(call, transaction);
        }
    }

    private void deleteUnRetrievTransactionFromExisted(Set<String> existedIds, Set<String> retrieveIds) {
        existedIds.removeAll(retrieveIds);
        for (String unRetrieveId : existedIds) {
            dao.deleteInconsistentTransactionById(unRetrieveId);
        }
    }

}
