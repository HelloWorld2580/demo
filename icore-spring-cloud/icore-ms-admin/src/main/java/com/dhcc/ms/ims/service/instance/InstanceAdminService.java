
package com.dhcc.ms.ims.service.instance;

import java.io.InputStream;

import com.dhcc.ms.ims.dto.request.JolokiaDtoReq;
import com.dhcc.ms.ims.po.InstanceInfo;

public interface InstanceAdminService {

    Object health(InstanceInfo instance) throws Exception;

    Object env(InstanceInfo instance) throws Exception;

    Object metrics(InstanceInfo instance) throws Exception;

    Object trace(InstanceInfo instance) throws Exception;

    Object dump(InstanceInfo instance) throws Exception;

    Object info(InstanceInfo instance) throws Exception;

    Object configprops(InstanceInfo instance) throws Exception;

    Object loggers(InstanceInfo instance) throws Exception;

    Object jolokia(InstanceInfo instance, JolokiaDtoReq req) throws Exception;

    InputStream heapdump(InstanceInfo instance) throws Exception;

    Object resetEnv(InstanceInfo instance) throws Exception;

    Object setEnv(InstanceInfo instance, String propName, String propValue) throws Exception;

    Object loggers(InstanceInfo instance, String logger, String level) throws Exception;
}
