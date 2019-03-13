package com.dhcc.ms.ims.controller;

import com.dhcc.ms.ims.dto.request.druid.DataSourceTarget;
import com.dhcc.ms.ims.dto.request.druid.InstanceTarget;
import com.dhcc.ms.ims.dto.request.druid.SqlTarget;
import com.dhcc.ms.ims.dto.request.druid.WallTarget;
import com.dhcc.ms.ims.dto.response.druid.*;
import com.dhcc.ms.ims.service.DruidMoniterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/druid-monitor")
public class DruidController {

    @Autowired
    private DruidMoniterService druidMoniterService;

    /**
     * 获取包含有druid的服务列表
     * 是否包含有druid的判断条件为元数据中的druid=true
     * 即若服务使用了druid，需要在元数据添加druid=true以作标识
     *
     * @return
     */
    @GetMapping("/service/list")
    public List<DruidServiceList>  getDruidServicesList(){
        return druidMoniterService.getDruidServicesList();
    }

    /**
     * 基本信息列表
     * 获取多个实例的druid基本信息
     * 如版本，jdk等
     *
     * @param instanceTargets
     * @return
     */
    @PostMapping("/basicInfo/list")
    private List<BasicInfo> getInstanceBasicInfo(@RequestBody List<InstanceTarget> instanceTargets) {
        return druidMoniterService.getInstanceBasicInfoList(instanceTargets);
    }

    /**
     * 清空单个实例的统计信息
     *
     * @param instanceTarget
     * @return
     */
    @PostMapping("/resetAll")
    private boolean resetInstance(@RequestBody InstanceTarget instanceTarget) {
        return druidMoniterService.resetInstance(instanceTarget);
    }


    /**
     * 数据源列表
     * 获取多个实例的druid的多数据源信息
     *
     * @param instanceTargets
     * @return
     */
    @PostMapping("/dataSource/list")
    private List<DataSourceStat> getDataSourceStatList(@RequestBody List<InstanceTarget> instanceTargets) {
       return druidMoniterService.getDataSourceStatList(instanceTargets);
    }

    /**
     * 连接信息列表
     * 获取多个数据源的链接信息
     *
     * @param dataSourceTargets
     * @return
     */
    @PostMapping("/connectionInfo/list")
    private List<ConnectionInfo> getConnectionInfoList(@RequestBody List<DataSourceTarget> dataSourceTargets) {
        return druidMoniterService.getConnectionInfoList(dataSourceTargets);
    }


    /**
     * sql信息列表
     * 获取多个数据源的sql统计
     *
     * @param dataSourceTargets
     * @return
     */
    @PostMapping("/sqlstat/list")
    private List<SqlStat> getSqlStatList(@RequestBody  List<DataSourceTarget> dataSourceTargets) {
        return druidMoniterService.getSqlStatList(dataSourceTargets);
    }

    /**
     * 单个sql信息
     * 获取单个sql的统计信息
     *
     * @param sqlTarget
     * @return
     */
    @PostMapping("/{dataSourceId}/sqlstat/{sqlId}")
    private SqlStat getSqlStatInfo(@RequestBody SqlTarget sqlTarget) {
        return druidMoniterService.getSqlStatInfo(sqlTarget);
    }

    /**
     * 防火墙信息列表
     * 获取多个数据源的防火墙信息
     *
     * @param dataSourceTargets
     * @return
     */
    @PostMapping("/wallstat/count")
    public WallStat getWallStatList(@RequestBody List<DataSourceTarget> dataSourceTargets) {
        return druidMoniterService.getWallStatList(dataSourceTargets);
    }

    /**
     * 单个防火墙信息
     * 获取单个数据源的防火墙信息
     *
     * @param wallTarget
     * @return
     */
    @PostMapping("/{dataSourceId}/wallstat")
    public SqlStat getWallStatInfo(@RequestBody WallTarget wallTarget) {
        return druidMoniterService.getWallStatInfo(wallTarget);
    }






}
