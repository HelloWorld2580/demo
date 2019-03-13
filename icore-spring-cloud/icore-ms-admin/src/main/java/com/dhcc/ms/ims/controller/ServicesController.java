package com.dhcc.ms.ims.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.ms.ims.dto.request.ExceptionWarnDtoPageReq;
import com.dhcc.ms.ims.dto.request.JolokiaDtoReq;
import com.dhcc.ms.ims.dto.request.ServicesWeightDtoReq;
import com.dhcc.ms.ims.po.InstanceInfo;
import com.dhcc.ms.ims.service.instance.InstanceAdminService;
import com.dhcc.ms.ims.service.instance.InstancesFinder;
import com.dhcc.ms.ims.service.instance.InstancesService;
import com.dhcc.ms.utils.dto.BaseMetaDto;
import com.dhcc.ms.utils.dto.ListRespDto;

@RestController
@RequestMapping("/services")
public class ServicesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServicesController.class);

    @Autowired
    private InstancesFinder finder;

    @Autowired
    private InstancesService operateService;

    @Autowired
    private InstanceAdminService adminService;

    @RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
    @ResponseBody
    public ListRespDto<Map<String, Object>> applications(@RequestBody ExceptionWarnDtoPageReq page) {
        List<Map<String, Object>> respList = new ArrayList<Map<String, Object>>();

        ListRespDto<InstanceInfo> infos = finder.instances(page);
        for (InstanceInfo instance : infos.getList()) {
            respList.add(buildInfo(instance));
        }
        return new ListRespDto<Map<String, Object>>(respList, infos.getTotalCount());
    }

    @HystrixCommand
    @RequestMapping(value = "/weightinstances", method = RequestMethod.POST)
    @ResponseBody
    public ListRespDto<Map<String, Object>> weightinstances(@RequestBody ExceptionWarnDtoPageReq page) {
        List<Map<String, Object>> respList = new ArrayList<Map<String, Object>>();

        ListRespDto<InstanceInfo> infos = finder.instancesWeight(page);
        for (InstanceInfo instance : infos.getList()) {
            respList.add(buildInfo(instance));
        }
        return new ListRespDto<Map<String, Object>>(respList, infos.getTotalCount());
    }

    private Map<String, Object> buildInfo(InstanceInfo instance) {
        Map<String, Object> info = new HashMap<String, Object>(6);
        info.put("INSTANCE_ID", instance.getId());
        info.put("APPLICATION_NAME", instance.getAppName());
        info.put("REGISTE_ID", instance.getRegisteId());
        info.put("SERVICE_URL", instance.getServiceUrl());
        info.put("STATUS", instance.getStatus().name());
        info.put("WEIGHT", instance.getWeight());

        try {
            info.put("INFO", info(instance.getId()));
        } catch (Exception e) {
            LOGGER.info("an exception occurs when the service [{}] is called", info.get("SERVICE_URL"), e);
            info.put("INFO", "an exception occurs when the service is called");
        }

        return info;
    }

    @RequestMapping(value = "/weight/double", method = RequestMethod.POST)
    @ResponseBody
    public BaseMetaDto doubledWeight(@RequestBody ServicesWeightDtoReq servicesReq) {
        operateService.doubledWeight(servicesReq.getInstanceIds());
        return new BaseMetaDto();
    }

    @RequestMapping(value = "/weight/half", method = RequestMethod.POST)
    @ResponseBody
    public BaseMetaDto halfWeight(@RequestBody ServicesWeightDtoReq servicesReq) {
        operateService.halfWeight(servicesReq.getInstanceIds());
        return new BaseMetaDto();
    }

    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    @ResponseBody
    public BaseMetaDto disable(@RequestBody ServicesWeightDtoReq servicesReq) {
        operateService.disable(servicesReq.getInstanceIds());
        return new BaseMetaDto();
    }

    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    @ResponseBody
    public BaseMetaDto enable(@RequestBody ServicesWeightDtoReq servicesReq) {
        operateService.enable(servicesReq.getInstanceIds());
        return new BaseMetaDto();
    }

    @RequestMapping(value = "/{id}/health", method = RequestMethod.GET)
    @ResponseBody
    public Object health(@PathVariable String id) throws Exception {
        return adminService.health(finder.instance(id));
    }

    @RequestMapping(value = "/{id}/env", method = RequestMethod.GET)
    @ResponseBody
    public Object env(@PathVariable String id) throws Exception {
        return adminService.env(finder.instance(id));
    }

    @RequestMapping(value = "/{id}/metrics", method = RequestMethod.GET)
    @ResponseBody
    public Object metrics(@PathVariable String id) throws Exception {
        return adminService.metrics(finder.instance(id));
    }

    @RequestMapping(value = "/{id}/trace", method = RequestMethod.GET)
    @ResponseBody
    public Object trace(@PathVariable String id) throws Exception {
        return adminService.trace(finder.instance(id));
    }

    @RequestMapping(value = "/{id}/dump", method = RequestMethod.GET)
    @ResponseBody
    public Object dump(@PathVariable String id) throws Exception {
        return adminService.dump(finder.instance(id));
    }

    @RequestMapping(value = "/{id}/info", method = RequestMethod.GET)
    @ResponseBody
    public Object info(@PathVariable String id) throws Exception {
        return adminService.info(finder.instance(id));
    }

    @RequestMapping(value = "/{id}/configprops", method = RequestMethod.GET)
    @ResponseBody
    public Object configprops(@PathVariable String id) throws Exception {
        return adminService.configprops(finder.instance(id));
    }

    @RequestMapping(value = "/{id}/loggers", method = RequestMethod.GET)
    @ResponseBody
    public Object loggers(@PathVariable String id) throws Exception {
        return adminService.loggers(finder.instance(id));
    }

    @RequestMapping(value = "/{id}/jolokia", method = RequestMethod.POST)
    @ResponseBody
    public Object jolokia(@PathVariable String id, @RequestBody JolokiaDtoReq req) throws Exception {
        return adminService.jolokia(finder.instance(id), req);
    }

    @RequestMapping(value = "/{id}/setenv", method = RequestMethod.POST)
    @ResponseBody
    public Object setEnv(@PathVariable String id, @RequestBody Map<String, String> param) throws Exception {
        String propName = param.get("propName");
        String propValue = param.get("propValue");
        return adminService.setEnv(finder.instance(id), propName, propValue);
    }

    @RequestMapping(value = "/{id}/resetenv", method = RequestMethod.GET)
    @ResponseBody
    public Object resetEnv(@PathVariable String id) throws Exception {
        return adminService.env(finder.instance(id));
    }

    /**
     * 设置日志级别
     * 
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{id}/setloggers", method = RequestMethod.POST)
    @ResponseBody
    public Object loggers(@PathVariable String id, @RequestBody Map<String, String> req) throws Exception {
        String configuredLevel = req.get("configuredLevel");
        String logger = req.get("logger");
        return adminService.loggers(finder.instance(id), logger, configuredLevel);

    }

    @RequestMapping(value = "/{id}/heapdump", method = RequestMethod.GET)
    public void heapdump(@PathVariable String id, HttpServletResponse response) throws Exception {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = adminService.heapdump(finder.instance(id));

            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            while (in.read(buffer) > 0) {
                out.write(buffer);
                out.flush();
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
