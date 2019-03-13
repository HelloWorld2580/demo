package com.dhcc.ms.ims.dto.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dhcc.ms.ims.utils.ListJsonPrettyFormatSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class InconsistentTransactionDtoResp {
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date createTime;

    private String serviceName;

    private String targetClass;

    private String methodName;

    @JsonSerialize(using = ListJsonPrettyFormatSerializer.class)
    private List<TransactionDtoResp> transactions = new ArrayList<TransactionDtoResp>();

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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<TransactionDtoResp> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDtoResp> transactions) {
        this.transactions = transactions;
    }

    public static class TransactionDtoResp {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
        private Date createTime;

        private String serviceName;

        private String targetClass;

        private String methodName;

        private Object[] args;

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getTargetClass() {
            return targetClass;
        }

        public void setTargetClass(String targetClass) {
            this.targetClass = targetClass;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public Object[] getArgs() {
            return args;
        }

        public void setArgs(Object[] args) {
            this.args = args;
        }

    }
}
