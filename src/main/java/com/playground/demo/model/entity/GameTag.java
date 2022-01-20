package com.playground.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.playground.demo.model.entity.id.GameTagId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_game_tag", schema = "game")
public class GameTag implements Serializable {

    @EmbeddedId
    private GameTagId id;

    @ManyToOne
    @MapsId("game_id")
    @JoinColumn(referencedColumnName = "game_id", name = "game_id")
    @JsonBackReference
    private Game game;

    @ManyToOne
    @MapsId("tag_id")
    @JoinColumn(referencedColumnName = "tag_id", name = "tag_id")
    private Tag tag;

}
