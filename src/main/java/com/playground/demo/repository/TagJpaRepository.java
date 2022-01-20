package com.playground.demo.repository;

import com.playground.demo.model.entity.GameTag;
import com.playground.demo.model.entity.Tag;
import com.playground.demo.model.entity.id.GameTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagJpaRepository extends JpaRepository<Tag, Integer> {

    Tag findByTagName(String tagName);

}
