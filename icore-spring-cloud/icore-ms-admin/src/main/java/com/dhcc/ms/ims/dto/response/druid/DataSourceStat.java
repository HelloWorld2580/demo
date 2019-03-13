package com.dhcc.ms.ims.dto.response.druid;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataSourceStat {

    private String id;
    private String appName;
    private String instanceId;
    private String contextPath;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @JsonProperty("Identity")
    private  long identity;
    @JsonProperty("Name")
    private  String name;
    @JsonProperty("DbType")
    private  String dbType;
    @JsonProperty("DriverClassName")
    private  String driverClassName;

    @JsonProperty("URL")
    private  String url;
    @JsonProperty("UserName")
    private  String userName;
    @JsonProperty("FilterClassNames")
    private List<String> filterClassNames;

    @JsonProperty("WaitThreadCount")
    private  int waitThreadCount;
    @JsonProperty("NotEmptyWaitCount")
    private  long notEmptyWaitCount;
    @JsonProperty("NotEmptyWaitMillis")
    private  long notEmptyWaitMillis;

    @JsonProperty("PoolingCount")
    private  int poolingCount;
    @JsonProperty("PoolingPeak")
    private  int poolingPeak;
    @JsonProperty("PoolingPeakTime")
    private String poolingPeakTime;

    @JsonProperty("ActiveCount")
    private  int activeCount;
    @JsonProperty("ActivePeak")
    private  int activePeak;
    @JsonProperty("ActivePeakTime")
    private  String activePeakTime;

    @JsonProperty("InitialSize")
    private  int initialSize;
    @JsonProperty("MinIdle")
    private  int minIdle;
    @JsonProperty("MaxActive")
    private  int maxActive;

    @JsonProperty("QueryTimeout")
    private  int queryTimeout;
    @JsonProperty("TransactionQueryTimeout")
    private  int transactionQueryTimeout;
    @JsonProperty("LoginTimeout")
    private  int loginTimeout;
    @JsonProperty("ValidConnectionCheckerClassName")
    private  String validConnectionCheckerClassName;
    @JsonProperty("ExceptionSorterClassName")
    private  String exceptionSorterClassName;

    @JsonProperty("TestOnBorrow")
    private  boolean testOnBorrow;
    @JsonProperty("TestOnReturn")
    private  boolean testOnReturn;
    @JsonProperty("TestWhileIdle")
    private  boolean testWhileIdle;

    @JsonProperty("DefaultAutoCommit")
    private  boolean defaultAutoCommit;
    @JsonProperty("DefaultReadOnly")
    private  Boolean defaultReadOnly;
    @JsonProperty("DefaultTransactionIsolation")
    private  Integer defaultTransactionIsolation;

    @JsonProperty("LogicConnectCount")
    private  long logicConnectCount;
    @JsonProperty("LogicCloseCount")
    private  long logicCloseCount;
    @JsonProperty("LogicConnectErrorCount")
    private  long logicConnectErrorCount;

    @JsonProperty("PhysicalConnectCount")
    private  long physicalConnectCount;
    @JsonProperty("PhysicalCloseCount")
    private  long physicalCloseCount;
    @JsonProperty("PhysicalConnectErrorCount")
    private  long physicalConnectErrorCount;

    @JsonProperty("ExecuteCount")
    private  long executeCount;
    @JsonProperty("ErrorCount")
    private  long errorCount;
    @JsonProperty("CommitCount")
    private  long commitCount;
    @JsonProperty("RollbackCount")
    private  long rollbackCount;

    @JsonProperty("PSCacheAccessCount")
    private  long pSCacheAccessCount;
    @JsonProperty("PSCacheHitCount")
    private  long pSCacheHitCount;
    @JsonProperty("PSCacheMissCount")
    private  long pSCacheMissCount;

    @JsonProperty("StartTransactionCount")
    private  long startTransactionCount;
    @JsonProperty("TransactionHistogram")
    private  long[] transactionHistogram;

    @JsonProperty("ConnectionHoldTimeHistogram")
    private  long[] connectionHoldTimeHistogram;
    @JsonProperty("RemoveAbandoned")
    private  boolean removeAbandoned;
    @JsonProperty("ClobOpenCount")
    private  long clobOpenCount;
    @JsonProperty("BlobOpenCount")
    private  long blobOpenCount;
    @JsonProperty("KeepAliveCheckCount")
    private  long keepAliveCheckCount;

    @JsonProperty("KeepAlive")
    private  boolean keepAlive;
    @JsonProperty("FailFast")
    private  boolean failFast;
    @JsonProperty("MaxWait")
    private  long maxWait;
    @JsonProperty("MaxWaitThreadCount")
    private  int maxWaitThreadCount;
    @JsonProperty("PoolPreparedStatements")
    private  boolean poolPreparedStatements;
    @JsonProperty("MaxPoolPreparedStatementPerConnectionSize")
    private  int maxPoolPreparedStatementPerConnectionSize;
    @JsonProperty("MinEvictableIdleTimeMillis")
    private  long minEvictableIdleTimeMillis;
    @JsonProperty("MaxEvictableIdleTimeMillis")
    private  long maxEvictableIdleTimeMillis;

    @JsonProperty("LogDifferentThread")
    private  boolean logDifferentThread;
    @JsonProperty("RecycleErrorCount")
    private  long recycleErrorCount;
    @JsonProperty("PreparedStatementOpenCount")
    private  long PpreparedStatementOpenCount;
    @JsonProperty("PreparedStatementClosedCount")
    private  long preparedStatementClosedCount;

    @JsonProperty("UseUnfairLock")
    private  boolean useUnfairLock;
    @JsonProperty("InitGlobalVariants")
    private  boolean initGlobalVariants;
    @JsonProperty("InitVariants")
    private  boolean initVariants;

    public long getIdentity() {
        return identity;
    }

    public void setIdentity(long identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getFilterClassNames() {
        return filterClassNames;
    }

    public void setFilterClassNames(List<String> filterClassNames) {
        this.filterClassNames = filterClassNames;
    }

    public int getWaitThreadCount() {
        return waitThreadCount;
    }

    public void setWaitThreadCount(int waitThreadCount) {
        this.waitThreadCount = waitThreadCount;
    }

    public long getNotEmptyWaitCount() {
        return notEmptyWaitCount;
    }

    public void setNotEmptyWaitCount(long notEmptyWaitCount) {
        this.notEmptyWaitCount = notEmptyWaitCount;
    }

    public long getNotEmptyWaitMillis() {
        return notEmptyWaitMillis;
    }

    public void setNotEmptyWaitMillis(long notEmptyWaitMillis) {
        this.notEmptyWaitMillis = notEmptyWaitMillis;
    }

    public int getPoolingCount() {
        return poolingCount;
    }

    public void setPoolingCount(int poolingCount) {
        this.poolingCount = poolingCount;
    }

    public int getPoolingPeak() {
        return poolingPeak;
    }

    public void setPoolingPeak(int poolingPeak) {
        this.poolingPeak = poolingPeak;
    }

    public String getPoolingPeakTime() {
        return poolingPeakTime;
    }

    public void setPoolingPeakTime(String poolingPeakTime) {
        this.poolingPeakTime = poolingPeakTime;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    public int getActivePeak() {
        return activePeak;
    }

    public void setActivePeak(int activePeak) {
        this.activePeak = activePeak;
    }

    public String getActivePeakTime() {
        return activePeakTime;
    }

    public void setActivePeakTime(String activePeakTime) {
        this.activePeakTime = activePeakTime;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(int queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

    public int getTransactionQueryTimeout() {
        return transactionQueryTimeout;
    }

    public void setTransactionQueryTimeout(int transactionQueryTimeout) {
        this.transactionQueryTimeout = transactionQueryTimeout;
    }

    public int getLoginTimeout() {
        return loginTimeout;
    }

    public void setLoginTimeout(int loginTimeout) {
        this.loginTimeout = loginTimeout;
    }

    public String getValidConnectionCheckerClassName() {
        return validConnectionCheckerClassName;
    }

    public void setValidConnectionCheckerClassName(String validConnectionCheckerClassName) {
        this.validConnectionCheckerClassName = validConnectionCheckerClassName;
    }

    public String getExceptionSorterClassName() {
        return exceptionSorterClassName;
    }

    public void setExceptionSorterClassName(String exceptionSorterClassName) {
        this.exceptionSorterClassName = exceptionSorterClassName;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public boolean isDefaultAutoCommit() {
        return defaultAutoCommit;
    }

    public void setDefaultAutoCommit(boolean defaultAutoCommit) {
        this.defaultAutoCommit = defaultAutoCommit;
    }

    public Boolean getDefaultReadOnly() {
        return defaultReadOnly;
    }

    public void setDefaultReadOnly(Boolean defaultReadOnly) {
        this.defaultReadOnly = defaultReadOnly;
    }

    public Integer getDefaultTransactionIsolation() {
        return defaultTransactionIsolation;
    }

    public void setDefaultTransactionIsolation(Integer defaultTransactionIsolation) {
        this.defaultTransactionIsolation = defaultTransactionIsolation;
    }

    public long getLogicConnectCount() {
        return logicConnectCount;
    }

    public void setLogicConnectCount(long logicConnectCount) {
        this.logicConnectCount = logicConnectCount;
    }

    public long getLogicCloseCount() {
        return logicCloseCount;
    }

    public void setLogicCloseCount(long logicCloseCount) {
        this.logicCloseCount = logicCloseCount;
    }

    public long getLogicConnectErrorCount() {
        return logicConnectErrorCount;
    }

    public void setLogicConnectErrorCount(long logicConnectErrorCount) {
        this.logicConnectErrorCount = logicConnectErrorCount;
    }

    public long getPhysicalConnectCount() {
        return physicalConnectCount;
    }

    public void setPhysicalConnectCount(long physicalConnectCount) {
        this.physicalConnectCount = physicalConnectCount;
    }

    public long getPhysicalCloseCount() {
        return physicalCloseCount;
    }

    public void setPhysicalCloseCount(long physicalCloseCount) {
        this.physicalCloseCount = physicalCloseCount;
    }

    public long getPhysicalConnectErrorCount() {
        return physicalConnectErrorCount;
    }

    public void setPhysicalConnectErrorCount(long physicalConnectErrorCount) {
        this.physicalConnectErrorCount = physicalConnectErrorCount;
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

    public long getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(long commitCount) {
        this.commitCount = commitCount;
    }

    public long getRollbackCount() {
        return rollbackCount;
    }

    public void setRollbackCount(long rollbackCount) {
        this.rollbackCount = rollbackCount;
    }

    public long getpSCacheAccessCount() {
        return pSCacheAccessCount;
    }

    public void setpSCacheAccessCount(long pSCacheAccessCount) {
        this.pSCacheAccessCount = pSCacheAccessCount;
    }

    public long getpSCacheHitCount() {
        return pSCacheHitCount;
    }

    public void setpSCacheHitCount(long pSCacheHitCount) {
        this.pSCacheHitCount = pSCacheHitCount;
    }

    public long getpSCacheMissCount() {
        return pSCacheMissCount;
    }

    public void setpSCacheMissCount(long pSCacheMissCount) {
        this.pSCacheMissCount = pSCacheMissCount;
    }

    public long getStartTransactionCount() {
        return startTransactionCount;
    }

    public void setStartTransactionCount(long startTransactionCount) {
        this.startTransactionCount = startTransactionCount;
    }

    public long[] getTransactionHistogram() {
        return transactionHistogram;
    }

    public void setTransactionHistogram(long[] transactionHistogram) {
        this.transactionHistogram = transactionHistogram;
    }

    public long[] getConnectionHoldTimeHistogram() {
        return connectionHoldTimeHistogram;
    }

    public void setConnectionHoldTimeHistogram(long[] connectionHoldTimeHistogram) {
        this.connectionHoldTimeHistogram = connectionHoldTimeHistogram;
    }

    public boolean isRemoveAbandoned() {
        return removeAbandoned;
    }

    public void setRemoveAbandoned(boolean removeAbandoned) {
        this.removeAbandoned = removeAbandoned;
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

    public long getKeepAliveCheckCount() {
        return keepAliveCheckCount;
    }

    public void setKeepAliveCheckCount(long keepAliveCheckCount) {
        this.keepAliveCheckCount = keepAliveCheckCount;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isFailFast() {
        return failFast;
    }

    public void setFailFast(boolean failFast) {
        this.failFast = failFast;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    public int getMaxWaitThreadCount() {
        return maxWaitThreadCount;
    }

    public void setMaxWaitThreadCount(int maxWaitThreadCount) {
        this.maxWaitThreadCount = maxWaitThreadCount;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public long getMaxEvictableIdleTimeMillis() {
        return maxEvictableIdleTimeMillis;
    }

    public void setMaxEvictableIdleTimeMillis(long maxEvictableIdleTimeMillis) {
        this.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
    }

    public boolean isLogDifferentThread() {
        return logDifferentThread;
    }

    public void setLogDifferentThread(boolean logDifferentThread) {
        this.logDifferentThread = logDifferentThread;
    }

    public long getRecycleErrorCount() {
        return recycleErrorCount;
    }

    public void setRecycleErrorCount(long recycleErrorCount) {
        this.recycleErrorCount = recycleErrorCount;
    }

    public long getPpreparedStatementOpenCount() {
        return PpreparedStatementOpenCount;
    }

    public void setPpreparedStatementOpenCount(long ppreparedStatementOpenCount) {
        PpreparedStatementOpenCount = ppreparedStatementOpenCount;
    }

    public long getPreparedStatementClosedCount() {
        return preparedStatementClosedCount;
    }

    public void setPreparedStatementClosedCount(long preparedStatementClosedCount) {
        this.preparedStatementClosedCount = preparedStatementClosedCount;
    }

    public boolean isUseUnfairLock() {
        return useUnfairLock;
    }

    public void setUseUnfairLock(boolean useUnfairLock) {
        this.useUnfairLock = useUnfairLock;
    }

    public boolean isInitGlobalVariants() {
        return initGlobalVariants;
    }

    public void setInitGlobalVariants(boolean initGlobalVariants) {
        this.initGlobalVariants = initGlobalVariants;
    }

    public boolean isInitVariants() {
        return initVariants;
    }

    public void setInitVariants(boolean initVariants) {
        this.initVariants = initVariants;
    }
}
