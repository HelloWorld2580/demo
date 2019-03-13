package com.dhcc.ms.ims.service;

import java.util.List;

import com.dhcc.ms.ims.po.InconsistentTransaction;

public interface InconsistentTransactionServiceAdapter {

    List<InconsistentTransaction> getAllInconsistentTransactions();

    void deleteTransaction(InconsistentTransaction inconsistentTransaction);

}
