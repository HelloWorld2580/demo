package com.dhcc.ms.ims.dto.response.druid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Function {
    @JsonProperty("name")
    private String name;
    @JsonProperty("invokeCount")
    private long invokeCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(long invokeCount) {
        this.invokeCount = invokeCount;
    }
}
