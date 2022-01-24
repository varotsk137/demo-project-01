package com.playground.demo.repository;

import com.playground.demo.model.entity.GameTag;
import com.playground.demo.model.entity.id.GameTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface GameTagJpaRepository extends JpaRepository<GameTag, GameTagId> {

    void deleteByIdGameId(Integer gameId);
}
