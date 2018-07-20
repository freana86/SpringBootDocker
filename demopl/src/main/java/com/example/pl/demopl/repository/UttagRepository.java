package com.example.pl.demopl.repository;

import com.example.pl.demopl.domain.Uttag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UttagRepository extends CrudRepository<Uttag, Integer> {
}
