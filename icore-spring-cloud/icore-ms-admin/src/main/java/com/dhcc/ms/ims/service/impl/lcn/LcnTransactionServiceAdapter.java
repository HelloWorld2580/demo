package com.dhcc.ms.ims.service.impl.lcn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhcc.ms.ims.po.InconsistentTransaction;
import com.dhcc.ms.ims.service.InconsistentTransactionServiceAdapter;

@Service
public class LcnTransactionServiceAdapter implements InconsistentTransactionServiceAdapter {
    @Autowired
    private LcnTransactionServiceFinder finder;
    @Autowired
    private LcnTransactionServiceHandler handler;

    @Override
    public List<InconsistentTransaction> getAllInconsistentTransactions() {
        return finder.inconsistentTransactions();
    }

    @Override
    public void deleteTransaction(InconsistentTransaction inconsistentTransaction) {
        handler.deleteTransaction(finder.managerServiceName(), inconsistentTransaction);
    }

}
