package com.dhcc.ms.ims.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.dhcc.ms.ims.po.InstanceInfo;
import com.github.abel533.mapper.Mapper;

public interface InstanceInfoDao extends Mapper<InstanceInfo> {
    @Select("SELECT id FROM msims_instance_info")
    @Results(@Result(column = "id"))
    Set<String> selectAllInstanceId();

}
