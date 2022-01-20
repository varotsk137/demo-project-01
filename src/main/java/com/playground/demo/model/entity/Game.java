package com.playground.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_game", schema = "game")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer gid;

    @NotBlank
    @Length(min = 1, max = 100)
    @Column(name = "title")
    private String title;

    @Length(max = 1000)
    @Column(name = "description")
    private String description;

    @Min(0)
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "release_date")
    private ZonedDateTime releaseDate;

    @Min(0)
    @Max(100)
    @NotNull
    @Column(name = "discount")
    private Integer discount;

    @ManyToOne
    @JoinColumn(referencedColumnName = "pub_id", name = "publisher")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(referencedColumnName = "dev_id", name = "developer")
    private Developer developer;

    @Size(max = 10)
    @OneToMany(mappedBy = "game", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GameTag> tags;

}
