package com.dhcc.ms.ims.service.impl.lcn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dhcc.ms.ims.po.InconsistentTransaction;

@Component
public class LcnTransactionServiceHandler {
    @Autowired
    @Qualifier("balancedRestTemplate")
    private RestTemplate rest;

    boolean deleteTransaction(String managerServiceName, InconsistentTransaction inconsistentTransaction) {
        try {
            return rest.getForEntity("http://{1}/admin/delCompensate?path={2}", Boolean.class, managerServiceName,
                    inconsistentTransaction.getId()).getBody();
        } catch (Exception e) {
            return false;
        }
    }
}
