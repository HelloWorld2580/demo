package com.dhcc.ms.ims.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dhcc.ms.ims.po.ExceptionWarn;
import com.github.abel533.mapper.Mapper;

public interface ExceptionWarnDao extends Mapper<ExceptionWarn> {
    @Delete("DELETE FROM msims_exceptions_warn WHERE timestamp <= (SELECT timestamp FROM (SELECT timestamp FROM msims_exceptions_warn ORDER BY timestamp DESC LIMIT 1 OFFSET #{maxRecord} ) foo)")
    public int deleteOldRecords(int maxRecord);

    @Select("SELECT count(*) FROM msims_exceptions_warn WHERE timestamp >= #{startTimrestamp} AND timestamp < #{endTimrestamp}")
    public int getNewExceptionWarnsCount(@Param("startTimrestamp") String startTimrestamp,
            @Param("endTimrestamp") String endTimrestamp);
}
