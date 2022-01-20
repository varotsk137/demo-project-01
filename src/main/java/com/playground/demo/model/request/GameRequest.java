package com.playground.demo.model.request;

import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.GameTag;
import com.playground.demo.model.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameRequest implements Serializable {

    @NotBlank
    @Length(min = 1, max = 100)
    private String title;

    @Length(max = 1000)
    private String description;

    @Min(0)
    private BigDecimal price;

    @NotNull
    private ZonedDateTime releaseDate;

    @Min(0)
    @Max(100)
    @NotNull
    private Integer discount;

}
