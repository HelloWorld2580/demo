package com.dhcc.ms.ims.service.instance.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.ms.ims.dao.InstanceInfoDao;
import com.dhcc.ms.ims.po.InstanceInfo;
import com.dhcc.ms.ims.po.InstanceInfo.RunningStatus;
import com.dhcc.ms.ims.service.instance.InstanceWeightChangeService;

@Component
public class Instance {

    @Value("${com.dhcc.ms.ims.instance.invalidTimeout:86400000}")
    private long invalidTimeout;

    @Autowired
    private InstanceInfoDao dao;

    @Autowired
    private InstanceWeightChangeService service;

    @Transactional
    public void doubledWeight(String id) {
        InstanceInfo info = getInstanceInfo(id);

        int weight = info.getWeight();
        updateWeight(info, weight * InstanceInfo.WEIGHT_RATE);
    }

    @Transactional
    public void halfWeight(String id) {
        InstanceInfo info = getInstanceInfo(id);

        int weight = info.getWeight();
        if (weight > InstanceInfo.INITIAL_WEIGHT) {
            updateWeight(info, weight / InstanceInfo.WEIGHT_RATE);
        }
    }

    @Transactional
    public void disable(String id) {
        InstanceInfo info = getInstanceInfo(id);

        updateWeight(info, InstanceInfo.DISABLE_WEIGHT);
    }

    @Transactional
    public void enable(String id) {
        InstanceInfo info = getInstanceInfo(id);

        int weight = info.getWeight();
        if (InstanceInfo.DISABLE_WEIGHT == weight) {
            updateWeight(info, InstanceInfo.INITIAL_WEIGHT);
        }
    }

    private InstanceInfo getInstanceInfo(String id) {
        return dao.selectByPrimaryKey(id);
    }

    private void updateWeight(InstanceInfo info, int weight) {
        info.setWeight(weight);
        service.changeWeight(info);
        dao.updateByPrimaryKey(info);
    }

    public void updateInfo(InstanceInfo updateInfo) {
        InstanceInfo info = getInstanceInfo(updateInfo.getId());

        Date now = new Date();

        boolean exist = info != null;
        if (!exist) {
            updateInfo.setStatusStartTimestamp(now);
            service.asyncChangeWeight(updateInfo);
            dao.insert(updateInfo);
            return;
        }

        boolean statusChange = !info.getStatus().equals(updateInfo.getStatus());
        if (statusChange) {
            info.setStatus(updateInfo.getStatus());
            info.setStatusStartTimestamp(now);
            dao.updateByPrimaryKey(info);
        }

        if (RunningStatus.DOWN.equals(info.getStatus())) {
            if (info.getStatusStartTimestamp().getTime() + invalidTimeout < now.getTime()) {
                dao.deleteByPrimaryKey(info.getId());
            }
            return;
        }

        boolean weightEffective = info.getWeight() == updateInfo.getWeight();
        if (!weightEffective) {
            service.asyncChangeWeight(info);
        }
    }

}
