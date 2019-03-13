package com.dhcc.ms.ims.service.impl.lcn.po;

import java.util.ArrayList;
import java.util.List;

public class TxGroup {

    private String groupId;

    private long startTime;

    private long nowTime;

    private int state;

    private int hasOver;

    /**
     * 补偿请求
     */
    private int isCommit;

    /**
     * 是否强制回滚(1:开启，0:关闭)
     */
    private int rollback = 0;

    private List<TxInfo> list;

    public TxGroup() {
        list = new ArrayList<TxInfo>();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<TxInfo> getList() {
        return list;
    }

    public void setList(List<TxInfo> list) {
        this.list = list;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getIsCommit() {
        return isCommit;
    }

    public void setIsCommit(int isCommit) {
        this.isCommit = isCommit;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void addTransactionInfo(TxInfo info) {
        list.add(info);
    }

    public long getNowTime() {
        return nowTime;
    }

    public void setNowTime(long nowTime) {
        this.nowTime = nowTime;
    }

    public int getHasOver() {
        return hasOver;
    }

    public void setHasOver(int hasOver) {
        this.hasOver = hasOver;
    }

    public int getRollback() {
        return rollback;
    }

    public void setRollback(int rollback) {
        this.rollback = rollback;
    }

}
