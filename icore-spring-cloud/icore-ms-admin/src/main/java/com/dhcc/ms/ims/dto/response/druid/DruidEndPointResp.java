package com.dhcc.ms.ims.dto.response.druid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DruidEndPointResp <T> {
    @JsonProperty("ResultCode")
    private int ResultCode;
    @JsonProperty("Content")
    private T content;

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int resultCode) {
        ResultCode = resultCode;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
