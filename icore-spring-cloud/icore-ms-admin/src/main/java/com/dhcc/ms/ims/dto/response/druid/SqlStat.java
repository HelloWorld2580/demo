package com.dhcc.ms.ims.dto.response.druid;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.management.openmbean.CompositeData;

public class SqlStat {
    @JsonProperty("ID")
    private long id;
    @JsonProperty("DataSource")
    private String dataSource;
    @JsonProperty("SQL")
    private String sql;
    @JsonProperty("ExecuteCount")
    private long executeCount;
    @JsonProperty("ErrorCount")
    private long errorCount;

    @JsonProperty("TotalTime")
    private long totalTime;
    @JsonProperty("LastTime")
    private String lastTime;
    @JsonProperty("MaxTimespan")
    private long maxTimespan;
    @JsonProperty("LastError")
    private CompositeData lastError;
    @JsonProperty("EffectedRowCount")
    private long effectedRowCount;

    @JsonProperty("FetchRowCount")
    private long fetchRowCount;
    @JsonProperty("MaxTimespanOccurTime")
    private String maxTimespanOccurTime;
    @JsonProperty("BatchSizeMax")
    private long batchSizeMax;
    @JsonProperty("BatchSizeTotal")
    private long batchSizeTotal;
    @JsonProperty("ConcurrentMax")
    private long concurrentMax;

    @JsonProperty("RunningCount")
    private long runningCount;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("File")
    private String file;

    @JsonProperty("LastErrorMessage")
    private String lastErrorMessage;
    @JsonProperty("LastErrorClass")
    private String lastErrorClass;
    @JsonProperty("LastErrorStackTrace")
    private String lastErrorStackTrace;
    @JsonProperty("LastErrorTime")
    private String lastErrorTime;

    @JsonProperty("DbType")
    private String dbType;
    @JsonProperty("URL")
    private String url;
    @JsonProperty("InTransactionCount")
    private long inTransactionCount;

    @JsonProperty("Histogram")
    private long[] histogram;
    @JsonProperty("LastSlowParameters")
    private String lastSlowParameters;
    @JsonProperty("ResultSetHoldTime")
    private long resultSetHoldTime;
    @JsonProperty("ExecuteAndResultSetHoldTime")
    private long executeAndResultSetHoldTime;
    @JsonProperty("FetchRowCountHistogram")
    private long[] fetchRowCountHistogram;

    @JsonProperty("EffectedRowCountHistogram")
    private long[] effectedRowCountHistogram;
    @JsonProperty("ExecuteAndResultHoldTimeHistogram")
    private long[] executeAndResultHoldTimeHistogram;
    @JsonProperty("EffectedRowCountMax")
    private long effectedRowCountMax;
    @JsonProperty("FetchRowCountMax")
    private long fetchRowCountMax;
    @JsonProperty("ClobOpenCount")
    private long clobOpenCount;

    @JsonProperty("BlobOpenCount")
    private long blobOpenCount;
    @JsonProperty("ReadStringLength")
    private long readStringLength;
    @JsonProperty("ReadBytesLength")
    private long readBytesLength;
    @JsonProperty("InputStreamOpenCount")
    private long inputStreamOpenCount;
    @JsonProperty("ReaderOpenCount")
    private long readerOpenCount;

    @JsonProperty("HASH")
    private long hash;

    @JsonProperty("formattedSql")
    private String formattedSql;
    @JsonProperty("parsedTable")
    private String parsedTable;
    @JsonProperty("parsedFields")
    private String parsedFields;
    @JsonProperty("parsedRelationships")
    private String parsedRelationships;
    @JsonProperty("parsedConditions")
    private String parsedConditions;
    @JsonProperty("parsedOrderbycolumns")
    private String parsedOrderbycolumns;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public long getExecuteCount() {
        return executeCount;
    }

    public void setExecuteCount(long executeCount) {
        this.executeCount = executeCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public long getMaxTimespan() {
        return maxTimespan;
    }

    public void setMaxTimespan(long maxTimespan) {
        this.maxTimespan = maxTimespan;
    }

    public CompositeData getLastError() {
        return lastError;
    }

    public void setLastError(CompositeData lastError) {
        this.lastError = lastError;
    }

    public long getEffectedRowCount() {
        return effectedRowCount;
    }

    public void setEffectedRowCount(long effectedRowCount) {
        this.effectedRowCount = effectedRowCount;
    }

    public long getFetchRowCount() {
        return fetchRowCount;
    }

    public void setFetchRowCount(long fetchRowCount) {
        this.fetchRowCount = fetchRowCount;
    }

    public String getMaxTimespanOccurTime() {
        return maxTimespanOccurTime;
    }

    public void setMaxTimespanOccurTime(String maxTimespanOccurTime) {
        this.maxTimespanOccurTime = maxTimespanOccurTime;
    }

    public long getBatchSizeMax() {
        return batchSizeMax;
    }

    public void setBatchSizeMax(long batchSizeMax) {
        this.batchSizeMax = batchSizeMax;
    }

    public long getBatchSizeTotal() {
        return batchSizeTotal;
    }

    public void setBatchSizeTotal(long batchSizeTotal) {
        this.batchSizeTotal = batchSizeTotal;
    }

    public long getConcurrentMax() {
        return concurrentMax;
    }

    public void setConcurrentMax(long concurrentMax) {
        this.concurrentMax = concurrentMax;
    }

    public long getRunningCount() {
        return runningCount;
    }

    public void setRunningCount(long runningCount) {
        this.runningCount = runningCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }

    public String getLastErrorClass() {
        return lastErrorClass;
    }

    public void setLastErrorClass(String lastErrorClass) {
        this.lastErrorClass = lastErrorClass;
    }

    public String getLastErrorStackTrace() {
        return lastErrorStackTrace;
    }

    public void setLastErrorStackTrace(String lastErrorStackTrace) {
        this.lastErrorStackTrace = lastErrorStackTrace;
    }

    public String getLastErrorTime() {
        return lastErrorTime;
    }

    public void setLastErrorTime(String lastErrorTime) {
        this.lastErrorTime = lastErrorTime;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getInTransactionCount() {
        return inTransactionCount;
    }

    public void setInTransactionCount(long inTransactionCount) {
        this.inTransactionCount = inTransactionCount;
    }

    public long[] getHistogram() {
        return histogram;
    }

    public void setHistogram(long[] histogram) {
        this.histogram = histogram;
    }

    public String getLastSlowParameters() {
        return lastSlowParameters;
    }

    public void setLastSlowParameters(String lastSlowParameters) {
        this.lastSlowParameters = lastSlowParameters;
    }

    public long getResultSetHoldTime() {
        return resultSetHoldTime;
    }

    public void setResultSetHoldTime(long resultSetHoldTime) {
        this.resultSetHoldTime = resultSetHoldTime;
    }

    public long getExecuteAndResultSetHoldTime() {
        return executeAndResultSetHoldTime;
    }

    public void setExecuteAndResultSetHoldTime(long executeAndResultSetHoldTime) {
        this.executeAndResultSetHoldTime = executeAndResultSetHoldTime;
    }

    public long[] getFetchRowCountHistogram() {
        return fetchRowCountHistogram;
    }

    public void setFetchRowCountHistogram(long[] fetchRowCountHistogram) {
        this.fetchRowCountHistogram = fetchRowCountHistogram;
    }

    public long[] getEffectedRowCountHistogram() {
        return effectedRowCountHistogram;
    }

    public void setEffectedRowCountHistogram(long[] effectedRowCountHistogram) {
        this.effectedRowCountHistogram = effectedRowCountHistogram;
    }

    public long[] getExecuteAndResultHoldTimeHistogram() {
        return executeAndResultHoldTimeHistogram;
    }

    public void setExecuteAndResultHoldTimeHistogram(long[] executeAndResultHoldTimeHistogram) {
        this.executeAndResultHoldTimeHistogram = executeAndResultHoldTimeHistogram;
    }

    public long getEffectedRowCountMax() {
        return effectedRowCountMax;
    }

    public void setEffectedRowCountMax(long effectedRowCountMax) {
        this.effectedRowCountMax = effectedRowCountMax;
    }

    public long getFetchRowCountMax() {
        return fetchRowCountMax;
    }

    public void setFetchRowCountMax(long fetchRowCountMax) {
        this.fetchRowCountMax = fetchRowCountMax;
    }

    public long getClobOpenCount() {
        return clobOpenCount;
    }

    public void setClobOpenCount(long clobOpenCount) {
        this.clobOpenCount = clobOpenCount;
    }

    public long getBlobOpenCount() {
        return blobOpenCount;
    }

    public void setBlobOpenCount(long blobOpenCount) {
        this.blobOpenCount = blobOpenCount;
    }

    public long getReadStringLength() {
        return readStringLength;
    }

    public void setReadStringLength(long readStringLength) {
        this.readStringLength = readStringLength;
    }

    public long getReadBytesLength() {
        return readBytesLength;
    }

    public void setReadBytesLength(long readBytesLength) {
        this.readBytesLength = readBytesLength;
    }

    public long getInputStreamOpenCount() {
        return inputStreamOpenCount;
    }

    public void setInputStreamOpenCount(long inputStreamOpenCount) {
        this.inputStreamOpenCount = inputStreamOpenCount;
    }

    public long getReaderOpenCount() {
        return readerOpenCount;
    }

    public void setReaderOpenCount(long readerOpenCount) {
        this.readerOpenCount = readerOpenCount;
    }

    public long getHash() {
        return hash;
    }

    public void setHash(long hash) {
        this.hash = hash;
    }

    public String getFormattedSql() {
        return formattedSql;
    }

    public void setFormattedSql(String formattedSql) {
        this.formattedSql = formattedSql;
    }

    public String getParsedTable() {
        return parsedTable;
    }

    public void setParsedTable(String parsedTable) {
        this.parsedTable = parsedTable;
    }

    public String getParsedFields() {
        return parsedFields;
    }

    public void setParsedFields(String parsedFields) {
        this.parsedFields = parsedFields;
    }

    public String getParsedRelationships() {
        return parsedRelationships;
    }

    public void setParsedRelationships(String parsedRelationships) {
        this.parsedRelationships = parsedRelationships;
    }

    public String getParsedConditions() {
        return parsedConditions;
    }

    public void setParsedConditions(String parsedConditions) {
        this.parsedConditions = parsedConditions;
    }

    public String getParsedOrderbycolumns() {
        return parsedOrderbycolumns;
    }

    public void setParsedOrderbycolumns(String parsedOrderbycolumns) {
        this.parsedOrderbycolumns = parsedOrderbycolumns;
    }
}
