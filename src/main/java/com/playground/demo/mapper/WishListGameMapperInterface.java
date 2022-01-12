package com.playground.demo.mapper;

import ch.qos.logback.core.util.StatusPrinter;
import com.playground.demo.model.WishListGame;
import com.playground.demo.model.entity.Game;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper
public interface WishListGameMapperInterface {

    WishListGameMapperInterface INSTANCE = Mappers.getMapper(WishListGameMapperInterface.class);

    @Mapping(target = "gid", source = "gid")
    @Mapping(target = "gameTitle", source = "title", defaultValue = "No Title")
    @Mapping(target = "discount", source = "discount")
    @Mapping(target = "currentPrice", source = "price")
    @Mapping(target = "releaseDate", source = "releaseDate")
    @Mapping(target = "wishlistTime", expression = "java(java.time.ZonedDateTime.now())")
    @Mapping(target = "tagList", source = "tags")
    WishListGame gameToWishlistGame(Game game);

    @AfterMapping
    default void after(@MappingTarget WishListGame.WishListGameBuilder builder, Game game) {

        if (game.getDiscount() > 0) {
            builder.currentPrice(calcCurrentPrice(game.getPrice(), game.getDiscount()));
        }

    }

    private BigDecimal calcCurrentPrice(BigDecimal initPrice, Integer discount) {
        return initPrice.multiply((BigDecimal.valueOf(100).subtract(BigDecimal.valueOf(discount))).divide(BigDecimal.valueOf(100)));
    }

}
