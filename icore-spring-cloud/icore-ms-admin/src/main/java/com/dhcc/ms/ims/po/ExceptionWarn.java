package com.dhcc.ms.ims.po;

import javax.persistence.Table;

@Table(name = "msims_exceptions_warn")
public class ExceptionWarn {
    private String httpMethod;
    private String httpPath;
    private String error;
    private String traceId;
    private String timestamp;
    private String datetime;
    public String getHttpMethod() {
        return httpMethod;
    }
    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
    public String getHttpPath() {
        return httpPath;
    }
    public void setHttpPath(String httpPath) {
        this.httpPath = httpPath;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getTraceId() {
        return traceId;
    }
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getDatetime() {
        return datetime;
    }
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
