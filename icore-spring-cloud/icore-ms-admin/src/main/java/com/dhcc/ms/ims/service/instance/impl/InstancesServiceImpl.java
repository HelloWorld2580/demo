package com.dhcc.ms.ims.service.instance.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.po.InstanceInfo;
import com.dhcc.ms.ims.service.instance.InstancesFinder;
import com.dhcc.ms.ims.service.instance.InstancesService;
import com.dhcc.ms.ims.utils.ImsErrorCodes;

@Service
public class InstancesServiceImpl implements InstancesService {

    @Autowired
    private InstancesFinder finder;

    @Autowired
    private Instance instance;

    @Override
    public void doubledWeight(String... instanceIds) {
        boolean hasException = false;

        for (String id : instanceIds) {
            try {
                instance.doubledWeight(id);
            } catch (Exception e) {
                hasException = true;
            }
        }

        if (hasException) {
            throw new CommonException(ImsErrorCodes.CODE_120300, ImsErrorCodes.CODE_120300_MSG);
        }
    }

    @Override
    public void halfWeight(String... instanceIds) {
        boolean hasException = false;

        for (String id : instanceIds) {
            try {
                instance.halfWeight(id);
            } catch (Exception e) {
                hasException = true;
            }
        }

        if (hasException) {
            throw new CommonException(ImsErrorCodes.CODE_120300, ImsErrorCodes.CODE_120300_MSG);
        }
    }

    @Override
    public void disable(String... instanceIds) {
        boolean hasException = false;

        for (String id : instanceIds) {
            try {
                instance.disable(id);
            } catch (Exception e) {
                hasException = true;
            }
        }

        if (hasException) {
            throw new CommonException(ImsErrorCodes.CODE_120300, ImsErrorCodes.CODE_120300_MSG);
        }
    }

    @Override
    public void enable(String... instanceIds) {
        boolean hasException = false;

        for (String id : instanceIds) {
            try {
                instance.enable(id);
            } catch (Exception e) {
                hasException = true;
            }
        }

        if (hasException) {
            throw new CommonException(ImsErrorCodes.CODE_120300, ImsErrorCodes.CODE_120300_MSG);
        }
    }

    @Override
    @Transactional
    public void updateInstances(InstanceInfo... infos) {
        Set<String> ids = finder.allInstanceIds();

        for (InstanceInfo info : infos) {
            ids.remove(info.getId());
            instance.updateInfo(info);
        }

        for (String id : ids) {
            instance.updateInfo(new DownInstanceInfo(id));
        }
    }

    class DownInstanceInfo extends InstanceInfo {
        public DownInstanceInfo(String id) {
            this.setId(id);
            this.setStatus(RunningStatus.DOWN);
        }
    }

}
