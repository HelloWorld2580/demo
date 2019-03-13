package com.dhcc.ms.ims.service;

import com.dhcc.ms.ims.dto.request.InconsistentTransactionDtoPageReq;
import com.dhcc.ms.ims.dto.response.InconsistentTransactionDtoResp;
import com.dhcc.ms.utils.dto.ListRespDto;

public interface InconsistentTransactionFinder {

    ListRespDto<InconsistentTransactionDtoResp> inconsistentTransactions(InconsistentTransactionDtoPageReq page);

    int inconsistentTransactionsCount(long startTimestamp, long endTimestamp);

}
