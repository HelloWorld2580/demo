package com.dhcc.ms.ims.dto.response.druid;

import com.netflix.appinfo.InstanceInfo;

import java.util.List;

public class DruidServiceList {
    private String name;
    private String instanceId;
    private String cluster;
    private String url;
    private InstanceInfo.InstanceStatus status;
    private int upSize;
    private int size;
    private String contextPath;
    private List<DruidServiceList> instances;


    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InstanceInfo.InstanceStatus getStatus() {
        return status;
    }

    public void setStatus(InstanceInfo.InstanceStatus status) {
        this.status = status;
    }

    public int getUpSize() {
        return upSize;
    }

    public void setUpSize(int upSize) {
        this.upSize = upSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<DruidServiceList> getInstances() {
        return instances;
    }

    public void setInstances(List<DruidServiceList> instances) {
        this.instances = instances;
    }
}
