package com.dhcc.ms.ims.dto.response.druid;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WallStat {
    @JsonProperty("checkCount")
    private long checkCount;
    @JsonProperty("hardCheckCount")
    private long hardCheckCount;
    @JsonProperty("violationCount")
    private long violationCount;
    @JsonProperty("violationEffectRowCount")
    private long violationEffectRowCount;
    @JsonProperty("blackListHitCount")
    private long blackListHitCount;
    @JsonProperty("blackListSize")
    private int blackListSize;
    @JsonProperty("whiteListHitCount")
    private long whiteListHitCount;
    @JsonProperty("whiteListSize")
    private int whiteListSize;
    @JsonProperty("syntaxErrorCount")
    private long syntaxErrorCount;

    @JsonProperty("tables")
    private List<Table> tables;
    @JsonProperty("functions")
    private List<Function> functions;
    @JsonProperty("whiteList")
    private List<WallSql> whiteList;
    @JsonProperty("blackList")
    private List<WallSql> blackList;

    public long getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(long checkCount) {
        this.checkCount = checkCount;
    }

    public long getHardCheckCount() {
        return hardCheckCount;
    }

    public void setHardCheckCount(long hardCheckCount) {
        this.hardCheckCount = hardCheckCount;
    }

    public long getViolationCount() {
        return violationCount;
    }

    public void setViolationCount(long violationCount) {
        this.violationCount = violationCount;
    }

    public long getViolationEffectRowCount() {
        return violationEffectRowCount;
    }

    public void setViolationEffectRowCount(long violationEffectRowCount) {
        this.violationEffectRowCount = violationEffectRowCount;
    }

    public long getBlackListHitCount() {
        return blackListHitCount;
    }

    public void setBlackListHitCount(long blackListHitCount) {
        this.blackListHitCount = blackListHitCount;
    }

    public int getBlackListSize() {
        return blackListSize;
    }

    public void setBlackListSize(int blackListSize) {
        this.blackListSize = blackListSize;
    }

    public long getWhiteListHitCount() {
        return whiteListHitCount;
    }

    public void setWhiteListHitCount(long whiteListHitCount) {
        this.whiteListHitCount = whiteListHitCount;
    }

    public int getWhiteListSize() {
        return whiteListSize;
    }

    public void setWhiteListSize(int whiteListSize) {
        this.whiteListSize = whiteListSize;
    }

    public long getSyntaxErrorCount() {
        return syntaxErrorCount;
    }

    public void setSyntaxErrorCount(long syntaxErrorCount) {
        this.syntaxErrorCount = syntaxErrorCount;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public List<WallSql> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<WallSql> whiteList) {
        this.whiteList = whiteList;
    }

    public List<WallSql> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<WallSql> blackList) {
        this.blackList = blackList;
    }
}

