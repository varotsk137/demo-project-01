package com.playground.demo.repository;

import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperJpaRepository extends JpaRepository<Developer, Integer> {

    Developer findByName(String name);

}
