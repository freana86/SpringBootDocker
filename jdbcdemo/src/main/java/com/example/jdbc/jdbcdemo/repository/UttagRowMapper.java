package com.example.jdbc.jdbcdemo.repository;

import com.example.jdbc.jdbcdemo.domain.Uttag;
import com.example.jdbc.jdbcdemo.domain.UttagBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UttagRowMapper implements RowMapper<Uttag> {
    @Override
    public Uttag mapRow(ResultSet resultSet, int i) throws SQLException {
        Uttag uttag = UttagBuilder.empty()
                .withId(resultSet.getInt("id"))
                .withType(resultSet.getString("type"))
                .withUuid(UUID.fromString(resultSet.getString("uuid")))
                .build();
        return uttag;
    }
}
