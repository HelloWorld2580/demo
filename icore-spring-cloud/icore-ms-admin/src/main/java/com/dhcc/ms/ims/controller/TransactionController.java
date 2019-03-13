package com.dhcc.ms.ims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.service.InconsistentTransactionService;
import com.dhcc.ms.utils.dto.BaseMetaDto;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private InconsistentTransactionService inconsistentTransactionService;

    @RequestMapping(value = "/{inconsistentTransactionId}", method = RequestMethod.DELETE)
    @ResponseBody
    @Transactional
    public BaseMetaDto delete(@PathVariable String inconsistentTransactionId) throws CommonException {
        inconsistentTransactionService.deleteInconsistentTransaction(inconsistentTransactionId);
        return new BaseMetaDto();
    }
}
