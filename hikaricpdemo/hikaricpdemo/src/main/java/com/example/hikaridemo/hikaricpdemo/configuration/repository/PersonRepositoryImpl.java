package com.example.hikaridemo.hikaricpdemo.configuration.repository;

import com.example.hikaridemo.hikaricpdemo.configuration.domain.Person;
import com.example.hikaridemo.hikaricpdemo.configuration.domain.PersonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Person create(Person person) {
        String SQL = "INSERT INTO person (name, age) VALUES (?, ?)";
        jdbcTemplate.update(SQL, person.getName(), person.getAge());
        return person;
    }

    @Override
    public Person read(Long id) {
        String SQL = "SELECT * FROM person WHERE id = " + id;
        return jdbcTemplate.queryForObject(SQL, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person p = PersonBuilder.aPerson()
                        .withId(resultSet.getLong("id"))
                        .withName(resultSet.getString("name"))
                        .withAge(resultSet.getInt("age")).build();

                logger.info("Read Person with id: {}", resultSet.getLong("id"));
                return p;
            }
        });
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public void delete(Long id) {
        String SQL = "DELETE FROM person WHERE id = "+ id;
        jdbcTemplate.update(SQL);
        logger.info("Deleted Person with Id: {}", id);
    }

    @Override
    public List<Person> getAll() {
        String SQL = "SELECT * FROM person";
        return jdbcTemplate.query(SQL, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet resultSet, int i) throws SQLException {
                Person p = PersonBuilder.aPerson()
                        .withId(resultSet.getLong("id"))
                        .withName(resultSet.getString("name"))
                        .withAge(resultSet.getInt("age")).build();
                return p;
            }
        });
    }
}
