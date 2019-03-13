
package com.dhcc.ms.ims.service.instance;

import com.dhcc.ms.ims.po.InstanceInfo;

public interface InstancesService {

    void doubledWeight(String... instanceId);

    void halfWeight(String... instanceId);

    void disable(String... instanceId);

    void enable(String... instanceId);

    void updateInstances(InstanceInfo... infos);
}
