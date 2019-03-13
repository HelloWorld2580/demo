package com.dhcc.ms.ims.service;

import com.dhcc.ms.ims.dto.request.druid.DataSourceTarget;
import com.dhcc.ms.ims.dto.request.druid.InstanceTarget;
import com.dhcc.ms.ims.dto.request.druid.SqlTarget;
import com.dhcc.ms.ims.dto.request.druid.WallTarget;
import com.dhcc.ms.ims.dto.response.druid.*;

import java.util.List;

public interface DruidMoniterService {
    /**
     * 获取包含有druid的服务列表
     * 是否包含有druid的判断条件为元数据中的druid=true
     * 即若服务使用了druid，需要在元数据添加druid=true以作标识
     *
     * @return
     */
    List<DruidServiceList> getDruidServicesList();

    /**
     * 单个基本信息
     * 获取单个实例的druid基本信息
     * 如版本，jdk等
     *
     * @param instanceTarget
     * @return
     */
    BasicInfo getInstanceBasicInfo(InstanceTarget instanceTarget);

    /**
     * 基本信息列表
     * 获取多个实例的druid基本信息
     * 如版本，jdk等
     *
     * @param instanceTargets
     * @return
     */
    List<BasicInfo> getInstanceBasicInfoList(List<InstanceTarget> instanceTargets);


    /**
     * 清空单个实例的统计信息
     *
     * @param instanceTarget
     * @return
     */
    boolean resetInstance(InstanceTarget instanceTarget);

    /**
     * 数据源列表
     * 获取多个实例的druid的多数据源信息
     *
     * @param instanceTargets
     * @return
     */
    List<DataSourceStat> getDataSourceStatList(List<InstanceTarget> instanceTargets);

    /**
     * 单个数据源
     * 获取单个数据源信息
     *
     * @param dataSourceTarget
     * @return
     */
    DataSourceStat getDataSourceStatInfo(DataSourceTarget dataSourceTarget);

    /**
     * 连接信息列表
     * 获取多个数据源的链接信息
     *
     * @param dataSourceTargets
     * @return
     */
    List<ConnectionInfo> getConnectionInfoList(List<DataSourceTarget> dataSourceTargets);


    /**
     * sql信息列表
     * 获取多个数据源的sql统计
     *
     * @param dataSourceTargets
     * @return
     */
    List<SqlStat> getSqlStatList( List<DataSourceTarget> dataSourceTargets);

    /**
     * 单个sql信息
     * 获取单个sql的统计信息
     *
     * @param sqlTarget
     * @return
     */
    SqlStat getSqlStatInfo(SqlTarget sqlTarget);

    /**
     * 防火墙信息列表
     * 获取多个数据源的防火墙信息
     *
     * @param dataSourceTargets
     * @return
     */
    WallStat getWallStatList( List<DataSourceTarget> dataSourceTargets);


    /**
     * 单个防火墙信息
     * 获取单个数据源的防火墙信息
     *
     * @param wallTarget
     * @return
     */
    public SqlStat getWallStatInfo(WallTarget wallTarget);
}
