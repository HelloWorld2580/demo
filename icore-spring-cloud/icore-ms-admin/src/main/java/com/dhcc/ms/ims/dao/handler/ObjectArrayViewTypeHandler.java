package com.dhcc.ms.ims.dao.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import com.google.gson.Gson;

public class ObjectArrayViewTypeHandler implements TypeHandler<Object[]> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Object[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, new Gson().toJson(parameter));
    }

    @Override
    public Object[] getResult(ResultSet rs, String columnName) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        throw new UnsupportedOperationException();
    }

}
