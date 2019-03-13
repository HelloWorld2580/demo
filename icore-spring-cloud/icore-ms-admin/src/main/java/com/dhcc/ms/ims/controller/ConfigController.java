package com.dhcc.ms.ims.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.ms.ims.dto.response.TurbineDtoResp;

@RestController
@RequestMapping("/config")
public class ConfigController {
  @Value("${turbine.aggregator.clusterConfig}")
  private String[] cluster;

  @Value("${turbine.appConfig}")
  private String[] app;

  private List<TurbineDtoResp> turbines = new ArrayList<TurbineDtoResp>();

  @RequestMapping(value = "/turbine", method = RequestMethod.GET)
  @ResponseBody
  public List<TurbineDtoResp> applications() {
    return turbines;
  }

  @PostConstruct
  public void init() {
    for (int i = 0; i < cluster.length; i++) {
      TurbineDtoResp turbine = new TurbineDtoResp();
      turbine.setApp(app[i]);
      turbine.setCluster(cluster[i]);
      turbines.add(turbine);
    }
  }
}
