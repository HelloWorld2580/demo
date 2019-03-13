package com.dhcc.ms.ims.service.instance.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.event.HeartbeatEvent;
import org.springframework.cloud.client.discovery.event.HeartbeatMonitor;
import org.springframework.cloud.client.discovery.event.InstanceRegisteredEvent;
import org.springframework.cloud.client.discovery.event.ParentHeartbeatEvent;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient.EurekaServiceInstance;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.po.InstanceInfo;
import com.dhcc.ms.ims.po.InstanceInfo.RegisteCenterType;
import com.dhcc.ms.ims.po.InstanceInfo.RunningStatus;
import com.dhcc.ms.ims.service.instance.InstanceWeightChangeService;
import com.dhcc.ms.ims.service.instance.InstancesService;
import com.dhcc.ms.ims.service.instance.impl.RegisteCenterSource.InstanceWeightChangedEvent;
import com.dhcc.ms.ims.utils.HashUtil;
import com.dhcc.ms.ims.utils.ImsErrorCodes;
import com.dhcc.ms.ribbon.TagAndWeightMetadataRule;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

public interface InstanceEventsHandler {
    void onInstanceRegistered(InstanceRegisteredEvent<?> event);

    void onApplicationReady(ApplicationReadyEvent event);

    void onParentHeartbeat(ParentHeartbeatEvent event);

    void onHeartbeat(HeartbeatEvent event);

    void onChangeWeight(InstanceWeightChangedEvent event);
}

@Component
class RegisteCenterSource implements InstanceEventsHandler, InstanceWeightChangeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisteCenterSource.class);

    private static final String METADATA_WEIGHT_KEY = TagAndWeightMetadataRule.META_DATA_KEY_WEIGHT;

    @Value("${eureka.client.serviceUrl.defaultZone}")
    private String[] eurekaServiceUrls;

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate rest;

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private InstancesService service;

    private final HeartbeatMonitor monitor = new HeartbeatMonitor();

    void refush() {
        List<InstanceInfo> infos = new ArrayList<InstanceInfo>();

        List<String> serviceIds = client.getServices();

        for (String serviceId : serviceIds) {
            List<ServiceInstance> instances = client.getInstances(serviceId);

            for (ServiceInstance instance : instances) {
                InstanceInfo info = createBaseInstanceInfo(instance);

                if (instance instanceof EurekaServiceInstance) {
                    handleEurekaServiceInstance((EurekaServiceInstance) instance, info);
                } else {
                    LOGGER.info("service {} (url {}) is not eureka instance.", instance.getServiceId(),
                            instance.getUri());
                    handleOtherServiceInstance(instance, info);
                }

                infos.add(info);
            }
        }

        service.updateInstances(infos.toArray(new InstanceInfo[0]));
    }

    private void handleEurekaServiceInstance(EurekaServiceInstance instance, InstanceInfo info) {
        com.netflix.appinfo.InstanceInfo instanceInfo = instance.getInstanceInfo();

        info.setRegisteId(instanceInfo.getInstanceId());

        String healthUrl = instanceInfo.getHealthCheckUrl();
        info.setServiceUrl(healthUrl.substring(0, healthUrl.lastIndexOf("/")));

        if (InstanceStatus.UP.equals(instanceInfo.getStatus())) {
            info.setStatus(RunningStatus.UP);
        } else if (InstanceStatus.UNKNOWN.equals(instanceInfo.getStatus())) {
            info.setStatus(RunningStatus.UNKNOWN);
        } else {
            info.setStatus(RunningStatus.DOWN);
        }

        info.setRegisteCenterType(RegisteCenterType.EUREKA);
    }

    private void handleOtherServiceInstance(ServiceInstance instance, InstanceInfo info) {
        info.setRegisteId(buildInstanceId(instance));

        info.setServiceUrl(instance.getUri().toString());

        info.setStatus(RunningStatus.UNKNOWN);

        info.setRegisteCenterType(RegisteCenterType.OTHER);
    }

    private InstanceInfo createBaseInstanceInfo(ServiceInstance instance) {
        InstanceInfo info = new InstanceInfo();

        info.setAppName(instance.getServiceId());
        info.setWeight(getWeight(instance));
        info.setId(HashUtil.generateSHA(buildInstanceId(instance)));

        return info;
    }

    private int getWeight(ServiceInstance instance) {
        String weightValue = instance.getMetadata().get(METADATA_WEIGHT_KEY);
        if (StringUtils.isEmpty(weightValue)) {
            return InstanceInfo.INITIAL_WEIGHT;
        } else {
            return Integer.parseInt(weightValue);
        }
    }

    private String buildInstanceId(ServiceInstance instance) {
        StringBuilder builder = new StringBuilder();

        builder.append(instance.getHost()).append(":").append(instance.getServiceId()).append(":")
                .append(instance.getPort());

        return builder.toString();
    }

    @Override
    public void changeWeight(InstanceInfo info) {
        if (RegisteCenterType.EUREKA == info.getRegisteCenterType()) {
            for (String eurekaUrl : eurekaServiceUrls) {
                try {
                    rest.put("{eurekaUrl}apps/{appName}/{instanceId}/metadata?{weightKey}={weightValue}", null,
                            eurekaUrl, info.getAppName(), info.getRegisteId(), METADATA_WEIGHT_KEY, info.getWeight());
                    return;
                } catch (Exception e) {
                    LOGGER.warn("there was a problem calling eureka service {} when changing weights", eurekaUrl, e);
                }
            }
        }

        LOGGER.warn("service {} is not eureka client,temporarily not supported Weight adjustment", info.getRegisteId());
        throw new CommonException(ImsErrorCodes.CODE_120300, ImsErrorCodes.CODE_120300_MSG);
    }

    @Override
    public void asyncChangeWeight(InstanceInfo info) {
        publisher.publishEvent(new InstanceWeightChangedEvent(info));
    }

    @Override
    @EventListener
    public void onInstanceRegistered(InstanceRegisteredEvent<?> event) {
        refush();
    }

    @Override
    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        refush();
    }

    @Override
    @EventListener
    public void onParentHeartbeat(ParentHeartbeatEvent event) {
        if (this.monitor.update(event.getValue())) {
            refush();
        }
    }

    @Override
    @EventListener
    public void onHeartbeat(HeartbeatEvent event) {
        if (this.monitor.update(event.getValue())) {
            refush();
        }
    }

    @Override
    @EventListener
    @Async
    public void onChangeWeight(InstanceWeightChangedEvent event) {
        changeWeight(event.getInfo());
    }

    @SuppressWarnings("serial")
    class InstanceWeightChangedEvent extends ApplicationEvent {
        private InstanceInfo info;

        public InstanceWeightChangedEvent(InstanceInfo info) {
            super(RegisteCenterSource.this);
            this.info = info;
        }

        public InstanceInfo getInfo() {
            return info;
        }

    }
}