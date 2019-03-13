package com.dhcc.ms.ims.service.impl.tcc;

import java.util.List;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import com.dhcc.ms.ims.po.InconsistentTransaction;
import com.dhcc.ms.ims.po.Transaction;
import com.dhcc.ms.ims.service.InconsistentTransactionServiceAdapter;

@Service
public class TccTransactionServiceAdapter implements InconsistentTransactionServiceAdapter {

    @Override
    public List<InconsistentTransaction> getAllInconsistentTransactions() {
        TccTransactionServiceFinder finder = transactionServiceFinder();
        return finder.inconsistentTransactions();
    }

    @Override
    public void deleteTransaction(InconsistentTransaction inconsistentTransaction) {
        TccTransactionServiceHandler handler = transactionServiceHandler();

        for (Transaction transaction : inconsistentTransaction.getTransactions()) {
            if (transaction == null) {
                continue;
            }
            handler.deleteTransaction(inconsistentTransaction, transaction);
        }
    }

    @Lookup
    TccTransactionServiceFinder transactionServiceFinder() {
        return null;
    }

    @Lookup
    TccTransactionServiceHandler transactionServiceHandler() {
        return null;
    }
}
