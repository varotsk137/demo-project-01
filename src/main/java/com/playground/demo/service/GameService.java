package com.playground.demo.service;

import com.playground.demo.mapper.WishListGameMapperInterface;
import com.playground.demo.model.WishListGame;
import com.playground.demo.model.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GameService {

    public WishListGame wishListGameMapping(Game game){
        return WishListGameMapperInterface.INSTANCE.GameToWishlistGame( game );
    }

    public WishListGame wishListGameMappingDiscount(Game game){
        return WishListGameMapperInterface.INSTANCE.GameToWishlistGameTryDiscount( game );
    }

}
