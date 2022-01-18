package com.playground.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game implements Serializable {

    @Positive
    @NotNull
    private Integer gid;

    @NotBlank
    @Length(min = 1, max = 100)
    private String title;

    @Length(max = 1000)
    private String description;

    @Min(0)
    private BigDecimal price;

    private ZonedDateTime releaseDate;

    @Min(0)
    @Max(100)
    @NotNull
    private Integer discount;

    private Publisher publisher;

    private Developer developer;

    @Size(max = 10)
    private List<Tag> tags;

}
