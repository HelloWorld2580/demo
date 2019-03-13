package com.dhcc.ms.ims.service.instance.impl;

import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dto.request.JolokiaDtoReq;
import com.dhcc.ms.ims.po.InstanceInfo;
import com.dhcc.ms.ims.service.instance.InstanceAdminService;

@Service
public class InstanceAdminServiceImpl implements InstanceAdminService {

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate rest;

    @Override
    public Object health(InstanceInfo instance) throws Exception {
        return getServerInfo(instance, "/health");
    }

    @Override
    public Object env(InstanceInfo instance) throws Exception {
        return getServerInfo(instance, "/env");
    }

    /**
     * 修改参数配置
     * 
     * @param instance
     * @param propName
     * @param propValue
     * @return
     * @throws Exception
     */
    @Override
    public Object setEnv(InstanceInfo instance, String propName, String propValue) throws Exception {
        Map<String, String> configruration = new HashMap<String, String>();
        configruration.put(propName, propValue);
        return postServerInfo(instance, "/env", configruration);
    }

    /**
     * 重置
     * 
     * @param instance
     * @return
     * @throws Exception
     */
    @Override
    public Object resetEnv(InstanceInfo instance) throws Exception {
        return getServerInfo(instance, "/env/reset");
    }

    @Override
    public Object metrics(InstanceInfo instance) throws Exception {
        return getServerInfo(instance, "/metrics");
    }

    @Override
    public Object trace(InstanceInfo instance) throws Exception {
        return getServerInfo(instance, "/trace");
    }

    @Override
    public Object dump(InstanceInfo instance) throws Exception {
        return getServerInfo(instance, "/dump");
    }

    @Override
    public Object info(InstanceInfo instance) throws Exception {
        return getServerInfo(instance, "/info");
    }

    @Override
    public Object configprops(InstanceInfo instance) throws Exception {
        return getServerInfo(instance, "/configprops");
    }

    @Override
    public Object loggers(InstanceInfo instance) throws Exception {
        return getServerInfo(instance, "/loggers");
    }

    @Override
    public Object loggers(InstanceInfo instance, String logger, String level) throws Exception {
        Map<String, String> configruration = new HashMap<String, String>();
        configruration.put("configuredLevel", level);
        return postServerInfo(instance, "/loggers/" + logger, configruration);
    }

    @Override
    public Object jolokia(InstanceInfo instance, JolokiaDtoReq req) throws Exception {
        return postServerInfo(instance, "/jolokia", req);
    }

    @Override
    public InputStream heapdump(InstanceInfo instance) throws Exception {
        URLConnection connection = buildUrl(instance, "/heapdump").toURL().openConnection();
        return connection.getInputStream();
    }

    private Object getServerInfo(InstanceInfo instance, String infoType) throws Exception {
        URI url = buildUrl(instance, infoType);

        return rest.getForObject(url, Object.class);
    }

    private Object postServerInfo(InstanceInfo instance, String infoType, Object req) throws Exception {
        URI url = buildUrl(instance, infoType);

        return rest.postForObject(url, req, String.class);
    }

    private URI buildUrl(InstanceInfo instance, String infoType) throws CommonException {
        return UriComponentsBuilder.fromUriString(instance.getServiceUrl()).path(infoType).build().toUri();
    }
}
