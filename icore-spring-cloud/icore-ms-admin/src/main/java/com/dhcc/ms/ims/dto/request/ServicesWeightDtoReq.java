
package com.dhcc.ms.ims.dto.request;

import javax.validation.constraints.NotNull;

public class ServicesWeightDtoReq {
    @NotNull
    private String[] instanceIds = {};

    public String[] getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(String[] instanceIds) {
        this.instanceIds = instanceIds;
    }
}
