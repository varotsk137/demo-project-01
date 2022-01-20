package com.playground.demo.repository;

import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherJpaRepository extends JpaRepository<Publisher, Integer> {

    Publisher findByName(String name);

}
