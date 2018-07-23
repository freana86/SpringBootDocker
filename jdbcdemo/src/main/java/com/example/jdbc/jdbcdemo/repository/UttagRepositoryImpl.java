package com.example.jdbc.jdbcdemo.repository;

import com.example.jdbc.jdbcdemo.domain.Uttag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class UttagRepositoryImpl implements UttagRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Uttag create(Uttag uttag) {
        String SQL = "INSERT INTO uttag (uuid, type) VALUES (?, ?)";
        int update = jdbcTemplate.update(SQL, uttag.getUuid().toString(), uttag.getType());
        return uttag;
    }

    @Override
    public Uttag read(Integer id) {
        String SQL = "SELECT * FROM uttag WHERE id=" + id;
        return jdbcTemplate.queryForObject(SQL, new UttagRowMapper());
    }

    @Override
    public Uttag update(Uttag uttag) {
        // TODO Implement me!..
        return null;
    }

    @Override
    public void delete(Integer id) {
        // TODO Implement me!..
    }

    @Override
    public void dropDB() {
        String SQL = "DELETE FROM uttag";
        jdbcTemplate.update(SQL);
        logger.info("Cleared Database");
    }

    @Override
    public void createAll(List<Uttag> uttagList) {
        for (Uttag uttag : uttagList) {
            this.create(uttag);
        }

    }

    @Override
    public List<Uttag> findAll() {
       String SQL = "SELECT * FROM uttag";
       List<Uttag> uttagList = jdbcTemplate.query(SQL, new UttagRowMapper());
       logger.info("Found {} elements in databas", uttagList.size());
       return uttagList;
    }
}
