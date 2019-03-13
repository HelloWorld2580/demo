package com.dhcc.ms.ims.service.impl.lcn;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dhcc.ms.ims.po.InconsistentTransaction;
import com.dhcc.ms.ims.po.Transaction;
import com.dhcc.ms.ims.service.impl.lcn.po.TransactionCompensateMsg;
import com.dhcc.ms.ims.service.impl.lcn.po.TxInfo;
import com.dhcc.ms.ims.service.impl.lcn.po.TxModel;
import com.google.gson.Gson;

@Component
public class LcnTransactionServiceFinder {
    private static final String ID_FORMAT = "%s:%s:%s";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    @Qualifier("balancedRestTemplate")
    private RestTemplate rest;

    @Autowired
    private DiscoveryClient client;

    private String managerServiceName;

    public List<InconsistentTransaction> inconsistentTransactions() {
        List<InconsistentTransaction> inconsistentTransactionList = new ArrayList<InconsistentTransaction>();

        List<TxModel> transactions = initManagerServiceNameAndGetTransactionInfos();

        if (transactions == null) {
            return inconsistentTransactionList;
        }

        for (TxModel transactionResp : transactions) {
            TransactionCompensateMsg message = parseToMessage(transactionResp);

            InconsistentTransaction inconsistentTransaction = createInconsistentTransaction(message);
            inconsistentTransactionList.add(inconsistentTransaction);

            buildAndSetStartTransaction(message, inconsistentTransaction);

            for (TxInfo tx : message.getTxGroup().getList()) {
                buildAndSetParticipantTransaction(message, tx, inconsistentTransaction);
            }

        }

        return inconsistentTransactionList;
    }

    public String managerServiceName() {
        if (managerServiceName == null) {
            initManagerServiceNameAndGetTransactionInfos();
        }
        return managerServiceName;
    }

    private List<TxModel> initManagerServiceNameAndGetTransactionInfos() {
        List<TxModel> transactions = null;

        if (managerServiceName != null) {
            transactions = getTransactions(managerServiceName);
            if (transactions != null) {
                return transactions;
            }
        }

        for (String serviceName : client.getServices()) {
            transactions = getTransactions(serviceName);

            if (transactions != null) {
                managerServiceName = serviceName;
                return transactions;
            }
        }

        return null;
    }

    private List<TxModel> getTransactions(String serviceName) {
        try {
            ParameterizedTypeReference<List<TxModel>> typeRef = new ParameterizedTypeReference<List<TxModel>>() {
            };
            ResponseEntity<List<TxModel>> responseEntity = rest.exchange(
                    "http://" + serviceName + "/admin/modelInfos?path=*", HttpMethod.GET, new HttpEntity<>(null),
                    typeRef);
            return responseEntity.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    private TransactionCompensateMsg parseToMessage(TxModel transactionResp) {
        String data = new String(Base64.getDecoder().decode(transactionResp.getBase64()));// TODO
        return new Gson().fromJson(data, TransactionCompensateMsg.class);
    }

    private InconsistentTransaction createInconsistentTransaction(TransactionCompensateMsg message) {
        InconsistentTransaction inconsistentTransaction = new InconsistentTransaction();

        inconsistentTransaction.setId(String.format(ID_FORMAT, message.getModel(),
                DateFormatUtils.format(message.getCurrentTime(), DATE_FORMAT), message.getGroupId()));

        inconsistentTransaction.setCreateTime(new Date());

        return inconsistentTransaction;
    }

    private void buildAndSetStartTransaction(TransactionCompensateMsg message,
            InconsistentTransaction inconsistentTransaction) {
        Transaction transaction = new Transaction();

        transaction.setId(message.getUniqueKey());
        transaction.setCreateTime(new Date(message.getCurrentTime()));
        transaction.setServiceName(message.getModel());
        transaction.setTargetClass(message.getClassName());
        transaction.setMethodName(message.getMethodStr());
        transaction.setArgs(null);

        inconsistentTransaction.addTransaction(transaction);
    }

    private void buildAndSetParticipantTransaction(TransactionCompensateMsg message, TxInfo tx,
            InconsistentTransaction inconsistentTransaction) {
        Transaction transaction = new Transaction();

        transaction.setId(tx.getUniqueKey());
        transaction.setCreateTime(new Date(message.getTime() + message.getCurrentTime()));// TODO
        transaction.setServiceName(tx.getModel());

        transaction.setTargetClass(tx.getMethodStr().substring(tx.getMethodStr().lastIndexOf(" ") + 1,
                tx.getMethodStr().lastIndexOf(".")));
        transaction.setMethodName(tx.getMethodStr());
        transaction.setArgs(null);// TODO

        inconsistentTransaction.addTransaction(transaction);
    }

}
