package com.dhcc.ms.ims.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InconsistentTransaction {
    private String id;

    private Date createTime;

    private List<Transaction> transactions = new ArrayList<Transaction>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Transaction getTransaction(String transactionCallId, String transactionId) {
        if (!(id.equals(transactionCallId))) {
            return null;
        }

        for (Transaction transaction : transactions) {
            if (transaction.getId().equals(transactionId)) {
                return transaction;
            }
        }

        return null;
    }
}
