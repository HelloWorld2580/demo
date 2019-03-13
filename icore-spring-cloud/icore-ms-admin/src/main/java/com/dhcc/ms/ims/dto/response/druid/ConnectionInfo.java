package com.dhcc.ms.ims.dto.response.druid;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ConnectionInfo {

    private String appName;
    private String instanceId;
    private long datasourceId;

    public long getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(long datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @JsonProperty("id")
    private long id;
    @JsonProperty("connectionId")
    private long connectionId;
    @JsonProperty("useCount")
    private long useCount;
    @JsonProperty("lastActiveTime")
    private String lastActiveTime;

    @JsonProperty("connectTime")
    private String connectTime;
    @JsonProperty("holdability")
    private int holdability;
    @JsonProperty("transactionIsolation")
    private int transactionIsolation;
    @JsonProperty("autoCommit")
    private boolean autoCommit;
    @JsonProperty("readoOnly")
    private boolean readoOnly;

    @JsonProperty("pscache")
    private Pscache pscache;

    @JsonProperty("keepAliveCheckCount")
    private long keepAliveCheckCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(long connectionId) {
        this.connectionId = connectionId;
    }

    public long getUseCount() {
        return useCount;
    }

    public void setUseCount(long useCount) {
        this.useCount = useCount;
    }

    public String getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(String lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public String getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(String connectTime) {
        this.connectTime = connectTime;
    }

    public int getHoldability() {
        return holdability;
    }

    public void setHoldability(int holdability) {
        this.holdability = holdability;
    }

    public int getTransactionIsolation() {
        return transactionIsolation;
    }

    public void setTransactionIsolation(int transactionIsolation) {
        this.transactionIsolation = transactionIsolation;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    public boolean isReadoOnly() {
        return readoOnly;
    }

    public void setReadoOnly(boolean readoOnly) {
        this.readoOnly = readoOnly;
    }

    public Pscache getPscache() {
        return pscache;
    }

    public void setPscache(Pscache pscache) {
        this.pscache = pscache;
    }

    public long getKeepAliveCheckCount() {
        return keepAliveCheckCount;
    }

    public void setKeepAliveCheckCount(long keepAliveCheckCount) {
        this.keepAliveCheckCount = keepAliveCheckCount;
    }
}

