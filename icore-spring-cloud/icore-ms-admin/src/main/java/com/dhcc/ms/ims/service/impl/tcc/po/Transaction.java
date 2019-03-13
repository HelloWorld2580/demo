package com.dhcc.ms.ims.service.impl.tcc.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {
    private String globalTransactionId;
    private String branchQualifier;

    private TransactionStatus status;

    private TransactionType transactionType;

    private Date createTime;

    private Boolean normal;

    private List<Participant> participants = new ArrayList<Participant>();
    private List<String> sceneDescriptions = new ArrayList<String>();

    public String getGlobalTransactionId() {
        return globalTransactionId;
    }

    public void setGlobalTransactionId(String globalTransactionId) {
        this.globalTransactionId = globalTransactionId;
    }

    public String getBranchQualifier() {
        return branchQualifier;
    }

    public void setBranchQualifier(String branchQualifier) {
        this.branchQualifier = branchQualifier;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getNormal() {
        return normal;
    }

    public void setNormal(Boolean normal) {
        this.normal = normal;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<String> getSceneDescriptions() {
        return sceneDescriptions;
    }

    public void setSceneDescriptions(List<String> sceneDescriptions) {
        this.sceneDescriptions = sceneDescriptions;
    }
}
