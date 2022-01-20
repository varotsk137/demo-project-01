package com.playground.demo.model.request;

import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.GameTag;
import com.playground.demo.model.entity.Publisher;
import com.playground.demo.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDtoRequest implements Serializable {

    @Valid
    private GameRequest gameRequest;

    @Valid
    private PublisherRequest publisherRequest;

    @Valid
    private DeveloperRequest developerRequest;

    @Size(max = 10)
    private List<String> tagList;

}
