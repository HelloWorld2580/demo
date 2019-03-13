package com.dhcc.ms.ims.dto.response.druid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pscache{
    @JsonProperty("sql")
    private String sql;
    @JsonProperty("defaultRowPrefetch")
    private int defaultRowPrefetch;
    @JsonProperty("rowPrefetch")
    private int rowPrefetch;
    @JsonProperty("hitCount")
    private int hitCount;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public int getDefaultRowPrefetch() {
        return defaultRowPrefetch;
    }

    public void setDefaultRowPrefetch(int defaultRowPrefetch) {
        this.defaultRowPrefetch = defaultRowPrefetch;
    }

    public int getRowPrefetch() {
        return rowPrefetch;
    }

    public void setRowPrefetch(int rowPrefetch) {
        this.rowPrefetch = rowPrefetch;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
}
