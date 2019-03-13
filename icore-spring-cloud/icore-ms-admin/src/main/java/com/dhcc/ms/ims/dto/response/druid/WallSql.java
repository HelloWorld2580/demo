package com.dhcc.ms.ims.dto.response.druid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WallSql {
    @JsonProperty("sql")
    private String sql;
    @JsonProperty("sample")
    private String sample;
    @JsonProperty("executeCount")
    private long executeCount;
    @JsonProperty("executeErrorCount")
    private long executeErrorCount;
    @JsonProperty("fetchRowCount")
    private long fetchRowCount;
    @JsonProperty("updateCount")
    private long updateCount;
    @JsonProperty("violationMessage")
    private String violationMessage;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public long getExecuteCount() {
        return executeCount;
    }

    public void setExecuteCount(long executeCount) {
        this.executeCount = executeCount;
    }

    public long getExecuteErrorCount() {
        return executeErrorCount;
    }

    public void setExecuteErrorCount(long executeErrorCount) {
        this.executeErrorCount = executeErrorCount;
    }

    public long getFetchRowCount() {
        return fetchRowCount;
    }

    public void setFetchRowCount(long fetchRowCount) {
        this.fetchRowCount = fetchRowCount;
    }

    public long getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(long updateCount) {
        this.updateCount = updateCount;
    }

    public String getViolationMessage() {
        return violationMessage;
    }

    public void setViolationMessage(String violationMessage) {
        this.violationMessage = violationMessage;
    }

    public WallSql add(WallSql wallSql) {
        this.sample = this.sample+" <br/> " + wallSql.getSample();
        this.executeCount+=wallSql.getExecuteCount();
        this.executeErrorCount+=wallSql.getExecuteErrorCount();
        this.fetchRowCount+=wallSql.getFetchRowCount();
        this.updateCount+=wallSql.getUpdateCount();
        this.violationMessage = this.violationMessage+" <br/> " + wallSql.getViolationMessage();
        return this;
    }
}
