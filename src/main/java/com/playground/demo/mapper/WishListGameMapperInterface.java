package com.playground.demo.mapper;

import com.playground.demo.model.WishListGame;
import com.playground.demo.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WishListGameMapperInterface {

    WishListGameMapperInterface INSTANCE = Mappers.getMapper(WishListGameMapperInterface.class);

    @Mapping(target = "gameTitle", source = "title", defaultValue = "No Title")
    @Mapping(target = "currentPrice", source = "price")
    @Mapping(target = "wishlistTime", expression = "java(java.time.ZonedDateTime.now())")
    @Mapping(target = "tagList", source = "tags")
    WishListGame GameToWishlistGame(Game game);

    @Mapping(target = "gameTitle", source = "title", defaultValue = "No Title")
    @Mapping(target = "currentPrice", source = "price", qualifiedByName = "discount")
    @Mapping(target = "wishlistTime", expression = "java(java.time.ZonedDateTime.now())")
    @Mapping(target = "tagList", source = "tags")
    WishListGame GameToWishlistGameTryDiscount(Game game);

    @Named(value = "discount")
    public default Double discountProductPrice(Double price){
        double discount = 11.11;
        return (price * (100.00-discount) / 100);
    }
}
