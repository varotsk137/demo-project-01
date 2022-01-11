package com.playground.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Game {

    @Positive
    @NotNull
    private Integer gid;

    @NotBlank
    @Length(min = 1, max = 100)
    private String title;

    @Length(max = 1000)
    private String description;

    @Min(0)
    private Double price;

    private ZonedDateTime releaseDate;

    private Publisher publisher;
    private Developer developer;

    @Size(min = 0, max = 10)
    private List<Tag> tags;

}
