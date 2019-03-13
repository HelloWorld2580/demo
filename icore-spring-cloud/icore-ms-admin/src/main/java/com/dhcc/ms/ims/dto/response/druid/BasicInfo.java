package com.dhcc.ms.ims.dto.response.druid;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.codehaus.jackson.annotate.JsonMethod;

import java.util.List;

public class BasicInfo {

    private String appName;
    private String instanceId;

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

    @JsonProperty("Version")
    private String version;
    @JsonProperty("Drivers")
    private List<String> drivers;
    @JsonProperty("ResetEnable")
    private boolean resetEnable;
    @JsonProperty("ResetCount")
    private int resetCount;
    @JsonProperty("JavaVMName")
    private String javaVMName;
    @JsonProperty("JavaVersion")
    private String javaVersion;
    @JsonProperty("StartTime")
    private String startTime;
    @JsonProperty("JavaClassPath")
    private String javaClassPath;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<String> drivers) {
        this.drivers = drivers;
    }

    public boolean isResetEnable() {
        return resetEnable;
    }

    public void setResetEnable(boolean resetEnable) {
        this.resetEnable = resetEnable;
    }

    public int getResetCount() {
        return resetCount;
    }

    public void setResetCount(int resetCount) {
        this.resetCount = resetCount;
    }

    public String getJavaVMName() {
        return javaVMName;
    }

    public void setJavaVMName(String javaVMName) {
        this.javaVMName = javaVMName;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getJavaClassPath() {
        return javaClassPath;
    }

    public void setJavaClassPath(String javaClassPath) {
        this.javaClassPath = javaClassPath;
    }
}
