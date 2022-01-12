package com.playground.demo.model;

import com.playground.demo.model.entity.Developer;
import com.playground.demo.model.entity.Publisher;
import com.playground.demo.model.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishListGame {

    private Integer gid;

    private String gameTitle;

    private Integer discount;

    private BigDecimal currentPrice;

    private ZonedDateTime releaseDate;

    private ZonedDateTime wishlistTime;

    private Developer developer;

    private Publisher publisher;

    private List<Tag> tagList;


}
