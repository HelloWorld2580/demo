package com.dhcc.ms.ims.service.impl;

import com.dhcc.ms.ims.dto.request.druid.DataSourceTarget;
import com.dhcc.ms.ims.dto.request.druid.InstanceTarget;
import com.dhcc.ms.ims.dto.request.druid.SqlTarget;
import com.dhcc.ms.ims.dto.request.druid.WallTarget;
import com.dhcc.ms.ims.dto.response.druid.*;
import com.dhcc.ms.ims.service.DruidMoniterService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 从各服务的druid jsonapi中获取数据进行二次处理
 * <p>
 * 对于聚合的功能，就改为使用多线程以优化速度
 */
@Service
public class DruidMoniterServiceImpl implements DruidMoniterService {

    private static final Logger LOG = LoggerFactory.getLogger(DruidMoniterServiceImpl.class);


    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private RestTemplate restTemplate;

    private final ParameterizedTypeReference BASIC_INFO_TYPE = new ParameterizedTypeReference<DruidEndPointResp<BasicInfo>>() {
    };
    private final ParameterizedTypeReference DATASOURCE_STAT_TYPE = new ParameterizedTypeReference<DruidEndPointResp<DataSourceStat>>() {
    };
    private final ParameterizedTypeReference DATASOURCE_STAT_LIST_TYPE = new ParameterizedTypeReference<DruidEndPointResp<List<DataSourceStat>>>() {
    };
    private final ParameterizedTypeReference CONNECTION_INFO_LIST_TYPE = new ParameterizedTypeReference<DruidEndPointResp<List<ConnectionInfo>>>() {
    };
    private final ParameterizedTypeReference SQL_STAT_TYPE = new ParameterizedTypeReference<DruidEndPointResp<SqlStat>>() {
    };
    private final ParameterizedTypeReference SQL_STAT_LIST_TYPE = new ParameterizedTypeReference<DruidEndPointResp<List<SqlStat>>>() {
    };
    private final ParameterizedTypeReference WALL_STAT_TYPE = new ParameterizedTypeReference<DruidEndPointResp<WallStat>>() {
    };
    private final ParameterizedTypeReference WALL_STAT_LIST_TYPE = new ParameterizedTypeReference<DruidEndPointResp<List<WallStat>>>() {
    };

    /**
     * 获取包含有druid的服务列表
     * 是否包含有druid的判断条件为元数据中的druid=true
     * 即若服务使用了druid，需要在元数据添加druid=true以作标识
     *
     * @return
     */
    public List<DruidServiceList> getDruidServicesList() {
        List<Application> applications = eurekaClient.getApplications().getRegisteredApplications();
        List<DruidServiceList> druidServicesList = new ArrayList<>();
        applications.forEach(application -> {
            DruidServiceList druidService = new DruidServiceList();
            List<InstanceInfo> instanceInfos = application.getInstances();
            druidService.setName(application.getName());
            if (instanceInfos.size() > 1) {
                List<DruidServiceList> instances = new ArrayList<>();
                instanceInfos.forEach(instanceInfo -> {
                    if ("true".equals(instanceInfo.getMetadata().get("druid"))) {
                        druidService.setSize(druidService.getSize() + 1);
                        DruidServiceList instance = new DruidServiceList();
                        instance.setInstanceId(instanceInfo.getInstanceId());
                        instance.setCluster(instanceInfo.getMetadata().get("cluster"));
                        instance.setUrl(instanceInfo.getHomePageUrl());
                        instance.setStatus(instanceInfo.getStatus());
                        instance.setContextPath(instanceInfo.getMetadata().get("druid-context-path"));
                        instance.setName(druidService.getName());
                        if (instance.getStatus() == InstanceInfo.InstanceStatus.UP) {
                            instance.setUpSize(1);
                            druidService.setUpSize(druidService.getUpSize() + 1);
                        }
                        instances.add(instance);
                    }
                });
                if (!instances.isEmpty()) {
                    druidService.setInstances(instances);
                }
            } else if (instanceInfos.size() == 1 && "true".equals(instanceInfos.get(0).getMetadata().get("druid"))) {

                instanceInfos.forEach(instanceInfo -> {
                    druidService.setSize(druidService.getSize() + 1);
                    druidService.setSize(instanceInfos.size());
                    druidService.setInstanceId(instanceInfo.getInstanceId());
                    druidService.setUrl(instanceInfo.getHomePageUrl());
                    druidService.setStatus(instanceInfo.getStatus());
                    druidService.setContextPath(instanceInfo.getMetadata().get("druid-context-path"));
                    if (druidService.getStatus() == InstanceInfo.InstanceStatus.UP) {
                        druidService.setUpSize(1);
                    }
                });
            }
            if (druidService.getSize() > 0) {
                druidServicesList.add(druidService);
            }
        });
        return druidServicesList;
    }


    /**
     * 单个基本信息
     * 获取单个实例的druid基本信息
     * 如版本，jdk等
     *
     * @param instanceTarget
     * @return
     */
    public BasicInfo getInstanceBasicInfo(InstanceTarget instanceTarget) {
        String url = this.getUrl(instanceTarget.getAppName(), instanceTarget.getInstanceId(),instanceTarget.getContextPath(), "basic.json");
        ResponseEntity<DruidEndPointResp<BasicInfo>> result = restTemplate.exchange(url, HttpMethod.POST, null, BASIC_INFO_TYPE);
        BasicInfo basicInfo = result.getBody().getContent();
        basicInfo.setAppName(instanceTarget.getAppName());
        basicInfo.setInstanceId(instanceTarget.getInstanceId());
        return basicInfo;
    }

    /**
     * 基本信息列表
     * 获取多个实例的druid基本信息
     * 如版本，jdk等
     *
     * @param instanceTargets
     * @return
     */
    public List<BasicInfo> getInstanceBasicInfoList(List<InstanceTarget> instanceTargets) {
        List<BasicInfo> basicInfos = new ArrayList<>();
        for (InstanceTarget target : instanceTargets) {
            basicInfos.add(this.getInstanceBasicInfo(target));
        }
        return basicInfos;
    }

    /**
     * 清空单个实例的统计信息
     *
     * @param instanceTarget
     * @return
     */
    public boolean resetInstance(InstanceTarget instanceTarget) {
        String url = this.getUrl(instanceTarget.getAppName(), instanceTarget.getInstanceId(),instanceTarget.getContextPath(), "reset-all.json");
        restTemplate.getForObject(url, String.class);
        return true;
    }


    /**
     * 数据源信息列表
     * 获取多个实例的druid的多数据源信息
     *
     * @param instanceTargets
     * @return
     */
    public List<DataSourceStat> getDataSourceStatList(List<InstanceTarget> instanceTargets) {
        List<DataSourceStat> dataSourceStats = new ArrayList<>();
        for (InstanceTarget instanceTarget : instanceTargets) {
            String url = this.getUrl(instanceTarget.getAppName(), instanceTarget.getInstanceId(),instanceTarget.getContextPath(), "datasource.json");
            ResponseEntity<DruidEndPointResp<List<DataSourceStat>>> result = restTemplate.exchange(url, HttpMethod.POST, null, DATASOURCE_STAT_LIST_TYPE);
            List<DataSourceStat> tempList=result.getBody().getContent();
            for(DataSourceStat dataSourceStat:tempList){
                dataSourceStat.setAppName(instanceTarget.getAppName());
                dataSourceStat.setInstanceId(instanceTarget.getInstanceId());
                dataSourceStat.setId(dataSourceStat.getInstanceId()+dataSourceStat.getIdentity());
                dataSourceStat.setContextPath(instanceTarget.getContextPath());

            }
            dataSourceStats.addAll(tempList);
        }
        return dataSourceStats;
    }

    /**
     * 单个数据源
     * 获取单个数据源信息
     *
     * @param dataSourceTarget
     * @return
     */
    public DataSourceStat getDataSourceStatInfo(DataSourceTarget dataSourceTarget) {
        String url = this.getUrl(dataSourceTarget.getAppName(),
                dataSourceTarget.getInstanceId(),dataSourceTarget.getContextPath(),
                "datasource-" + dataSourceTarget.getDatasourceId() + ".json");
        ResponseEntity<DruidEndPointResp<DataSourceStat>> result = restTemplate.exchange(url, HttpMethod.POST, null, DATASOURCE_STAT_TYPE);
        return result.getBody().getContent();
    }

    /**
     * 连接信息列表
     * 获取多个数据源的链接信息
     *
     * @param dataSourceTargets
     * @return
     */
    public List<ConnectionInfo> getConnectionInfoList(List<DataSourceTarget> dataSourceTargets) {
        List<ConnectionInfo> connectionInfos = new ArrayList<>();
        for (DataSourceTarget dataSourceTarget : dataSourceTargets) {
            String url = this.getUrl(dataSourceTarget.getAppName(),
                    dataSourceTarget.getInstanceId(),dataSourceTarget.getContextPath(),
                    "connectionInfo-" + dataSourceTarget.getDatasourceId() + ".json");
            ResponseEntity<DruidEndPointResp<List<ConnectionInfo>>> result = restTemplate.exchange(url, HttpMethod.POST, null, CONNECTION_INFO_LIST_TYPE);
            List<ConnectionInfo> tempList=result.getBody().getContent();
            for(ConnectionInfo connectionInfo:tempList){
                connectionInfo.setAppName(dataSourceTarget.getAppName());
                connectionInfo.setInstanceId(dataSourceTarget.getInstanceId());
                connectionInfo.setDatasourceId(dataSourceTarget.getDatasourceId());
            }
            connectionInfos.addAll(tempList);
        }
        return connectionInfos;
    }


    /**
     * sql信息列表
     * 获取多个数据源的sql统计
     *
     * @param dataSourceTargets
     * @return
     */
    public List<SqlStat> getSqlStatList(List<DataSourceTarget> dataSourceTargets) {
        List<SqlStat> sqlStats = new ArrayList<>();
        for (DataSourceTarget dataSourceTarget : dataSourceTargets) {
            String url = this.getUrl(dataSourceTarget.getAppName(),
                    dataSourceTarget.getInstanceId(),dataSourceTarget.getContextPath(),
                    "sql.json");
            ResponseEntity<DruidEndPointResp<List<SqlStat>>> result = restTemplate.exchange(url, HttpMethod.POST, null, SQL_STAT_LIST_TYPE);
            sqlStats.addAll(result.getBody().getContent());
        }


        return sqlStats;
    }

    /**
     * 单个sql信息
     * 获取单个sql的统计信息
     *
     * @param sqlTarget
     * @return
     */
    public SqlStat getSqlStatInfo(SqlTarget sqlTarget) {
        String url = this.getUrl(sqlTarget.getAppName(),
                sqlTarget.getInstanceId(),sqlTarget.getContextPath(),
                "sql-" + sqlTarget.getSqlId() + ".json");
        ResponseEntity<DruidEndPointResp<SqlStat>> result = restTemplate.exchange(url, HttpMethod.POST, null, SQL_STAT_TYPE);
        return result.getBody().getContent();
    }

    /**
     * 防火墙信息列表
     * 获取多个数据源的防火墙信息
     *
     * @param dataSourceTargets
     * @return
     */
    public WallStat getWallStatList(List<DataSourceTarget> dataSourceTargets){
        List<WallStat> wallStats=new ArrayList<>();
        for(DataSourceTarget dataSourceTarget:dataSourceTargets){
            String url = this.getUrl(dataSourceTarget.getAppName(),
                    dataSourceTarget.getInstanceId(),dataSourceTarget.getContextPath(),
                    "wall.json");
            ResponseEntity<DruidEndPointResp<WallStat>> result = restTemplate.exchange(url, HttpMethod.POST, null, WALL_STAT_TYPE);
            wallStats.add(result.getBody().getContent());
        }
        return this.countWallStats(wallStats);
    }

    /**
     * 汇总防火墙信息
     * @param wallStatList
     * @return
     */
    private WallStat countWallStats(List<WallStat> wallStatList){
        WallStat rs=new WallStat();
        if(wallStatList==null || wallStatList.isEmpty()){
            return rs;
        }
        Map<String,Function> functionCount=new HashMap<>();
        Map<String,Table> tableCount=new HashMap<>();
        Map<String,WallSql> whiteCount=new HashMap<>();
        Map<String,WallSql> blackCount=new HashMap<>();
        for(WallStat wallStat:wallStatList){
            //记数信息
            rs.setCheckCount(rs.getCheckCount()+wallStat.getCheckCount());
            rs.setHardCheckCount(rs.getHardCheckCount() + wallStat.getHardCheckCount() );
            rs.setViolationCount(rs.getViolationCount() + wallStat.getViolationCount() );
            rs.setViolationEffectRowCount(rs.getViolationEffectRowCount() + wallStat.getViolationEffectRowCount() );
            rs.setBlackListHitCount(rs.getBlackListHitCount() + wallStat.getBlackListHitCount() );
            rs.setBlackListSize(rs.getBlackListSize() + wallStat.getBlackListSize() );
            rs.setWhiteListHitCount(rs.getWhiteListHitCount() + wallStat.getWhiteListHitCount() );
            rs.setWhiteListSize(rs.getWhiteListSize()+ wallStat.getWhiteListSize() );
            rs.setSyntaxErrorCount(rs.getSyntaxErrorCount() + wallStat.getSyntaxErrorCount()  );

            for(Function function:wallStat.getFunctions()){
                Function tempfunction=functionCount.get(function.getName());
                if(tempfunction==null){
                    functionCount.put(function.getName(),function);
                }else{
                    tempfunction.setInvokeCount(function.getInvokeCount()+tempfunction.getInvokeCount());
                    functionCount.put(function.getName(),tempfunction);
                }
            }

            for(Table table:wallStat.getTables()){
                Table tempTable=tableCount.get(table.getName());
                if(tempTable==null){
                    tableCount.put(table.getName(),table);
                }else{
                    tableCount.put(table.getName(),tempTable.add(table));
                }
            }

            for(WallSql wallSql:wallStat.getWhiteList()){
                WallSql tempWallSql=whiteCount.get(wallSql.getSql());
                if(tempWallSql==null){
                    whiteCount.put(wallSql.getSql(),wallSql);
                }else{
                    whiteCount.put(wallSql.getSql(),tempWallSql.add(wallSql));
                }
            }

            for(WallSql wallSql:wallStat.getBlackList()){
                WallSql tempWallSql=blackCount.get(wallSql.getSql());
                if(tempWallSql==null){
                    blackCount.put(wallSql.getSql(),wallSql);
                }else{
                    blackCount.put(wallSql.getSql(),tempWallSql.add(wallSql));
                }
            }
        }

        rs.setFunctions(new ArrayList(functionCount.values()));
        rs.setTables(new ArrayList(tableCount.values()));
        rs.setWhiteList(new ArrayList(whiteCount.values()));
        rs.setBlackList(new ArrayList(blackCount.values()));


        return rs;
    }
    /**
     * 单个防火墙信息
     * 获取单个数据源的防火墙信息
     *
     * @param wallTarget
     * @return
     */
    public SqlStat getWallStatInfo(WallTarget wallTarget) {
        String url = this.getUrl(wallTarget.getAppName(),
                wallTarget.getInstanceId(),wallTarget.getContextPath(),
                "wall-" + wallTarget.getDataSourceId() + ".json");
        ResponseEntity<DruidEndPointResp<SqlStat>> result = restTemplate.exchange(url, HttpMethod.POST, null, WALL_STAT_TYPE);
        return result.getBody().getContent();
    }


    /**
     * 拼接请求druid监控信息的url
     *
     * @param appName
     * @param instanceId
     * @param endpoint
     * @return
     */
    private String getUrl(String appName,
                          String instanceId,
                          String contextPath,
                          String endpoint) {
        InstanceInfo instanceInfo = eurekaClient.getApplication(appName).getByInstanceId(instanceId);
        if (instanceInfo.getStatus() != InstanceInfo.InstanceStatus.UP) {
            throw new RuntimeException(instanceId + "的状态已不可用[" + instanceInfo.getStatus() + "]");
        }
        return instanceInfo.getHomePageUrl() + contextPath + "/druid/" + endpoint;
    }

}
