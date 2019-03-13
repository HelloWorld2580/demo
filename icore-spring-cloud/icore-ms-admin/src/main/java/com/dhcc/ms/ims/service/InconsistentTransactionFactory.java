package com.dhcc.ms.ims.service;

import java.util.List;

import com.dhcc.ms.ims.po.InconsistentTransaction;

public interface InconsistentTransactionFactory {

    void createInconsistentTransactions(List<InconsistentTransaction> allInconsistentTransactionCalls);

}
