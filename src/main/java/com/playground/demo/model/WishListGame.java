package com.playground.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishListGame {

    private String gameTitle;
    private Double currentPrice;
    private ZonedDateTime wishlistTime;

    private Developer developer;
    private Publisher publisher;

    private List<Tag> tagList;

}
