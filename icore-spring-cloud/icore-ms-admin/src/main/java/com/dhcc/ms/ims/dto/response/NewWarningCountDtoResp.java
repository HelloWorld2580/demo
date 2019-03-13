package com.dhcc.ms.ims.dto.response;

public class NewWarningCountDtoResp {
    private int newExceptionCount;
    private int newServiceDownCount;
    private int newInconsistentCount;

    private long startTimestamp;
    private long endTimestamp;

    public int getNewExceptionCount() {
        return newExceptionCount;
    }

    public void setNewExceptionCount(int newExceptionCount) {
        this.newExceptionCount = newExceptionCount;
    }

    public int getNewServiceDownCount() {
        return newServiceDownCount;
    }

    public void setNewServiceDownCount(int newServiceWariningCount) {
        this.newServiceDownCount = newServiceWariningCount;
    }

    public int getNewInconsistentCount() {
        return newInconsistentCount;
    }

    public void setNewInconsistentCount(int newInconsistentCount) {
        this.newInconsistentCount = newInconsistentCount;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public long getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(long endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
}
