package com.dhcc.ms.ims.dto.response;

import java.util.List;

import com.dhcc.ms.ims.service.impl.tcc.po.Transaction;

public class TransactionsResp {
  private List<Transaction> transactions;

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }
}
