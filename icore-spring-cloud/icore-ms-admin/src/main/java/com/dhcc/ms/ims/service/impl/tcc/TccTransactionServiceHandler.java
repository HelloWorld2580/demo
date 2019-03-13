package com.dhcc.ms.ims.service.impl.tcc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dhcc.ms.ims.po.InconsistentTransaction;
import com.dhcc.ms.ims.po.Transaction;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TccTransactionServiceHandler {
    @Autowired
    @Qualifier("balancedRestTemplate")
    private RestTemplate rest;

    boolean deleteTransaction(InconsistentTransaction inconsistentTransaction, Transaction transaction) {
        try {
            String serviceName = transaction.getServiceName();
            String inconsistentTransactionId = inconsistentTransaction.getId();
            String transactionId = transaction.getId();

            rest.delete("http://{1}/{2}/transactions/{3}/{4}", serviceName, serviceName, inconsistentTransactionId,
                    transactionId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
