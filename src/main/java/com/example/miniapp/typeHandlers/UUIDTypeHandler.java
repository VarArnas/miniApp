package com.example.miniapp.typeHandlers;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.util.UUID;

public class UUIDTypeHandler implements TypeHandler<UUID> {
    @Override
    public void setParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter, Types.OTHER);
    }

    @Override
    public UUID getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getObject(columnName, UUID.class);
    }

    @Override
    public UUID getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getObject(columnIndex, UUID.class);
    }

    @Override
    public UUID getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
