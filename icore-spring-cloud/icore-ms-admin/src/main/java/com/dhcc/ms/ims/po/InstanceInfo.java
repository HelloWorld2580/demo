package com.dhcc.ms.ims.po;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "msims_instance_info")
public class InstanceInfo {
    public static final int INITIAL_WEIGHT = 100;
    public static final int DISABLE_WEIGHT = 0;
    public static final int WEIGHT_RATE = 2;

    @Id
    private String id;

    private String appName;

    private String registeId;

    private String serviceUrl;

    private RunningStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date statusStartTimestamp;

    private int weight;

    private RegisteCenterType registeCenterType;

    public RegisteCenterType getRegisteCenterType() {
        return registeCenterType;
    }

    public void setRegisteCenterType(RegisteCenterType registeCenterType) {
        this.registeCenterType = registeCenterType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getRegisteId() {
        return registeId;
    }

    public void setRegisteId(String registeId) {
        this.registeId = registeId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public RunningStatus getStatus() {
        return status;
    }

    public void setStatus(RunningStatus status) {
        this.status = status;
    }

    public Date getStatusStartTimestamp() {
        return statusStartTimestamp;
    }

    public void setStatusStartTimestamp(Date statusStartTimestamp) {
        this.statusStartTimestamp = statusStartTimestamp;
    }

    public static enum RegisteCenterType {
        /**
         * eureka注册中心
         */
        EUREKA,

        /**
         * 未知注册中心
         */
        OTHER
    }

    public static enum RunningStatus {
        /**
         * 正常运行
         */
        UP,

        /**
         * 已宕机
         */
        DOWN,

        /**
         * 未知状态
         */
        UNKNOWN
    }
}
