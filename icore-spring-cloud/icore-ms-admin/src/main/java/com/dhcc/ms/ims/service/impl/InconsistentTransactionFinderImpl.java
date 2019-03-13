package com.dhcc.ms.ims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dhcc.ms.ims.dao.InconsistentTransactionCallDao;
import com.dhcc.ms.ims.dto.request.InconsistentTransactionDtoPageReq;
import com.dhcc.ms.ims.dto.response.InconsistentTransactionDtoResp;
import com.dhcc.ms.ims.dto.response.InconsistentTransactionDtoResp.TransactionDtoResp;
import com.dhcc.ms.ims.po.InconsistentTransaction;
import com.dhcc.ms.ims.po.Transaction;
import com.dhcc.ms.ims.service.InconsistentTransactionFinder;
import com.dhcc.ms.utils.dto.ListRespDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class InconsistentTransactionFinderImpl implements InconsistentTransactionFinder {

    @Autowired
    private InconsistentTransactionCallDao dao;

    @Override
    public ListRespDto<InconsistentTransactionDtoResp> inconsistentTransactions(
            InconsistentTransactionDtoPageReq page) {
        PageHelper.startPage(page.getPageIndex(), page.getPageSize());

        List<String> ids;
        if (StringUtils.isEmpty(page.getFilter())) {
            ids = dao.selectAllInconsistentTransactionIds();
        } else {
            ids = dao.filterInconsistentTransactionIds(page.getFilter());
        }
        PageInfo<String> pageInfo = new PageInfo<String>(ids);

        List<InconsistentTransactionDtoResp> inconsistentTransactionRespList = null;
        if (!ids.isEmpty()) {
            List<InconsistentTransaction> inconsistentTransactions = dao.selectInconsistentTransactions(ids);
            inconsistentTransactionRespList = buildRespList(inconsistentTransactions);
        }

        return new ListRespDto<InconsistentTransactionDtoResp>(inconsistentTransactionRespList, pageInfo.getTotal());
    }

    private List<InconsistentTransactionDtoResp> buildRespList(List<InconsistentTransaction> inconsistentTransactions) {
        assert inconsistentTransactions != null;

        InconsistentTransactionDtoRespListBuilder builder = new InconsistentTransactionDtoRespListBuilder();
        for (InconsistentTransaction inconsistentTransaction : inconsistentTransactions) {
            builder.addInconsistentTransaction(inconsistentTransaction);
        }

        return builder.buildRespList();
    }

    @Override
    public int inconsistentTransactionsCount(long startTimestamp, long endTimestamp) {
        Date start = new Date(startTimestamp);
        Date end = new Date(endTimestamp);

        return dao.selectInconsistentTransactionCount(start, end);
    }

    private class InconsistentTransactionDtoRespListBuilder {
        private List<InconsistentTransactionDtoResp> inconsistentTransactionRespList = new ArrayList<InconsistentTransactionDtoResp>();

        private InconsistentTransactionDtoResp inconsistentTransactionResp;
        private TransactionDtoResp summaryTransaction;
        private TransactionDtoResp newBuildedTransaction;

        void addInconsistentTransaction(InconsistentTransaction inconsistentTransaction) {
            initBuildContext();

            buildeAndSetInconsistentTransactionResp(inconsistentTransaction);

            for (Transaction transaction : inconsistentTransaction.getTransactions()) {
                buildeAndSetTransactionResp(transaction);
                addTransactionResp();
                refushSummaryInfo();
            }

            setSummaryInfo();

            addInconsistentTransactionResp();
        }

        private void initBuildContext() {
            inconsistentTransactionResp = null;
            summaryTransaction = null;
            newBuildedTransaction = null;
        }

        private void buildeAndSetInconsistentTransactionResp(InconsistentTransaction inconsistentTransaction) {
            InconsistentTransactionDtoResp inconsistentTransactionResp = new InconsistentTransactionDtoResp();
            inconsistentTransactionResp.setId(inconsistentTransaction.getId());
            inconsistentTransactionResp.setTransactions(new ArrayList<TransactionDtoResp>());

            this.inconsistentTransactionResp = inconsistentTransactionResp;
        }

        private void buildeAndSetTransactionResp(Transaction transaction) {
            TransactionDtoResp transactionResp = new TransactionDtoResp();
            BeanUtils.copyProperties(transaction, transactionResp);

            this.newBuildedTransaction = transactionResp;
        }

        private void addTransactionResp() {
            this.inconsistentTransactionResp.getTransactions().add(newBuildedTransaction);
        }

        private void refushSummaryInfo() {
            boolean initialTransaction = summaryTransaction == null
                    || summaryTransaction.getCreateTime().after(newBuildedTransaction.getCreateTime());
            if (initialTransaction) {
                summaryTransaction = newBuildedTransaction;
            }
        }

        private void setSummaryInfo() {
            inconsistentTransactionResp.setCreateTime(summaryTransaction.getCreateTime());
            inconsistentTransactionResp.setServiceName(summaryTransaction.getServiceName());
            inconsistentTransactionResp.setTargetClass(summaryTransaction.getTargetClass());
            inconsistentTransactionResp.setMethodName(summaryTransaction.getMethodName());
        }

        private void addInconsistentTransactionResp() {
            this.inconsistentTransactionRespList.add(inconsistentTransactionResp);
        }

        List<InconsistentTransactionDtoResp> buildRespList() {
            return inconsistentTransactionRespList;
        }
    }
}
