package com.dhcc.ms.ims.dto.response.druid;

import com.dhcc.ms.ims.utils.HistogramUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Table {
    @JsonProperty("name")
    private String name;
    @JsonProperty("selectCount")
    private long selectCount;
    @JsonProperty("deleteCount")
    private long deleteCount;
    @JsonProperty("insertCount")
    private long insertCount;
    @JsonProperty("updateCount")
    private long updateCount;
    @JsonProperty("alterCount")
    private long alterCount;
    @JsonProperty("dropCount")
    private long dropCount;
    @JsonProperty("createCount")
    private long createCount;
    @JsonProperty("truncateCount")
    private long truncateCount;
    @JsonProperty("replaceCount")
    private long replaceCount;
    @JsonProperty("deleteDataCount")
    private long deleteDataCount;
    @JsonProperty("deleteDataCountHistogram")
    private long[] deleteDataCountHistogram;
    @JsonProperty("fetchRowCount")
    private long fetchRowCount;
    @JsonProperty("fetchRowCountHistogram")
    private long[] fetchRowCountHistogram;
    @JsonProperty("updateDataCount")
    private long updateDataCount;
    @JsonProperty("updateDataCountHistogram")
    private long[] updateDataCountHistogram;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSelectCount() {
        return selectCount;
    }

    public void setSelectCount(long selectCount) {
        this.selectCount = selectCount;
    }

    public long getDeleteCount() {
        return deleteCount;
    }

    public void setDeleteCount(long deleteCount) {
        this.deleteCount = deleteCount;
    }

    public long getInsertCount() {
        return insertCount;
    }

    public void setInsertCount(long insertCount) {
        this.insertCount = insertCount;
    }

    public long getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(long updateCount) {
        this.updateCount = updateCount;
    }

    public long getAlterCount() {
        return alterCount;
    }

    public void setAlterCount(long alterCount) {
        this.alterCount = alterCount;
    }

    public long getDropCount() {
        return dropCount;
    }

    public void setDropCount(long dropCount) {
        this.dropCount = dropCount;
    }

    public long getCreateCount() {
        return createCount;
    }

    public void setCreateCount(long createCount) {
        this.createCount = createCount;
    }

    public long getTruncateCount() {
        return truncateCount;
    }

    public void setTruncateCount(long truncateCount) {
        this.truncateCount = truncateCount;
    }

    public long getReplaceCount() {
        return replaceCount;
    }

    public void setReplaceCount(long replaceCount) {
        this.replaceCount = replaceCount;
    }

    public long getDeleteDataCount() {
        return deleteDataCount;
    }

    public void setDeleteDataCount(long deleteDataCount) {
        this.deleteDataCount = deleteDataCount;
    }

    public long[] getDeleteDataCountHistogram() {
        return deleteDataCountHistogram;
    }

    public void setDeleteDataCountHistogram(long[] deleteDataCountHistogram) {
        this.deleteDataCountHistogram = deleteDataCountHistogram;
    }

    public long getFetchRowCount() {
        return fetchRowCount;
    }

    public void setFetchRowCount(long fetchRowCount) {
        this.fetchRowCount = fetchRowCount;
    }

    public long[] getFetchRowCountHistogram() {
        return fetchRowCountHistogram;
    }

    public void setFetchRowCountHistogram(long[] fetchRowCountHistogram) {
        this.fetchRowCountHistogram = fetchRowCountHistogram;
    }

    public long getUpdateDataCount() {
        return updateDataCount;
    }

    public void setUpdateDataCount(long updateDataCount) {
        this.updateDataCount = updateDataCount;
    }

    public long[] getUpdateDataCountHistogram() {
        return updateDataCountHistogram;
    }

    public void setUpdateDataCountHistogram(long[] updateDataCountHistogram) {
        this.updateDataCountHistogram = updateDataCountHistogram;
    }

    public Table add(Table table) {
        this.selectCount                 +=table.getSelectCount();
        this.deleteCount                 +=table.getDeleteCount();
        this.insertCount                 +=table.getInsertCount();
        this.updateCount                 +=table.getUpdateCount();
        this.alterCount                  +=table.getAlterCount();
        this.dropCount                   +=table.getDropCount();
        this.createCount                 +=table.getCreateCount();
        this.truncateCount               +=table.getTruncateCount();
        this.replaceCount                +=table.getReplaceCount();
        this.deleteDataCount             +=table.getDeleteDataCount();
        this.fetchRowCount               +=table.getFetchRowCount();
        this.updateDataCount             +=table.getUpdateDataCount();

        this.fetchRowCountHistogram= HistogramUtil.add(this.fetchRowCountHistogram,table.getFetchRowCountHistogram());
        this.deleteDataCountHistogram = HistogramUtil.add(this.deleteDataCountHistogram,table.getDeleteDataCountHistogram());
        this.updateDataCountHistogram  = HistogramUtil.add(this.updateDataCountHistogram,table.getUpdateDataCountHistogram());

        return this;
    }
}
