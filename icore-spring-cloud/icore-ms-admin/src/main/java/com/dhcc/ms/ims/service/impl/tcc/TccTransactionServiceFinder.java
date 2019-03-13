package com.dhcc.ms.ims.service.impl.tcc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dhcc.ms.ims.dto.response.TransactionsResp;
import com.dhcc.ms.ims.po.InconsistentTransaction;
import com.dhcc.ms.ims.service.impl.tcc.po.Participant;
import com.dhcc.ms.ims.service.impl.tcc.po.Transaction;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TccTransactionServiceFinder {
    @Autowired
    @Qualifier("balancedRestTemplate")
    private RestTemplate rest;

    @Autowired
    private DiscoveryClient client;

    private Map<String, InconsistentTransaction> tempInconsistentTransactionCalls = new HashMap<String, InconsistentTransaction>();
    private Set<String> unInconsistentTransactionIds = new HashSet<String>();

    public List<InconsistentTransaction> inconsistentTransactions() {

        for (String serviceName : client.getServices()) {

            List<Transaction> transactions = getTransactions(serviceName);
            if (transactions == null) {
                continue;
            }

            for (Transaction transactionResp : transactions) {
                String inconsistentTransactionId = transactionResp.getGlobalTransactionId();

                if (!isInconsistentTransaction(transactionResp, inconsistentTransactionId)) {
                    removeUnInconsistentTransaction(inconsistentTransactionId);
                    continue;
                }

                InconsistentTransaction inconsistentTransaction = getInconsistentTransaction(inconsistentTransactionId);

                com.dhcc.ms.ims.po.Transaction transaction = buildeTransaction(serviceName, transactionResp);

                addTransaction(inconsistentTransaction, transaction);
            }
        }

        return new ArrayList<>(tempInconsistentTransactionCalls.values());
    }

    private void removeUnInconsistentTransaction(String inconsistentTransactionId) {
        tempInconsistentTransactionCalls.remove(inconsistentTransactionId);
    }

    private boolean isInconsistentTransaction(Transaction transactionResp, String inconsistentTransactionId) {
        if (unInconsistentTransactionIds.contains(inconsistentTransactionId)) {
            return false;
        }

        if (transactionResp.getNormal()) {
            unInconsistentTransactionIds.add(inconsistentTransactionId);
            return false;
        }

        return true;
    }

    private List<com.dhcc.ms.ims.service.impl.tcc.po.Transaction> getTransactions(String serviceName) {
        try {
            return rest
                    .getForObject("http://" + serviceName + "/" + serviceName + "/transactions", TransactionsResp.class)
                    .getTransactions();
        } catch (Exception e) {
            return null;
        }
    }

    private InconsistentTransaction getInconsistentTransaction(String callId) {
        InconsistentTransaction inconsistentTransactionCall = tempInconsistentTransactionCalls.get(callId);

        if (inconsistentTransactionCall == null) {
            inconsistentTransactionCall = new InconsistentTransaction();
            inconsistentTransactionCall.setId(callId);
            inconsistentTransactionCall.setCreateTime(new Date());
            tempInconsistentTransactionCalls.put(callId, inconsistentTransactionCall);
        }

        return inconsistentTransactionCall;
    }

    private com.dhcc.ms.ims.po.Transaction buildeTransaction(String serviceName,
            com.dhcc.ms.ims.service.impl.tcc.po.Transaction transactionResp) {
        com.dhcc.ms.ims.po.Transaction transaction = new com.dhcc.ms.ims.po.Transaction();
        transaction.setCreateTime(transactionResp.getCreateTime());
        transaction.setId(transactionResp.getBranchQualifier());
        transaction.setServiceName(serviceName);

        Participant participant = transactionResp.getParticipants().get(0);
        transaction.setTargetClass(participant.getTargetClass());
        transaction.setMethodName(participant.getMethodName());
        transaction.setArgs(participant.getArgs());
        return transaction;
    }

    private void addTransaction(InconsistentTransaction inconsistentTransactionCall,
            com.dhcc.ms.ims.po.Transaction transaction) {
        inconsistentTransactionCall.addTransaction(transaction);
    }

}
