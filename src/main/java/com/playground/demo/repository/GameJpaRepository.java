package com.playground.demo.repository;

import com.playground.demo.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameJpaRepository extends JpaRepository<Game, Integer> {

    Game findByTitle(String title);

}
