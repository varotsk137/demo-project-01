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
    default void after(@MappingTarget WishListGame wishListGame, Game game){

        if (game.getDiscount() > 0) {
        wishListGame.setCurrentPrice(calcCurrentPrice(game.getPrice(), game.getDiscount()));
        }

    }

    private BigDecimal calcCurrentPrice(BigDecimal initPrice, Integer discount){
        return initPrice.multiply((BigDecimal.valueOf(100).subtract(BigDecimal.valueOf(discount))).divide(BigDecimal.valueOf(100)));
    }

//    @Mapping(target = "gameTitle", source = "title", defaultValue = "No Title")
//    @Mapping(target = "currentPrice", source = "price", qualifiedByName = "discount")
//    @Mapping(target = "wishlistTime", expression = "java(java.time.ZonedDateTime.now())")
//    @Mapping(target = "tagList", source = "tags")
//    WishListGame GameToWishlistGameTryDiscount(Game game);
//
//    @Named(value = "discount")
//    public default BigDecimal discountProductPrice(BigDecimal price){
//        BigDecimal discount = BigDecimal.valueOf(11.11);
//        return price.multiply((BigDecimal.valueOf(100).subtract(discount)).divide(BigDecimal.valueOf(100)));
//    }

}
