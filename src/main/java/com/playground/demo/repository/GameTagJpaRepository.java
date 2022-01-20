package com.playground.demo.repository;

import com.playground.demo.model.entity.Game;
import com.playground.demo.model.entity.GameTag;
import com.playground.demo.model.entity.id.GameTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameTagJpaRepository extends JpaRepository<GameTag, GameTagId> {

}
