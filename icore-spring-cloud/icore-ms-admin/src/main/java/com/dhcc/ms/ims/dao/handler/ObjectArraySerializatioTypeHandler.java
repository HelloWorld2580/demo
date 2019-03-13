package com.dhcc.ms.ims.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class ObjectArraySerializatioTypeHandler implements TypeHandler<Object[]> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Object[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setBytes(i, SerializationUtils.serialize(parameter));
    }

    @Override
    public Object[] getResult(ResultSet rs, String columnName) throws SQLException {
        return (Object[]) SerializationUtils.deserialize(rs.getBytes(columnName));
    }

    @Override
    public Object[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        return (Object[]) SerializationUtils.deserialize(rs.getBytes(columnIndex));
    }

    @Override
    public Object[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return (Object[]) SerializationUtils.deserialize(cs.getBytes(columnIndex));
    }

}
