package com.dhcc.ms.ims.service.instance;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.po.InstanceInfo;

public interface InstanceWeightChangeService {

    void changeWeight(InstanceInfo updateInfo) throws CommonException;

    void asyncChangeWeight(InstanceInfo info);
}
